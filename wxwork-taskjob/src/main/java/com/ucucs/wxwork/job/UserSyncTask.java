package com.ucucs.wxwork.job;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserSyncTask implements JobTask {

  @Scheduled(cron = "0/30 * * * * ?")
  @Override
  public void executeTask() {

  }
}
