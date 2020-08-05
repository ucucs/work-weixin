package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.entity.Result;
import com.ucucs.wxwork.module.entity.wrap.JsApiSignature;
import com.ucucs.wxwork.service.JsApiService;
import lombok.AllArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/jsapi")
public class JsApiController {

  private final JsApiService jsApiService;

  @GetMapping("/ticket")
  public Result<?> getTicket(
      @RequestParam(required = false, defaultValue = "false") Boolean forceRefresh) {
    String ticket = jsApiService.getTicket(forceRefresh);
    return Result.success(ticket);
  }

  @GetMapping("/ticketAgent")
  public Result<?> getAgentTicket(
      @RequestParam(required = false, defaultValue = "false") Boolean forceRefresh) {
    String ticket = jsApiService.getAgentTicket(forceRefresh);
    return Result.success(ticket);
  }

  @GetMapping("/signature")
  public Result<?> createSignature(@RequestParam String url) {
    Assert.hasText(url, "链接地址不能为空");

    JsApiSignature signature = jsApiService.createSignature(url);
    return Result.success(signature);
  }
}
