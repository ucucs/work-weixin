package com.ucucs.wxwork.module.exception;

import com.ucucs.wxwork.core.constant.ErrorHintConsts;

/**
 * coding.
 *
 * @author ucucs.
 */
public class WeiXinException extends RuntimeException {

  public WeiXinException(String message) {
    super(message);
  }

  public static WeiXinException build(String errorCode, String errorMsg) {
    String readableTips = ErrorHintConsts.getErrorMsg(errorCode, errorMsg);
    return new WeiXinException(readableTips);
  }
}
