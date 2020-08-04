package com.ucucs.wxwork.service;

import com.ucucs.wxwork.module.entity.WxUser;
import com.ucucs.wxwork.module.entity.WxUserDetail;
import com.ucucs.wxwork.module.service.WxUserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class UserService {

  private final WxUserService wxUserService;

  public List<WxUserDetail> list(Long partyId, Boolean fetchChild) {
    return wxUserService.list(partyId, fetchChild);
  }

  public List<WxUserDetail> listSimple(Long partyId, Boolean fetchChild) {
    return wxUserService.listSimple(partyId, fetchChild);
  }

  public void create(WxUser wxUser) {
    wxUserService.create(wxUser);
  }

  public void update(WxUser wxUser) {
    wxUserService.update(wxUser);
  }

  public void delete(String... userIds) {
    wxUserService.delete(userIds);
  }

  public WxUser getById(String userId) {
    return wxUserService.getById(userId);
  }

  public String userIdToOpenId(String userId) {
    return wxUserService.userIdToOpenId(userId);
  }
}
