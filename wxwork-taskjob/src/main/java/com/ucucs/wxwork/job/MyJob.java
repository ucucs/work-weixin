package com.ucucs.wxwork.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * coding.
 *
 * @author ucucs.
 */
@DisallowConcurrentExecution
public class MyJob extends QuartzJobBean {

  private static final Logger LOG = LoggerFactory.getLogger(MyJob.class);

  @Override
  protected void executeInternal(JobExecutionContext jobExecutionContext) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    LOG.info("JOB运行了一次 :" + sdf.format(new Date()));
  }
}
