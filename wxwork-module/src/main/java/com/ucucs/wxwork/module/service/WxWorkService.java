package com.ucucs.wxwork.module.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ucucs.wxwork.core.constant.WxWorkConsts.RequestType;
import java.util.Map;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxWorkService {

  String getAccessToken(String category);

  JsonNode getRsp(
      String urlPath, Map<String, Object> urlParams, Object bodyObject, RequestType requestType);
}
