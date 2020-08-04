package com.ucucs.wxwork.module.service;

import com.ucucs.wxwork.module.entity.WxMessage;
import com.ucucs.wxwork.module.entity.wrap.MessageSendResult;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxMessageService {

  MessageSendResult sendMessage(WxMessage message);
}
