package com.ucucs.wxwork.module.service;

import com.ucucs.wxwork.module.entity.wrap.JsApiSignature;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxJsApiService {

  String getTicket(boolean forceRefresh);

  String getAgentTicket(boolean forceRefresh);

  JsApiSignature createSignature(String url);
}
