package com.ucucs.wxwork.job;

import com.ucucs.wxwork.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserSyncTask implements JobTask {

  private final UserService userService;

  @Async
  @Scheduled(cron = "0/30 * * * * ?")
  @Override
  public void executeTask() {
    userService.syncUser();
  }
}
