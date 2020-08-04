package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.entity.Result;
import com.ucucs.wxwork.entity.param.user.UserCreateParam;
import com.ucucs.wxwork.entity.param.user.UserUpdateParam;
import com.ucucs.wxwork.module.entity.UserSimple;
import com.ucucs.wxwork.module.entity.WxUser;
import com.ucucs.wxwork.module.entity.WxUserDetail;
import com.ucucs.wxwork.module.util.BeanMapper;
import com.ucucs.wxwork.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/list")
  public Result<?> getUserList(
      @RequestParam Long partyId,
      @RequestParam(required = false, defaultValue = "0") Integer fetchChild) {
    Assert.notNull(partyId, "部门ID不能为空");
    List<WxUserDetail> userList = userService.list(partyId, fetchChild == 1);
    return Result.success(userList);
  }

  @GetMapping("/listSimple")
  public Result<?> getUserSimpleList(
      @RequestParam Long partyId,
      @RequestParam(required = false, defaultValue = "0") Integer fetchChild) {
    Assert.notNull(partyId, "部门ID不能为空");
    List<UserSimple> userList = userService.listSimple(partyId, fetchChild == 1);
    return Result.success(userList);
  }

  @PostMapping("/create")
  public Result<?> createUser(@RequestBody UserCreateParam userParam) {
    WxUser user = BeanMapper.mapper(userParam, WxUser.class);
    userService.create(user);
    return Result.success();
  }

  @PostMapping("/update")
  public Result<?> updateUser(@RequestBody UserUpdateParam userParam) {
    WxUser user = BeanMapper.mapper(userParam, WxUser.class);
    userService.update(user);
    return Result.success();
  }

  @RequestMapping("/delete")
  public Result<?> deleteUser(@RequestParam String[] userIds) {
    Assert.notEmpty(userIds, "用户ID不能为空");
    userService.delete(userIds);
    return Result.success();
  }

  @GetMapping("/getById")
  public Result<?> getById(@RequestParam String userId) {
    Assert.hasText(userId, "用户ID不能为空");
    WxUser user = userService.getById(userId);
    return Result.success(user);
  }

  @GetMapping("/toOpenId")
  public Result<?> userIdToOpenId(@RequestParam String userId) {
    Assert.hasText(userId, "用户ID不能为空");
    String openId = userService.userIdToOpenId(userId);
    return Result.success(openId);
  }
}
