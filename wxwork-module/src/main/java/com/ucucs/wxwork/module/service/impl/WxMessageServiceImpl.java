package com.ucucs.wxwork.module.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.core.constant.ApiPathConsts;
import com.ucucs.wxwork.core.constant.MessageConsts.MsgType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.WxMessage;
import com.ucucs.wxwork.module.entity.wrap.MessageSendResult;
import com.ucucs.wxwork.module.service.WxMessageService;
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
public class WxMessageServiceImpl implements WxMessageService {

  private final WxWorkService wxWorkService;

  private String getAccessToken(String msgType) {
    String tokenType =
        MsgType.MINIPROGRAM_NOTICE.equals(msgType) ? TokenType.MINI_APP : TokenType.CONTACT;
    return wxWorkService.getAccessToken(tokenType);
  }

  @Override
  public MessageSendResult sendMessage(WxMessage message) {
    String accessToken = getAccessToken(message.getMsgType());

    MapBuilder<String, Object> paramBuilder = new MapBuilder<>();
    paramBuilder.put("access_token", accessToken);

    JsonNode rspNode =
        wxWorkService.getRsp(
            ApiPathConsts.MESSAGE_SEND,
            paramBuilder.build(),
            message.wrapMsgBody(),
            RequestType.POST_JSON);

    return MessageSendResult.parseMsgBody(rspNode);
  }
}
