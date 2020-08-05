package com.ucucs.wxwork.module.service;

import com.ucucs.wxwork.module.entity.WxExternalContactDetail;
import com.ucucs.wxwork.module.entity.WxExternalGroupChatDetail;
import com.ucucs.wxwork.module.entity.wrap.GroupChatStatus;
import com.ucucs.wxwork.module.entity.wrap.WxExternalUnAssignPage;
import java.util.List;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxExternalContactService {

  List<String> list(String userId);

  WxExternalUnAssignPage listUnAssigned(Integer pageIndex, Integer pageSize);

  List<GroupChatStatus> listGroupChat(
      Integer pageIndex, Integer pageSize, Integer status, String[] userIds, String[] partyIds);

  void transferContact(String externalUserId, String handOverUserId, String takeOverUserId);

  void transferGroupChat(String[] groupChatIds, String takeOverUserId);

  WxExternalContactDetail getContactDetail(String externalUserId);

  WxExternalGroupChatDetail getGroupChatDetail(String chatId);

  void markUserWithTag(
      String userId, String externalUserId, List<String> addTagIds, List<String> removeTagIds);
}
