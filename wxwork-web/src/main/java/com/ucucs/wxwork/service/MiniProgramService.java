package com.ucucs.wxwork.service;

import com.ucucs.wxwork.module.entity.wrap.CodeToSessionResult;
import com.ucucs.wxwork.module.service.WxMiniProgramService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * coding.
 *
 * @author ucucs.
 */
@AllArgsConstructor
@Service
public class MiniProgramService {

  private final WxMiniProgramService wxMiniProgramService;

  public CodeToSessionResult jsCodeToSession(String jsCode) {
    return wxMiniProgramService.jsCodeToSession(jsCode);
  }
}
