package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxParty implements WxBodyConvert {

  private Long id;
  private String name;

  @JsonAlias("name_en")
  private String enName;

  @JsonAlias("parentid")
  private Long parentId;
  private Integer order;

  @Override
  public Map<String, Object> wrapMsgBody() {
    MapBuilder<String, Object> body = new MapBuilder<>();
    return body.put("id", id)
        .put("name", name)
        .put("name_en", enName)
        .put("parentid", parentId)
        .put("order", order)
        .build();
  }

  public static List<WxParty> parseMsgBody(JsonNode msgNode) {
    ArrayNode partyListNode = msgNode.withArray("department");
    return JsonUtil.nodeToBeanList(partyListNode, WxParty.class);
  }
}
