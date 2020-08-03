package com.ucucs.wxwork.core.exception.type;

/**
 * 系统异常定义.
 *
 * @author ucucs.
 */
public enum SystemErrorType implements ErrorType {

  /** 系统繁忙，请稍后再试. */
  SYSTEM_BUSY("-1", "系统繁忙,请稍后再试"),

  /** 系统未知错误. */
  SYSTEM_ERROR("1000", "系统未知出错"),

  /** 系统内部错误. */
  INTERNAL_SERVER_ERROR("1001", "系统错误"),

  /** 业务错误. */
  SERVICE_EXCEPTION("1002", "业务错误"),

  /** 非法状态. */
  ILLEGAL_STATE("1003", "非法状态");

  /** 错误代码. */
  private final String code;

  /** 错误提示. */
  private final String msg;

  SystemErrorType(String code, String msg) {
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
