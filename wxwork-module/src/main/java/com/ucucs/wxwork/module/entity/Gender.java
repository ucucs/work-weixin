package com.ucucs.wxwork.module.entity;

import lombok.Getter;

@Getter
public enum Gender {
  /** 男. */
  MALE("男", "1"),
  /** 女. */
  FEMALE("女", "2");

  private final String genderName;
  private final String code;

  Gender(String genderName, String code) {
    this.genderName = genderName;
    this.code = code;
  }

  public static Gender fromCode(String code) {
    if ("1".equals(code)) {
      return Gender.MALE;
    }
    if ("2".equals(code)) {
      return Gender.FEMALE;
    }

    return null;
  }
}
