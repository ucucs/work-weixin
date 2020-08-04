package com.ucucs.wxwork.module.service;

import com.ucucs.wxwork.module.entity.UserSimple;
import com.ucucs.wxwork.module.entity.WxUser;
import com.ucucs.wxwork.module.entity.WxUserDetail;
import java.util.List;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxUserService {

  List<WxUserDetail> list(Long partyId, Boolean fetchChild);

  List<UserSimple> listSimple(Long partyId, Boolean fetchChild);

  void create(WxUser user);

  void update(WxUser user);

  void delete(String... userIds);

  WxUser getById(String userId);

  String userIdToOpenId(String userId);
}
