package com.ucucs.wxwork.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public abstract class JobTask extends QuartzJobBean {

  protected static final Logger logger = LoggerFactory.getLogger(JobTask.class);

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext) {
    try {
      executeTask(jobExecutionContext);
    } catch (Exception ex) {
      logger.error("执行任务出错", ex);
    }
  }

  public abstract void executeTask(JobExecutionContext jobExecutionContext);
}
