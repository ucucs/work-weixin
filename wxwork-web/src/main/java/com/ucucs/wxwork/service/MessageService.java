package com.ucucs.wxwork.service;

import com.ucucs.wxwork.module.entity.WxMessage;
import com.ucucs.wxwork.module.entity.wrap.MessageSendResult;
import com.ucucs.wxwork.module.service.WxMessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class MessageService {

  private final WxMessageService wxMessageService;

  public MessageSendResult sendMessage(WxMessage message) {
    return wxMessageService.sendMessage(message);
  }
}
