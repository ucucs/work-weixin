package com.ucucs.wxwork.service;

import com.ucucs.wxwork.module.entity.wrap.JsApiSignature;
import com.ucucs.wxwork.module.service.WxJsApiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class JsApiService {

  private final WxJsApiService wxJsApiService;

  public String getTicket(boolean forceRefresh) {
    return wxJsApiService.getTicket(forceRefresh);
  }

  public String getAgentTicket(boolean forceRefresh) {
    return wxJsApiService.getAgentTicket(forceRefresh);
  }

  public JsApiSignature createSignature(String url) {
    return wxJsApiService.createSignature(url);
  }
}
