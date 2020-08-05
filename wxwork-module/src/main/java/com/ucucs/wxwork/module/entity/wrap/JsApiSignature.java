package com.ucucs.wxwork.module.entity.wrap;

import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class JsApiSignature {

  private String appId;

  private String nonceStr;

  private long timestamp;

  private String url;

  private String signature;
}
