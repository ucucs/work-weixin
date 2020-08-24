package com.ucucs.wxwork.service;

import com.ucucs.wxwork.entity.ScheduleJob;
import lombok.AllArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Component
public class QuartzJobService {

  private static final Logger logger = LoggerFactory.getLogger(QuartzJobService.class);

  private final SchedulerFactoryBean schedulerFactoryBean;

  public void initJob(ScheduleJob job) throws Exception {
    if (job == null) {
      return;
    }
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

    addJob(scheduler, triggerKey, job);
    logger.info(scheduler + "添加任务:{}", job);
  }

  private void addJob(Scheduler scheduler, TriggerKey triggerKey, ScheduleJob job)
      throws Exception {
    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
    if (null == trigger) {
      /*不存在,创建一个*/
      configJobExistTrigger(scheduler, job);
    } else {
      /*存在,直接用*/
      configJobNoExistTrigger(scheduler, trigger, triggerKey, job);
    }
  }

  private void configJobNoExistTrigger(
      Scheduler scheduler, CronTrigger trigger, TriggerKey triggerKey, ScheduleJob job)
      throws Exception {
    /*Trigger已存在，那么更新相应的定时设置*/
    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
    /* 按新的cronExpression表达式重新构建trigger*/
    trigger =
        trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
    /*按新的trigger重新设置job执行*/
    scheduler.rescheduleJob(triggerKey, trigger);
  }

  public void configJobExistTrigger(Scheduler scheduler, ScheduleJob job) throws Exception {
    Class clazz = Class.forName(job.getJobClass());
    /*设定job详情和属性*/
    JobDetailImpl jobDetail = new JobDetailImpl();
    jobDetail.setDescription(job.getDescription());
    jobDetail.setGroup(job.getJobGroup());
    jobDetail.setJobClass(clazz);
    jobDetail.setJobDataMap(new JobDataMap());
    jobDetail.setKey(JobKey.jobKey(job.getJobGroup(), job.getJobName()));
    jobDetail.setRequestsRecovery(job.getRequestsRecovery());
    /*设置持久*/
    jobDetail.setDurability(job.getDruable());

    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

    CronTrigger trigger =
        TriggerBuilder.newTrigger()
            .withIdentity(job.getJobName(), job.getJobGroup())
            .withSchedule(scheduleBuilder)
            .build();
    scheduler.scheduleJob(jobDetail, trigger);
  }

  private void deleteJob(Scheduler scheduler, TriggerKey triggerKey) {
    if (null != triggerKey) {
      try {
        scheduler.unscheduleJob(triggerKey);
      } catch (SchedulerException e) {
        logger.error("删除job异常");
        e.printStackTrace();
      }
    } else {
      logger.warn("删除job无效,没有指定对应的Trigger");
    }
  }

  public void stopJob(String name, String group) {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    JobKey jobKey = JobKey.jobKey(name, group);
    try {
      scheduler.pauseJob(jobKey);
    } catch (SchedulerException e) {
      logger.error("停止job异常");
      e.printStackTrace();
    }
  }

  public void addJob(ScheduleJob job) {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    Class clazz = null;
    try {
      clazz = Class.forName(job.getJobClass());
      JobDetail jobDetail =
          JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
      try {
        scheduler.addJob(jobDetail, true);
      } catch (SchedulerException e) {
        logger.error("添加job出现异常");
        e.printStackTrace();
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void pauseAll() {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    try {
      scheduler.pauseAll();
    } catch (SchedulerException e) {
      logger.error("暂停所有job出现异常");
      e.printStackTrace();
    }
  }

  public void shutdown() {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    try {
      scheduler.shutdown();
    } catch (SchedulerException e) {
      logger.error("关闭所有Job出现异常!");
      e.printStackTrace();
    }
  }

  public void restartJob(String name, String group) {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    JobKey jobKey = JobKey.jobKey(name, group);
    try {
      scheduler.resumeJob(jobKey);
    } catch (SchedulerException e) {
      logger.error("恢复任务异常");
      e.printStackTrace();
    }
  }

  public void restartAll() {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    try {
      scheduler.resumeAll();
    } catch (SchedulerException e) {
      logger.error("恢复所有job异常");
      e.printStackTrace();
    }
  }

  public void deleteJob(String jobName, String jobGroup) {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
    try {
      /*scheduler.deleteJob(JobKey.jobKey(jobName,jobGroup));*/
      scheduler.unscheduleJob(triggerKey);
    } catch (SchedulerException e) {
      logger.error("删除job" + jobGroup + "." + jobName + "出现异常");
      e.printStackTrace();
    }
  }
}
