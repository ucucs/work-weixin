package com.ucucs.wxwork.module.exception;

import com.ucucs.wxwork.core.constant.ErrorHintConsts;
import com.ucucs.wxwork.core.exception.base.BaseException;

/**
 * coding.
 *
 * @author ucucs.
 */
public class WeiXinException extends BaseException {

  public WeiXinException(String message) {
    super(WxWorkErrorType.RESPONSE_ERROR, message);
  }

  public static WeiXinException build(String errorCode, String errorMsg) {
    String readableTips = ErrorHintConsts.getErrorMsg(errorCode, errorMsg);
    return new WeiXinException(readableTips);
  }
}
