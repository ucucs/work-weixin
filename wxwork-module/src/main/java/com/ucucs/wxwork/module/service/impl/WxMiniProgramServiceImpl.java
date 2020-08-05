package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.core.constant.ApiPathConsts;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.wrap.CodeToSessionResult;
import com.ucucs.wxwork.module.service.WxMiniProgramService;
import com.ucucs.wxwork.module.service.WxWorkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class WxMiniProgramServiceImpl implements WxMiniProgramService {

  private final WxWorkService wxWorkService;

  private String getAccessToken() {
    return wxWorkService.getAccessToken(TokenType.MINI_APP);
  }

  @Override
  public CodeToSessionResult jsCodeToSession(String jsCode) {
    String accessToken = getAccessToken();

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder
        .put("access_token", accessToken)
        .put("grant_type", "authorization_code")
        .put("js_code", jsCode);

    JsonNode rspNode =
        wxWorkService.getRsp(
            ApiPathConsts.JSCODE_TO_SESSION, paramBuilder.build(), null, RequestType.GET);

    return CodeToSessionResult.parseMsgBody(rspNode);
  }
}
