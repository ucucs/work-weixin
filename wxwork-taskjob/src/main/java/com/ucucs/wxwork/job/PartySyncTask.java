package com.ucucs.wxwork.job;

import com.ucucs.wxwork.service.PartyService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PartySyncTask implements JobTask {

  private final PartyService partyService;

  @Async
  @Scheduled(cron = "0/30 * * * * ?")
  @Override
  public void executeTask() {
    partyService.syncParty();
  }
}
