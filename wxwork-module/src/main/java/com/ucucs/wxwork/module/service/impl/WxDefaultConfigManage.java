package com.ucucs.wxwork.module.service.impl;

import com.ucucs.wxwork.core.constant.ApiPathConsts;
import com.ucucs.wxwork.module.service.WxConfigManage;
import java.util.Map;

/**
 * coding.
 *
 * @author ucucs.
 */
public class WxDefaultConfigManage implements WxConfigManage {

  protected String baseUrl;
  protected String corpId;
  protected Integer agentId;
  protected String notifyToken;
  protected String notifyAesKey;
  protected Map<String, String> categorySecrets;

  @Override
  public String getApiUrl(String path) {
    if (baseUrl == null) {
      baseUrl = ApiPathConsts.API_BASE_URL;
    }
    return baseUrl + path;
  }

  @Override
  public String getTypeSecret(String category) {
    return categorySecrets.get(category);
  }

  @Override
  public String getCorpId() {
    return corpId;
  }

  @Override
  public Integer getAgentId() {
    return agentId;
  }

  @Override
  public String getNotifyToken() {
    return notifyToken;
  }

  @Override
  public String getNotifyAesKey() {
    return notifyAesKey;
  }
}
