package com.ucucs.wxwork.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PartySyncTask implements JobTask {

  @Scheduled(cron = "0 0/2 * * * ?")
  @Override
  public void executeTask() {


  }
}
