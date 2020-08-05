package com.ucucs.wxwork.exception;

import com.ucucs.wxwork.core.exception.type.ErrorType;

/**
 * 数据验证错误定义.
 *
 * @author ucucs.
 */
public enum ValidateErrorType implements ErrorType {

  /** 参数缺失. */
  MISSING_ARGUMENT("10006", "缺少参数"),

  /** 非法数据内容体. */
  ILLEGAL_BODY("10003", "非法数据内容体"),

  /** 错误的参数类型. */
  ILLEGAL_ARGUMENT_TYPE("10010", "参数类型错误"),

  /** 参数错误. */
  ILLEGAL_ARGUMENT("10001", "参数错误"),

  /** 参数绑定错误. */
  ARGUMENT_BINDING("10001", "参数绑定错误"),

  /** 约束声明定义错误. */
  ILLEGAL_CONSTRAINT_DECLARE("10011", "约束声明错误"),

  /** 错误的请求. */
  METHOD_NOT_ALLOWED("10009", "不支持的方法"),

  /** 表单格式错误或超过文件大小限制. */
  ILLEGAL_MULTIPART_ERROR("10013", "表单格式错误或超过限制"),

  /** 验证码错误. */
  CAPTCHA_WRONG("10017", "验证码错误");

  /** 错误代码. */
  private final String code;

  /** 错误提示. */
  private final String msg;

  ValidateErrorType(String code, String msg) {
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
