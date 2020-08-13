package com.ucucs.wxwork.job;

import com.ucucs.wxwork.service.ExternalContactService;
import lombok.AllArgsConstructor;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ExternalContactSyncTask extends JobTask {

  private final ExternalContactService externalContactService;

  @Override
  public void executeTask(JobExecutionContext jobExecutionContext) {
    LOG.info("正在同步外部联系人");
    externalContactService.syncContact();
  }
}
