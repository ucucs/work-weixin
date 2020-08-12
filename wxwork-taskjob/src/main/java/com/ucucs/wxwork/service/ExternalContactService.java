package com.ucucs.wxwork.service;

import com.ucucs.wxwork.model.WxWorkExternalContact;
import com.ucucs.wxwork.model.WxWorkExternalFollow;
import com.ucucs.wxwork.module.entity.FollowUser;
import com.ucucs.wxwork.module.entity.UserSimple;
import com.ucucs.wxwork.module.entity.WxExternalContactDetail;
import com.ucucs.wxwork.module.service.WxExternalContactService;
import com.ucucs.wxwork.module.util.BeanMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExternalContactService {

  private final WxExternalContactService wxExternalContactService;
  private final UserService userService;

  public List<String> getContactList(String userId) {
    return wxExternalContactService.list(userId);
  }

  public WxExternalContactDetail getContactDetail(String externalUserId) {
    return wxExternalContactService.getContactDetail(externalUserId);
  }

  public void syncContact() {
    // 获取所有人员
    List<UserSimple> userList = userService.getUserSimpleList(1L, true);
    List<String> externalUserIds;
    WxExternalContactDetail contactDetail;
    for (UserSimple user : userList) {
      // 根据用户ID去获取外部联系人列表
      externalUserIds = getContactList(user.getUserId());
      for(String externalUserId:externalUserIds) {
         contactDetail = getContactDetail(externalUserId);

         //写入到数据库
        WxWorkExternalContact contact = BeanMapper.mapper(contactDetail,WxWorkExternalContact.class);

        List<WxWorkExternalFollow> follows = new ArrayList<>();
        for(FollowUser followUser:contactDetail.getFollowedUsers()) {
          WxWorkExternalFollow follow = BeanMapper
        }
      }
    }
}
