package com.ucucs.wxwork.core.exception.base;

import com.ucucs.wxwork.core.exception.type.ErrorType;
import com.ucucs.wxwork.core.exception.type.SystemErrorType;

/**
 * 基本异常定义.
 *
 * @author ucucs.
 */
public class BaseException extends RuntimeException {

  /** 错误类型. */
  protected final ErrorType errorType;

  public BaseException() {
    this(SystemErrorType.SYSTEM_ERROR);
  }

  public BaseException(ErrorType errorType) {
    this.errorType = errorType;
  }

  public BaseException(ErrorType errorType, String message) {
    super(message);
    this.errorType = errorType;
  }

  public BaseException(ErrorType errorType, String message, Throwable cause) {
    super(message, cause);
    this.errorType = errorType;
  }

  public ErrorType getErrorType() {
    return errorType;
  }
}
