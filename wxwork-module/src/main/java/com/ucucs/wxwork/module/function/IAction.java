package com.ucucs.wxwork.module.function;

/**
 * 执行相关动作.
 *
 * @param <T> 对象类型
 */
@FunctionalInterface
public interface IAction<T> {

  /**
   * 执行动作.
   *
   * @param param 动作参数
   */
  void run(T param);
}
