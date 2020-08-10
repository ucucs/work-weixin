package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.entity.ScheduleJob;
import com.ucucs.wxwork.service.ScheduleJobManageService;
import lombok.AllArgsConstructor;
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

  private final ScheduleJobManageService scheduleJobManage;

  @RequestMapping("/addjob")
  public void addJob() {
    ScheduleJob j = new ScheduleJob();

    j.setConcurrent(false);
    j.setCronExpression("0 0/1 * * * ?");
    j.setJobName("MyJob");
    j.setJobGroup("MyJob_Group");
    j.setJobStatus("1");
    j.setParameters(null);
    j.setJobClass("com.ucucs.wxwork.job.MyJob");
    j.setRequestsRecovery(true);
    j.setDruable(true);
    j.setUpdateData(true);
    j.setDescription("54645654646464.");
    try {
      scheduleJobManage.initJob(j);
      System.out.println("添加job成功!");
    } catch (Exception e) {
      System.out.println("添加job异常!");
      e.printStackTrace();
    }
  }

  @RequestMapping("/stopjobbyname")
  public void stopJobByName() {
    scheduleJobManage.stopJob("testJob", "DEFAULT");
    System.out.println("成功停止job");
    return;
  }

  @RequestMapping("/restartjob")
  public void restartJob() {
    scheduleJobManage.restartJob("testJob", "DEFAULT");
    System.out.println("恢复job");
    return;
  }

  @RequestMapping("/deletejob")
  public void deleteJob() {
    scheduleJobManage.deleteJob("MyJob", "MyJob_Group");
  }

  /** 暂停所有job */
  @RequestMapping("/pauseall")
  public void stopAll() {
    scheduleJobManage.pauseAll();
    System.out.println("暂停所有job,重启项目不启动");
    return;
  }

  @RequestMapping("/shutdownall")
  public void shutdownAll() {
    scheduleJobManage.shutdown();
    System.out.println("已经关闭所有job,重启项目会启动");
    return;
  }

  /** 恢复所有job */
  @RequestMapping("/stratall")
  public void restartAll() {
    scheduleJobManage.restartAll();
    System.out.println("恢复所有job");
    return;
  }
}
