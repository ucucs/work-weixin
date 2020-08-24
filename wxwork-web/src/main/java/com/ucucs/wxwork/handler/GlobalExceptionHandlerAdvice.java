package com.ucucs.wxwork.handler;

import com.ucucs.wxwork.core.entity.Result;
import com.ucucs.wxwork.core.exception.base.BaseException;
import com.ucucs.wxwork.core.exception.type.SystemErrorType;
import com.ucucs.wxwork.exception.ValidateErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

/**
 * 全局异常错误处理.
 *
 * @author ucucs.
 */
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

  @ExceptionHandler(ServletRequestBindingException.class)
  public Result<?> servletRequestBindingExceptionHandler(ServletRequestBindingException ex) {
    logger.error("servlet request bind exception:{}", ex.getMessage());
    return Result.fail(ValidateErrorType.ILLEGAL_ARGUMENT_TYPE, ex);
  }

  @ExceptionHandler(BindException.class)
  public Result<?> bindExceptionHandler(BindException ex) {
    logger.error("param bind exception:{}", ex.getMessage());
    BindingResult bindingTTResult = ex.getBindingResult();
    String fieldError =
        bindingTTResult.getFieldError() == null
            ? "参数验证异常"
            : bindingTTResult.getFieldError().getDefaultMessage();

    return Result.fail(ValidateErrorType.ARGUMENT_BINDING, fieldError);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Result<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
    logger.error("method argument valid exception:{}", ex.getMessage());
    BindingResult bindingTTResult = ex.getBindingResult();
    String fieldError =
        bindingTTResult.getFieldError() == null
            ? "方法参数验证异常"
            : bindingTTResult.getFieldError().getDefaultMessage();

    return Result.fail(ValidateErrorType.ILLEGAL_ARGUMENT, fieldError);
  }

  @ExceptionHandler(value = {MissingServletRequestParameterException.class})
  public Result<?> missingServletRequestParameterExceptionHanlder(
      MissingServletRequestParameterException ex) {
    logger.error("missing servlet request parameter exception:{}", ex.getMessage());
    return Result.fail(ValidateErrorType.MISSING_ARGUMENT);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public Result<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
    logger.error("request body read exception:{}", ex.getMessage());
    return Result.fail(ValidateErrorType.ILLEGAL_BODY);
  }

  @ExceptionHandler(MultipartException.class)
  public Result<?> multipartExceptionHandler(MultipartException ex) {
    logger.error("request multipart file size limit exception:{}", ex.getMessage());
    return Result.fail(ValidateErrorType.ILLEGAL_MULTIPART_ERROR, ex);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public Result<?> forbiddenErrorHandler(HttpRequestMethodNotSupportedException ex) {
    logger.error("request method support exception:{}", ex.getMessage());
    return Result.fail(ValidateErrorType.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(value = {BaseException.class})
  public Result<?> baseExceptionHandler(BaseException ex) {
    logger.error("base exception:{}", ex.getMessage());
    return Result.fail(ex.getErrorType(), ex.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<?> runtimeExceptionHandler(RuntimeException ex) {
    logger.error("runtime exception", ex);
    return Result.fail(SystemErrorType.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = {Throwable.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<?> throwableHandler(Throwable ex) {
    logger.error("throwable exception", ex);
    return Result.fail(SystemErrorType.SYSTEM_ERROR);
  }
}
