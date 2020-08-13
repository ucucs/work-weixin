package com.ucucs.wxwork.service;

import com.ucucs.wxwork.mapper.WxWorkExternalContactMapper;
import com.ucucs.wxwork.model.WxWorkExternalContact;
import com.ucucs.wxwork.module.entity.UserSimple;
import com.ucucs.wxwork.module.entity.WxExternalContactDetail;
import com.ucucs.wxwork.module.service.WxExternalContactService;
import com.ucucs.wxwork.module.util.BeanMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExternalContactService {

  private static final Logger LOG = LoggerFactory.getLogger(ExternalContactService.class);

  private final WxExternalContactService wxExternalContactService;
  private final UserService userService;
  private final WxWorkExternalContactMapper wxWorkExternalContactMapper;

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
      try {
        // 根据用户ID去获取外部联系人列表
        externalUserIds = getContactList(user.getUserId());
        for (String externalUserId : externalUserIds) {
          contactDetail = getContactDetail(externalUserId);

          // 写入到数据库
          WxWorkExternalContact contact =
              BeanMapper.mapper(contactDetail, WxWorkExternalContact.class);

          //        List<WxWorkExternalFollow> follows =
          // BeanMapper.mapperList(contactDetail.getFollowedUsers(),WxWorkExternalFollow.class,(item)->{
          //          item.setCreateTime(new Date(followUser.getCreateTime()));
          //        });

          WxWorkExternalContact dbContact =
              wxWorkExternalContactMapper.selectByPrimaryKey(contact.getExternalUserId());
          if (dbContact != null) {
            wxWorkExternalContactMapper.updateByPrimaryKeySelective(contact);
          } else {
            wxWorkExternalContactMapper.insertSelective(contact);
          }
        }
      } catch (Exception ex) {
        LOG.error("企业", ex);
      }
    }
  }
}
