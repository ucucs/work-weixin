package com.ucucs.wxwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.ucucs.wxwork.mapper")
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.ucucs.wxwork")
public class WxWorkTaskJobApplication {

  public static void main(String[] args) {
    SpringApplication.run(WxWorkTaskJobApplication.class, args);
  }
}
