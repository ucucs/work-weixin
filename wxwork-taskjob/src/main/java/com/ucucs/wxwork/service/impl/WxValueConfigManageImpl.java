package com.ucucs.wxwork.service.impl;

import com.ucucs.wxwork.config.WxConfigProperties;
import com.ucucs.wxwork.module.service.impl.WxDefaultConfigManage;

/**
 * coding.
 *
 * @author ucucs.
 */
public class WxValueConfigManageImpl extends WxDefaultConfigManage {

  public WxValueConfigManageImpl(WxConfigProperties wxConfig) {
    this.agentId = wxConfig.getAgentId();
    this.baseUrl = wxConfig.getBaseUrl();
    this.corpId = wxConfig.getCorpId();
    this.agentId = wxConfig.getAgentId();
    this.notifyToken = wxConfig.getNotifyToken();
    this.notifyAesKey = wxConfig.getNotifyAesKey();
    this.categorySecrets = wxConfig.getCategorySecrets();
  }
}
