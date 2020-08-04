package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.core.constant.MessageConsts.MsgType;
import com.ucucs.wxwork.entity.Result;
import com.ucucs.wxwork.module.entity.WxMessage;
import com.ucucs.wxwork.module.entity.wrap.MessageSendResult;
import com.ucucs.wxwork.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {

  private final MessageService messageService;

  @PostMapping("/send")
  public Result<?> sendMessage(@RequestBody WxMessage message) {
    Assert.notNull(message, "发送消息内容体不能为空");
    Assert.hasText(message.getMsgType(), "消息类型不能为空");

    if (MsgType.MINIPROGRAM_NOTICE.equals(message.getMsgType())) {
      Assert.hasText(message.getAppId(), "小程序AppId不能为空");
    } else {
      Assert.notNull(message.getAgentId(), "应用Id不能为空");
    }

    MessageSendResult sendResult = messageService.sendMessage(message);
    return Result.success(sendResult);
  }
}
