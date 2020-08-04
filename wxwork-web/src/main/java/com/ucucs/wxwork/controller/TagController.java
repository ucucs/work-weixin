package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.entity.Result;
import com.ucucs.wxwork.module.entity.WxTag;
import com.ucucs.wxwork.module.entity.wrap.TagOperateResult;
import com.ucucs.wxwork.module.entity.wrap.TagUserInfo;
import com.ucucs.wxwork.service.TagService;
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
@RequestMapping("/tag")
public class TagController {

  private final TagService tagService;

  @GetMapping("/list")
  public Result<?> getTagList() {
    List<WxTag> tagList = tagService.list();
    return Result.success(tagList);
  }

  @PostMapping("/create")
  public Result<?> createTag(@RequestParam Long tagId, @RequestParam String tagName) {
    Assert.hasText(tagName, "标签名称不能为空");
    Long wxTagId = tagService.create(tagId, tagName);
    return Result.success(wxTagId);
  }

  @PostMapping("/update")
  public Result<?> updateTag(@RequestParam Long tagId, @RequestParam String tagName) {
    Assert.notNull(tagId, "标签ID不能为空");
    Assert.hasText(tagName, "标签名称不能为空");

    tagService.update(tagId, tagName);
    return Result.success();
  }

  @GetMapping("/delete")
  public Result<?> deleteTag(@RequestParam Long tagId) {
    Assert.notNull(tagId, "标签ID不能为空");

    tagService.delete(tagId);
    return Result.success();
  }

  @GetMapping("/listUsers")
  public Result<?> listTagUsers(@RequestParam Long tagId) {
    Assert.notNull(tagId, "标签ID不能为空");

    TagUserInfo tagUserInfo = tagService.listUsers(tagId);
    return Result.success(tagUserInfo);
  }

  @PostMapping("/addUserToTag")
  public Result<?> addUserToTag(
      @RequestParam Long tagId,
      @RequestParam List<String> userIds,
      @RequestParam List<String> partyIds) {
    Assert.notNull(tagId, "标签ID不能为空");

    Assert.isTrue(
        CollectionUtils.isEmpty(userIds) && CollectionUtils.isEmpty(partyIds), "成员ID和部门ID不能同时为空");

    TagOperateResult operateResult = tagService.addUserToTag(tagId, userIds, partyIds);
    return Result.success(operateResult);
  }

  @PostMapping("/removeUserFromTag")
  public Result<?> removeUserFromTag(
      @RequestParam Long tagId,
      @RequestParam List<String> userIds,
      @RequestParam List<String> partyIds) {
    Assert.notNull(tagId, "标签ID不能为空");

    Assert.isTrue(
        CollectionUtils.isEmpty(userIds) && CollectionUtils.isEmpty(partyIds), "成员ID和部门ID不能同时为空");

    TagOperateResult operateResult = tagService.removeUserFromTag(tagId, userIds, partyIds);
    return Result.success(operateResult);
  }
}
