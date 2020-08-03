package com.ucucs.wxwork.module.service;

import com.ucucs.wxwork.module.entity.WxUser;
import java.util.List;
import java.util.Map;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxUserService {

  List<WxUser> list(Long partyId, Boolean fetchChild, Integer status);

  List<WxUser> listSimple(Long partyId, Boolean fetchChild, Integer status);

  void create(WxUser user);

  void update(WxUser user);

  void delete(String... userIds);

  WxUser getById(String userId);

  String getUserId(String mobile);

  Map<String, String> userIdToOpenId(String userId, Integer agentId);

  String openIdToUserId(String openId);
}
