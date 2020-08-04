package com.ucucs.wxwork.core.exception.type;

/**
 * 错误类型接口定义.
 *
 * @author ucucs.
 */
public interface ErrorType {

  /**
   * 错误代码.
   *
   * @return 错误代码
   */
  String getCode();

  /**
   * 错误信息.
   *
   * @return 错误信息
   */
  String getMsg();
}
