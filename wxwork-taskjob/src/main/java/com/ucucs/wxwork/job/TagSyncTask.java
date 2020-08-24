package com.ucucs.wxwork.job;

import com.ucucs.wxwork.service.TagService;
import lombok.AllArgsConstructor;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TagSyncTask extends JobTask {

  private final TagService tagService;

  @Override
  public void executeTask(JobExecutionContext jobExecutionContext) {
    logger.info("正在同步公司标签");
    tagService.syncTag();
  }
}
