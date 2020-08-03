package com.ucucs.wxwork.module.service.impl;

import com.ucucs.wxwork.core.constant.WxWorkConsts.TokenType;
import com.ucucs.wxwork.module.entity.WxUser;
import com.ucucs.wxwork.module.service.WxUserService;
import com.ucucs.wxwork.module.service.WxWorkService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class WxUserServiceImpl implements WxUserService {

  private final WxWorkService wxWorkService;

  private String getAccessToken() {
    return wxWorkService.getAccessToken(TokenType.CONTACT);
  }

  @Override
  public List<WxUser> list(Long partyId, Boolean fetchChild, Integer status) {
    return null;
  }

  @Override
  public List<WxUser> listSimple(Long partyId, Boolean fetchChild, Integer status) {
    return null;
  }

  @Override
  public void create(WxUser user) {}

  @Override
  public void update(WxUser user) {}

  @Override
  public void delete(String... userIds) {}

  @Override
  public WxUser getById(String userId) {
    return null;
  }

  @Override
  public String getUserId(String mobile) {
    return null;
  }

  @Override
  public Map<String, String> userIdToOpenId(String userId, Integer agentId) {
    return null;
  }

  @Override
  public String openIdToUserId(String openId) {
    return null;
  }
}
