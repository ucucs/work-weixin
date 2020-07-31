package com.ucucs.wxwork.core.constant;

/**
 * coding.
 *
 * @author ucucs.
 */
public class WxWorkConsts {

  public static class TokenType {
    public static final String CORP = "corp";
    public static final String CONTACT = "contact";
    public static final String CUSTOMER = "customer";
    public static final String APP = "app";
    public static final String MINI_APP = "miniapp";
  }

  public static class RspStatus {
    /** 微信接口返回成功状态码. */
    public static String SUCCESS = "0";
  }

  public enum RequestType {
    GET,
    POST_JSON,
    POST_XML
  }
}
