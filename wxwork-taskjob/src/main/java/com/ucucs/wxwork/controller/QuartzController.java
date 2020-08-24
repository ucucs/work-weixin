package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.core.entity.Result;
import com.ucucs.wxwork.entity.ScheduleJob;
import com.ucucs.wxwork.service.QuartzJobService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/quartz")
public class QuartzController {

  private static final Logger logger = LoggerFactory.getLogger(QuartzController.class);

  private final QuartzJobService scheduleJobManage;

  @RequestMapping("/addjob")
  public Result<?> addJob(@RequestBody ScheduleJob scheduleJob) {
    try {
      scheduleJobManage.initJob(scheduleJob);
      return Result.success("添加job成功");
    } catch (Exception ex) {
      logger.error("添加job异常{}", scheduleJob, ex);
      return Result.fail("添加job异常");
    }
  }

  @RequestMapping("/stopjobbyname")
  public Result<?> stopJobByName() {
    scheduleJobManage.stopJob("testJob", "DEFAULT");
    return Result.success("成功停止job");
  }

  @RequestMapping("/restartjob")
  public Result<?> restartJob() {
    scheduleJobManage.restartJob("testJob", "DEFAULT");
    return Result.success("恢复job成功");
  }

  @RequestMapping("/deletejob")
  public Result<?> deleteJob() {
    scheduleJobManage.deleteJob("MyJob", "MyJob_Group");
    return Result.success("删除Job成功");
  }

  /** 暂停所有job */
  @RequestMapping("/pauseall")
  public Result<?> stopAll() {
    scheduleJobManage.pauseAll();
    return Result.success("暂停所有job,重启项目不启动");
  }

  @RequestMapping("/shutdownall")
  public Result<?> shutdownAll() {
    scheduleJobManage.shutdown();
    return Result.success("已经关闭所有job,重启项目会启动");
  }

  /** 恢复所有job */
  @RequestMapping("/stratall")
  public Result<?> restartAll() {
    scheduleJobManage.restartAll();
    return Result.success("恢复所有job");
  }
}
