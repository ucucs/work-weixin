package com.ucucs.wxwork.entity;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class ScheduleJob {

  /** 任务名称 */
  private String jobName;
  /** 任务分组 */
  private String jobGroup;
  /** cron表达式 */
  private String cronExpression;
  /** spring bean 任务所在 类名 */
  private String jobClass;
  /** 任务调用的方法传入的参数,统一使用String, */
  private Map<String, Object> parameters;
  /** 任务是否有状态 是否支持并行 */
  private Boolean concurrent;
  /** 任务描述 */
  private String description;
  /** 是否要求唤醒 */
  private Boolean requestsRecovery;
  /** 是否持久化 */
  private Boolean druable;
}
