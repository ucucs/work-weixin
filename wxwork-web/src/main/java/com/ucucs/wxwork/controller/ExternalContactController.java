package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.entity.Result;
import com.ucucs.wxwork.module.entity.WxExternalContactDetail;
import com.ucucs.wxwork.module.entity.wrap.GroupChatStatus;
import com.ucucs.wxwork.module.entity.wrap.WxExternalUnAssignPage;
import com.ucucs.wxwork.service.ExternalContactService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/externalcontact")
public class ExternalContactController {

  private final ExternalContactService externalContactService;

  @GetMapping("/list")
  public Result<?> getContactList(@RequestParam String userId) {
    Assert.hasText(userId, "用户ID不能为空");
    List<String> contactList = externalContactService.list(userId);
    return Result.success(contactList);
  }

  @GetMapping("/listUnAssigned")
  public Result<?> getUnAssignedList(
      @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
    WxExternalUnAssignPage assignPage = externalContactService.listUnAssigned(pageIndex, pageSize);
    return Result.success(assignPage);
  }

  @GetMapping("/listGroupChat")
  public Result<?> getGroupChatList(
      @RequestParam Integer pageIndex,
      @RequestParam Integer pageSize,
      @RequestParam(required = false) Integer status,
      @RequestParam(required = false) String[] userIds,
      @RequestParam(required = false) String[] partyIds) {
    List<GroupChatStatus> chatList =
        externalContactService.listGroupChat(pageIndex, pageSize, status, userIds, partyIds);
    return Result.success(chatList);
  }

  @PostMapping("/transferContact")
  public Result<?> transferContact(
      @RequestParam String externalUserId,
      @RequestParam String handOverUserId,
      @RequestParam String takeOverUserId) {
    Assert.hasText(externalUserId, "外部联系人ID不能为空");
    Assert.hasText(handOverUserId, "离职成员ID不能为空");
    Assert.hasText(takeOverUserId, "接替成员ID不能为空");

    externalContactService.transferContact(externalUserId, handOverUserId, takeOverUserId);
    return Result.success();
  }

  @PostMapping("/transferGroupChat")
  public Result<?> transferGroupChat(
      @RequestParam String[] groupChatIds, @RequestParam String takeOverUserId) {
    Assert.notEmpty(groupChatIds, "客户群ID不能为空");
    Assert.hasText(takeOverUserId, "接替成员ID不能为空");

    externalContactService.transferGroupChat(groupChatIds, takeOverUserId);
    return Result.success();
  }

  @GetMapping("/detail")
  public Result<?> getContactDetail(@RequestParam String externalUserId) {
    Assert.hasText(externalUserId, "外部联系人ID不能为空");

    WxExternalContactDetail detail = externalContactService.getContactDetail(externalUserId);
    return Result.success(detail);
  }
}
