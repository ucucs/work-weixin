package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.entity.Result;
import com.ucucs.wxwork.module.entity.WxExternalContactDetail;
import com.ucucs.wxwork.module.entity.WxExternalGroupChatDetail;
import com.ucucs.wxwork.module.entity.wrap.GroupChatStatus;
import com.ucucs.wxwork.module.entity.wrap.WxExternalUnAssignPage;
import com.ucucs.wxwork.service.ExternalContactService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
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

  @GetMapping("/detailChat")
  public Result<?> getGroupChatDetail(@RequestParam String chatId) {
    Assert.hasText(chatId, "群组ID不能为空");

    WxExternalGroupChatDetail detail = externalContactService.getGroupChatDetail(chatId);
    return Result.success(detail);
  }

  @PostMapping("/transferContact")
  public Result<?> transferContact(
      @RequestParam String userId,
      @RequestParam String externalUserId,
      @RequestParam List<String> addTagIds,
      @RequestParam List<String> removeTagIds) {
    Assert.hasText(userId, "用户ID不能为空");
    Assert.hasText(externalUserId, "外部联系人ID不能为空");

    Assert.isTrue(
        CollectionUtils.isEmpty(addTagIds) && CollectionUtils.isEmpty(removeTagIds),
        "标记和移除的标签不能同时为空");

    externalContactService.markUserWithTag(userId, externalUserId, addTagIds, removeTagIds);
    return Result.success();
  }
}
