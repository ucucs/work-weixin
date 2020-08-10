package com.ucucs.wxwork.core.entity;

import com.ucucs.wxwork.core.exception.base.BaseException;
import com.ucucs.wxwork.core.exception.type.ErrorType;
import com.ucucs.wxwork.core.exception.type.SystemErrorType;
import java.io.Serializable;

/**
 * 基础响应结果.
 *
 * @author ucucs.
 */
public class Result<T> implements Serializable {

  /** 成功响应代码. */
  private static final String SUCCESS_CODE = "0";

  /** 失败响应状态. */
  private static final Integer FAILED_STATUS = 100;

  /** 成功响应状态. */
  private static final Integer SUCCESS_STATUS = 200;

  /** 成功响应提示. */
  private static final String SUCCESS_MESG = "成功";

  /** 状态. */
  private final Integer status;

  /** 响应代码. */
  private final String code;

  /** 响应提示. */
  private final String message;

  /** 响应数据. */
  private T data;

  public Result(ErrorType errorType) {
    this.status = FAILED_STATUS;
    this.code = errorType.getCode();
    this.message = errorType.getMsg();
  }

  public Result(ErrorType errorType, String errorMsg) {
    this.status = FAILED_STATUS;
    this.code = errorType.getCode();
    this.message = errorMsg;
  }

  public Result(ErrorType errorType, String errorMsg, T data) {
    this.status = FAILED_STATUS;
    this.code = errorType.getCode();
    this.message = errorMsg;
    this.data = data;
  }

  /* 私有方法,构造成功对象. */
  private Result(Integer status, String code, String message, T data) {
    this.status = status;
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static Result<?> success(Object data) {
    return new Result<>(SUCCESS_STATUS, SUCCESS_CODE, SUCCESS_MESG, data);
  }

  public static Result<?> success() {
    return success(null);
  }

  public static Result<?> fail() {
    return new Result<>(SystemErrorType.SYSTEM_ERROR);
  }

  public static Result<?> fail(String message) {
    return new Result<>(SystemErrorType.SYSTEM_ERROR, message);
  }

  public static Result<?> fail(BaseException baseException) {
    return new Result<>(baseException.getErrorType());
  }

  public static Result<?> fail(BaseException baseException, Object data) {
    return new Result<>(baseException.getErrorType(), baseException.getMessage(), data);
  }

  public static Result<?> fail(ErrorType errorType) {
    return new Result<>(errorType);
  }

  public static Result<?> fail(ErrorType errorType, String errorMsg) {
    return new Result<>(errorType, errorMsg);
  }

  public static Result<?> fail(ErrorType errorType, Throwable exception) {
    return new Result<>(errorType, exception.getMessage());
  }

  public Integer getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }
}
