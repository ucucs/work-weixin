package com.ucucs.wxwork.module.service;

import com.ucucs.wxwork.module.entity.wrap.CodeToSessionResult;

/**
 * coding.
 *
 * @author ucucs.
 */
public interface WxMiniProgramService {

  CodeToSessionResult jsCodeToSession(String jsCode);
}
