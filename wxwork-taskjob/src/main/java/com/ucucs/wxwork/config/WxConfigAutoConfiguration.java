package com.ucucs.wxwork.config;

import com.ucucs.wxwork.module.service.WxConfigManage;
import com.ucucs.wxwork.service.impl.WxValueConfigManageImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * coding.
 *
 * @author ucucs.
 */
@Configuration
@EnableConfigurationProperties(WxConfigProperties.class)
@ConditionalOnClass({WxConfigManage.class})
public class WxConfigAutoConfiguration {

  private final WxConfigProperties wxConfig;

  public WxConfigAutoConfiguration(WxConfigProperties wxConfig) {
    this.wxConfig = wxConfig;
  }

  @Bean
  public WxConfigManage wxConfigManage() {
    return new WxValueConfigManageImpl(wxConfig);
  }
}
