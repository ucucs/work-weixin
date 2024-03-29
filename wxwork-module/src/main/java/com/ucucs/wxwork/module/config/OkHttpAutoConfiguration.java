package com.ucucs.wxwork.module.config;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OkHttpProperties.class)
@ConditionalOnClass({OkHttpClient.class})
public class OkHttpAutoConfiguration {

  private final OkHttpProperties okHttpProperties;

  public OkHttpAutoConfiguration(OkHttpProperties okHttpProperties) {
    this.okHttpProperties = okHttpProperties;
  }

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory(), x509TrustManager())
        .retryOnConnectionFailure(false)
        .connectionPool(connectionPool())
        .connectTimeout(okHttpProperties.getConnectTimeout(), TimeUnit.SECONDS)
        .readTimeout(okHttpProperties.getReadTimeout(), TimeUnit.SECONDS)
        .writeTimeout(okHttpProperties.getWriteTimeout(), TimeUnit.SECONDS)
        .hostnameVerifier((hostname, session) -> true)
        .build();
  }

  @Bean
  public X509TrustManager x509TrustManager() {
    return new X509TrustManager() {
      @Override
      public void checkClientTrusted(X509Certificate[] chain, String authType) {}

      @Override
      public void checkServerTrusted(X509Certificate[] chain, String authType) {}

      @Override
      public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
      }
    };
  }

  @Bean
  public SSLSocketFactory sslSocketFactory() {
    try {
      // 信任任何链接
      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, new TrustManager[] {x509TrustManager()}, new SecureRandom());
      return sslContext.getSocketFactory();
    } catch (NoSuchAlgorithmException | KeyManagementException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Bean
  public ConnectionPool connectionPool() {
    return new ConnectionPool(
        okHttpProperties.getMaxIdleConnections(),
        okHttpProperties.getKeepAliveDuration(),
        TimeUnit.SECONDS);
  }
}
