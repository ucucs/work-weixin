package com.ucucs.wxwork.module.component;

import com.ucucs.wxwork.module.util.HttpUtil;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OkHttpComponent {

  private static final Logger logger = LoggerFactory.getLogger(OkHttpComponent.class);

  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");

  private final OkHttpClient okHttpClient;

  public OkHttpComponent(OkHttpClient okHttpClient) {
    this.okHttpClient = okHttpClient;
  }

  /**
   * get 请求.
   *
   * @param url 请求url地址
   * @return string
   */
  public String doGet(String url) {
    return doGet(url, null, null);
  }

  /**
   * get 请求.
   *
   * @param url 请求url地址
   * @param params 请求参数 map
   * @return string
   */
  public String doGetWithParam(String url, Map<String, Object> params) {
    return doGet(url, params, null);
  }

  /**
   * get 请求.
   *
   * @param url 请求url地址
   * @param headers 请求头字段 {k1, v1 k2, v2, ...}
   * @return string
   */
  public String doGetWithHeader(String url, Map<String, String> headers) {
    return doGet(url, null, headers);
  }

  /**
   * get 请求.
   *
   * @param url 请求url地址
   * @param params 请求参数 map
   * @param headers 请求头字段 {k1, v1 k2, v2, ...}
   * @return string
   */
  public String doGet(String url, Map<String, Object> params, Map<String, String> headers) {
    String reqUrl = buildUrl(url, params);

    Request.Builder builder = new Request.Builder();
    if (headers != null && headers.size() > 0) {
      for (String key : headers.keySet()) {
        builder.addHeader(key, headers.get(key));
      }
    }

    Request request = builder.url(reqUrl).build();
    logger.info("do get request and url[{}]", reqUrl);
    return execute(request);
  }

  /**
   * 构建Get请求的地址.
   *
   * @param url 地址
   * @param params 参数
   * @return 带参数地址
   */
  public String buildUrl(String url, Map<String, Object> params) {
    if (params != null && params.size() > 0) {
      StringBuilder sb = new StringBuilder();
      int pos = 0;
      for (String key : params.keySet()) {
        if (pos > 0) {
          sb.append("&");
        }
        sb.append(String.format("%s=%s", key, HttpUtil.encode(params.get(key).toString())));
        pos++;
      }

      return String.format("%s?%s", url, sb.toString());
    }

    return url;
  }

  /**
   * post 请求.
   *
   * @param url 请求url地址
   * @param params 请求参数 map
   * @return string
   */
  public String doPost(String url, Map<String, String> params) {
    FormBody.Builder builder = new FormBody.Builder();

    if (params != null && params.size() > 0) {
      for (String key : params.keySet()) {
        builder.add(key, params.get(key));
      }
    }
    Request request = new Request.Builder().url(url).post(builder.build()).build();
    logger.info("do post request and url[{}]", url);

    return execute(request);
  }

  /**
   * post 请求, 请求数据为 json 的字符串.
   *
   * @param url 请求url地址
   * @param json 请求数据, json 字符串
   * @return string
   */
  public String doPostJson(String url, String json) {
    logger.info("do post request and url[{}]", url);
    return exectePost(url, json, JSON);
  }

  /**
   * post 请求, 请求数据为 json 的字符串.
   *
   * @param url 请求url地址
   * @param json 请求数据, json 字符串
   * @param params 请求参数, URL参数
   * @return string
   */
  public String doPostJson(String url, String json, Map<String, Object> params) {
    String reqUrl = buildUrl(url, params);
    logger.info("do post request and url[{}]", reqUrl);
    return exectePost(reqUrl, json, JSON);
  }

  /**
   * post 请求, 请求数据为 xml 的字符串.
   *
   * @param url 请求url地址
   * @param xml 请求数据, xml 字符串
   * @return string
   */
  public String doPostXml(String url, String xml) {
    logger.info("do post request and url[{}]", url);
    return exectePost(url, xml, XML);
  }

  private String exectePost(String url, String data, MediaType contentType) {
    RequestBody requestBody = RequestBody.create(contentType, data);
    Request request = new Request.Builder().url(url).post(requestBody).build();

    return execute(request);
  }

  private String execute(Request request) {
    try (Response response = okHttpClient.newCall(request).execute()) {
      if (response.isSuccessful()) {
        return response.body() != null ? response.body().string() : "";
      }
    } catch (Exception ex) {
      logger.error("Execute request error", ex);
    }
    return "";
  }
}
