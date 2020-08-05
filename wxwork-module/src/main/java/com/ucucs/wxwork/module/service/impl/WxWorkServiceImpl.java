package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ucucs.wxwork.core.constant.ApiPathConsts;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RspStatus;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.component.OkHttpComponent;
import com.ucucs.wxwork.module.exception.WeiXinException;
import com.ucucs.wxwork.module.service.WxConfigManage;
import com.ucucs.wxwork.module.service.WxWorkService;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class WxWorkServiceImpl implements WxWorkService {

  private static final Logger LOG = LoggerFactory.getLogger(WxWorkServiceImpl.class);

  private final OkHttpComponent okHttpComponent;

  private final WxConfigManage wxConfigManage;

  private static final Cache<String, String> TOKEN_CACHE =
      Caffeine.newBuilder()
          .expireAfterWrite(95, TimeUnit.MINUTES)
          .initialCapacity(5)
          .maximumSize(50)
          .build();

  @Override
  public String getCorpId() {
    return wxConfigManage.getCorpId();
  }

  @Override
  public String getAccessToken(String category) {
    String cacheKey = String.format("WeiXin_%s_%s", wxConfigManage.getCorpId(), category);
    String accessToken = TOKEN_CACHE.getIfPresent(cacheKey);
    if (!StringUtils.hasText(accessToken)) {
      JsonNode tokenNode = getToken(category);
      accessToken = tokenNode.get("access_token").asText();
      if (StringUtils.hasText(accessToken)) {
        TOKEN_CACHE.put(cacheKey, accessToken);
      }
    }
    return accessToken;
  }

  @Override
  public JsonNode getRsp(
      String urlPath, Map<String, Object> urlParams, Object bodyObject, RequestType requestType) {
    String reqUrl = wxConfigManage.getApiUrl(urlPath);

    // 访问微信服务器的地址
    String rspString = null;
    switch (requestType) {
      case GET:
        rspString = okHttpComponent.doGet(reqUrl, urlParams, null);
        break;
      case POST_JSON:
        String bodyJson = JsonUtil.toJson(bodyObject);
        rspString = okHttpComponent.doPostJson(reqUrl, bodyJson, urlParams);
        break;
      case POST_XML:
        break;
    }

    LOG.info("微信接口返回{} {} {}", urlParams, bodyObject, rspString);

    JsonNode rspNode = JsonUtil.fromJson(rspString);

    if (rspNode == null) {
      throw new WeiXinException("获取微信Token失败,响应为空");
    }

    String errCode = rspNode.get("errcode").asText();
    if (!RspStatus.SUCCESS.equals(errCode)) {
      throw WeiXinException.build(errCode, rspNode.get("errmsg").asText());
    }

    return rspNode;
  }

  private JsonNode getToken(String category) {
    String secret = wxConfigManage.getTypeSecret(category);
    if (!StringUtils.hasText(secret)) {
      LOG.warn("未找到指定类型的密钥:{}", category);
      throw new WeiXinException("未找到指定类型的密钥");
    }

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("corpid", wxConfigManage.getCorpId()).put("corpsecret", secret);

    return getRsp(ApiPathConsts.GET_TOKEN, paramBuilder.build(), null, RequestType.GET);
  }
}
