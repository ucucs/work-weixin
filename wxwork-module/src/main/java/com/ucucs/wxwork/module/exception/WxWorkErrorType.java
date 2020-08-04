package com.ucucs.wxwork.module.exception;

import com.ucucs.wxwork.core.exception.type.ErrorType;

/**
 * 微信异常定义.
 *
 * @author ucucs.
 */
public enum WxWorkErrorType implements ErrorType {

  /** 微信响应错误. */
  RESPONSE_ERROR("3000", "微信响应错误");

  /** 错误代码. */
  private final String code;

  /** 错误提示. */
  private final String msg;

  WxWorkErrorType(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public String getMsg() {
    return msg;
  }
}
