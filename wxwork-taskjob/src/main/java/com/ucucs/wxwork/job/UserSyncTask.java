package com.ucucs.wxwork.job;

import com.ucucs.wxwork.service.UserService;
import lombok.AllArgsConstructor;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserSyncTask extends JobTask {

  private final UserService userService;

  @Override
  public void executeTask(JobExecutionContext jobExecutionContext) {
    logger.info("正在同步部门人员");
    userService.syncUser();
  }
}
