package com.ucucs.wxwork.job;

import com.ucucs.wxwork.service.PartyService;
import lombok.AllArgsConstructor;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PartySyncTask extends JobTask {

  private final PartyService partyService;

  @Override
  public void executeTask(JobExecutionContext jobExecutionContext) {
    logger.info("正在同步组织架构");
    partyService.syncParty();
  }
}
