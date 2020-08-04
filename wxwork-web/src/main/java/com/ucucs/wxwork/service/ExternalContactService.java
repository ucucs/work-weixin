package com.ucucs.wxwork.service;

import com.ucucs.wxwork.module.entity.WxExternalContactDetail;
import com.ucucs.wxwork.module.entity.wrap.GroupChatStatus;
import com.ucucs.wxwork.module.entity.wrap.WxExternalUnAssignPage;
import com.ucucs.wxwork.module.service.WxExternalContactService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExternalContactService {

  private final WxExternalContactService wxExternalContactService;

  public List<String> list(String userId) {
    return wxExternalContactService.list(userId);
  }

  public WxExternalUnAssignPage listUnAssigned(Integer pageIndex, Integer pageSize) {
    return wxExternalContactService.listUnAssigned(pageIndex, pageSize);
  }

  public List<GroupChatStatus> listGroupChat(
      Integer pageIndex, Integer pageSize, Integer status, String[] userIds, String[] partyIds) {
    return wxExternalContactService.listGroupChat(pageIndex, pageSize, status, userIds, partyIds);
  }

  public void transferContact(String externalUserId, String handOverUserId, String takeOverUserId) {
    wxExternalContactService.transferContact(externalUserId, handOverUserId, takeOverUserId);
  }

  public void transferGroupChat(String[] groupChatIds, String takeOverUserId) {
    wxExternalContactService.transferGroupChat(groupChatIds, takeOverUserId);
  }

  public WxExternalContactDetail getContactDetail(String externalUserId) {
    return wxExternalContactService.getContactDetail(externalUserId);
  }
}
