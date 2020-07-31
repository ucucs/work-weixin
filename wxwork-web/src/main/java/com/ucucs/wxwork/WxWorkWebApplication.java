package com.ucucs.wxwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ucucs.wxwork")
public class WxWorkWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(WxWorkWebApplication.class, args);
  }
}
