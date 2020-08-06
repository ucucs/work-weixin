package com.ucucs.wxwork.mapper;

import com.ucucs.wxwork.model.WxWorkParty;
import java.io.Serializable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxWorkPartyMapper extends Serializable {
    int deleteByPrimaryKey(Long id);

    int insert(WxWorkParty record);

    int insertSelective(WxWorkParty record);

    WxWorkParty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxWorkParty record);

    int updateByPrimaryKey(WxWorkParty record);
}