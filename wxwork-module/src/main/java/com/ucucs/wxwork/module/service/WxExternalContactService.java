package com.ucucs.wxwork.module.service;

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
}
