package com.ucucs.wxwork.module.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** OKHTTP连接配置. */
@Getter
@Setter
@ConfigurationProperties(prefix = "ok.http")
public class OkHttpProperties {

  /** 建立连接超时,单位秒. */
  private Integer connectTimeout = 10;

  /** 响应读取超时,单位秒. */
  private Integer readTimeout = 30;

  /** 写入数据超时.单位秒. */
  private Integer writeTimeout = 30;

  /** 最大空闲连接数. */
  private Integer maxIdleConnections = 5;

  /** 连接存活时间,单位秒. */
  private Long keepAliveDuration = 300L;
}
