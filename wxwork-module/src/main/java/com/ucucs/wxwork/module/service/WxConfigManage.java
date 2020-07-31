package com.ucucs.wxwork.module.service;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxConfigManage {

  String getApiUrl(String path);

  String getTypeSecret(String category);

  String getCorpId();

  Integer getAgentId();

  String getNotifyToken();

  String getNotifyAesKey();
}
