package com.ucucs.wxwork.entity.param.user;

import com.ucucs.wxwork.entity.param.ValidParam;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class UserUpdateParam implements ValidParam {

  private String userId;
  private String name;
  private Long[] partyIds;
  private Integer[] orders;
  private String position;
  private String mobile;
  private Integer gender;
  private String email;
  private String address;
  private String avatarMediaId;
  private Integer enable;
  private Boolean toInvite;
  private String alias;
  private Integer[] isLeaderInParty;
  private Integer hideMobile;
  private String englishName;
  private String telephone;
  private String externalPosition;
  private Map<String, Object> externalProfile;
  private String mainPartyId;

  @Override
  public void validParam() {
    Assert.hasLength(userId, "用户Id不能为空");
    Assert.hasLength(name, "成员名称不能为空");
  }
}
