package com.ucucs.wxwork.service;

import com.ucucs.wxwork.mapper.WxWorkUserDetailMapper;
import com.ucucs.wxwork.mapper.WxWorkUserMapper;
import com.ucucs.wxwork.mapper.ext.ExtWxWorkUserPartyMapper;
import com.ucucs.wxwork.model.WxWorkUser;
import com.ucucs.wxwork.model.WxWorkUserDetail;
import com.ucucs.wxwork.model.WxWorkUserParty;
import com.ucucs.wxwork.module.entity.WxUserDetail;
import com.ucucs.wxwork.module.service.WxUserService;
import com.ucucs.wxwork.module.util.BeanMapper;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

  private final WxUserService wxUserService;

  private final WxWorkUserMapper wxWorkUserMapper;
  private final WxWorkUserDetailMapper wxWorkUserDetailMapper;
  private final ExtWxWorkUserPartyMapper extWxWorkUserPartyMapper;

  public List<WxUserDetail> getUserList(Long partyId, Boolean fetchChild) {
    return wxUserService.list(partyId, fetchChild);
  }

  public void syncUser() {
    List<WxUserDetail> userDetailList = getUserList(1L, true);

    WxWorkUser workUser;
    WxWorkUserDetail workDetail;
    for (WxUserDetail wxDetail : userDetailList) {
      workUser = BeanMapper.mapper(wxDetail, WxWorkUser.class);
      workDetail = BeanMapper.mapper(wxDetail, WxWorkUserDetail.class);

      // 成员对外属性
      String externalProfileJson = JsonUtil.toJson(wxDetail.getExternalProfile());
      workDetail.setExternalProfile(externalProfileJson);

      // 拓展属性
      String extAttrJson = JsonUtil.toJson(wxDetail.getExtAttr());
      workDetail.setExtAttr(extAttrJson);

      // 分管的部门
      List<WxWorkUserParty> userPartyList = new ArrayList<>();
      Long[] partyIds = wxDetail.getPartyIds();
      for (int i = 0, partyIdsLength = partyIds.length; i < partyIdsLength; i++) {
        Long partyId = partyIds[i];
        WxWorkUserParty userParty = new WxWorkUserParty();
        userParty.setUserId(wxDetail.getUserId());
        userParty.setPartyId(partyId);
        userParty.setOrder(wxDetail.getOrders()[i]);
        userParty.setChargeLeader(wxDetail.getIsLeaderInParty()[i]);
        userParty.setMainParty(wxDetail.getMainPartyId());
        userPartyList.add(userParty);
      }

      // 判断用户是否存在
      WxWorkUser dbUser = wxWorkUserMapper.selectByPrimaryKey(wxDetail.getUserId());
      if (dbUser != null) {
        wxWorkUserMapper.updateByPrimaryKeySelective(workUser);
      } else {
        wxWorkUserMapper.insertSelective(workUser);
      }

      WxWorkUserDetail dbDetail = wxWorkUserDetailMapper.selectByPrimaryKey(wxDetail.getUserId());
      if (dbDetail != null) {
        wxWorkUserDetailMapper.updateByPrimaryKeySelective(workDetail);
      } else {
        wxWorkUserDetailMapper.insertSelective(workDetail);
      }

      // 移除用户部门关系表,重新插入
      extWxWorkUserPartyMapper.deleteByUserId(wxDetail.getUserId());
      for (WxWorkUserParty userParty : userPartyList) {
        extWxWorkUserPartyMapper.insertSelective(userParty);
      }
    }
  }
}
