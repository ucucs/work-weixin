package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ucucs.wxwork.core.constant.ApiPathConsts;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.wrap.JsApiSignature;
import com.ucucs.wxwork.module.service.WxJsApiService;
import com.ucucs.wxwork.module.service.WxWorkService;
import com.ucucs.wxwork.module.util.RandomUtil;
import com.ucucs.wxwork.module.util.crypto.SHA1;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class WxJsApiServiceImpl implements WxJsApiService {

  private final WxWorkService wxWorkService;

  private static final Cache<String, String> TICKET_CACHE =
      Caffeine.newBuilder()
          .expireAfterWrite(7000, TimeUnit.SECONDS)
          .initialCapacity(5)
          .maximumSize(50)
          .build();

  private String getAccessToken() {
    return wxWorkService.getAccessToken(TokenType.CORP);
  }

  @Override
  public String getTicket(boolean forceRefresh) {
    String cacheKey = String.format("WeiXin_Js_Ticket_%s", wxWorkService.getCorpId());
    String jsTicket = TICKET_CACHE.getIfPresent(cacheKey);
    if (!StringUtils.hasText(jsTicket) || forceRefresh) {
      JsonNode tokenNode = fetchJsTicket();
      jsTicket = tokenNode.get("ticket").asText();
      if (StringUtils.hasText(jsTicket)) {
        TICKET_CACHE.put(cacheKey, jsTicket);
      }
    }
    return jsTicket;
  }

  @Override
  public String getAgentTicket(boolean forceRefresh) {
    String cacheKey = String.format("WeiXin_Agent_Ticket_%s", wxWorkService.getCorpId());
    String jsTicket = TICKET_CACHE.getIfPresent(cacheKey);
    if (!StringUtils.hasText(jsTicket) || forceRefresh) {
      JsonNode tokenNode = fetchAgentTicket();
      jsTicket = tokenNode.get("ticket").asText();
      if (StringUtils.hasText(jsTicket)) {
        TICKET_CACHE.put(cacheKey, jsTicket);
      }
    }
    return jsTicket;
  }

  @Override
  public JsApiSignature createSignature(String url) {
    long timestamp = System.currentTimeMillis() / 1000;
    String noncestr = RandomUtil.getRandomStr();
    String jsapiTicket = getTicket(false);
    String signature =
        SHA1.genWithAmple(
            "jsapi_ticket=" + jsapiTicket,
            "noncestr=" + noncestr,
            "timestamp=" + timestamp,
            "url=" + url);
    JsApiSignature jsapiSignature = new JsApiSignature();
    jsapiSignature.setTimestamp(timestamp);
    jsapiSignature.setNonceStr(noncestr);
    jsapiSignature.setUrl(url);
    jsapiSignature.setSignature(signature);
    jsapiSignature.setAppId(wxWorkService.getCorpId());

    return jsapiSignature;
  }

  private JsonNode fetchJsTicket() {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    return wxWorkService.getRsp(
        ApiPathConsts.GET_JSAPI_TICKET, paramBuilder.build(), null, RequestType.GET);
  }

  private JsonNode fetchAgentTicket() {
    String accessToken = wxWorkService.getAccessToken(TokenType.APP);

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken).put("type", "agent_config");

    return wxWorkService.getRsp(
        ApiPathConsts.GET_AGENT_CONFIG_TICKET, paramBuilder.build(), null, RequestType.GET);
  }
}
