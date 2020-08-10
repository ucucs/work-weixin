package com.ucucs.wxwork.controller;

import com.ucucs.wxwork.core.entity.Result;
import com.ucucs.wxwork.module.entity.wrap.CodeToSessionResult;
import com.ucucs.wxwork.service.MiniProgramService;
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
@RequestMapping("/miniprogram")
public class MiniProgramController {

  private final MiniProgramService miniProgramService;

  @GetMapping("/codeToSession")
  public Result<?> jsCodeToSession(@RequestParam String jsCode) {
    Assert.hasText(jsCode, "JsCode不能为空");

    CodeToSessionResult session = miniProgramService.jsCodeToSession(jsCode);
    return Result.success(session);
  }
}
