package com.ucucs.wxwork.module.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class ExternalAttr {

  /** 属性类型: 0-本文 1-网页 2-小程序. */
  private int type;
  /** 属性名称： 需要先确保在管理端有创建改属性，否则会忽略. */
  private String name;
  /** 文本属性内容,长度限制12个UTF8字符. */
  private String value;
  /** 网页的url,必须包含http或者https头. */
  private String url;
  /** 小程序的展示标题,长度限制12个UTF8字符. 或者 网页的展示标题,长度限制12个UTF8字符 */
  private String title;
  /** 小程序appid，必须是有在本企业安装授权的小程序，否则会被忽略. */
  private String appid;
  /** 小程序的页面路径. */
  private String pagePath;
}
