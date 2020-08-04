package com.ucucs.wxwork.module.service;

<<<<<<< HEAD
import com.ucucs.wxwork.module.entity.UserSimple;
=======
>>>>>>> 917a95a906178c57a24a45cf300d1ce78603509b
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

<<<<<<< HEAD
  List<UserSimple> listSimple(Long partyId, Boolean fetchChild);
=======
  List<WxUserDetail> listSimple(Long partyId, Boolean fetchChild);
>>>>>>> 917a95a906178c57a24a45cf300d1ce78603509b

  void create(WxUser user);

  void update(WxUser user);

  void delete(String... userIds);

  WxUser getById(String userId);

  String userIdToOpenId(String userId);
}
