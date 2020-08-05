package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class GroupChatMember {

  @JsonAlias("userid")
  private String userId;

  @JsonAlias("type")
  private int type;

  @JsonAlias("join_time")
  private Long joinTime;

  @JsonAlias("join_scene")
  private Integer joinScene;

  @JsonAlias("unionid")
  private String unionId;
}
