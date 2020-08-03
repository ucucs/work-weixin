package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ucucs.wxwork.core.util.MapBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxParty implements WxBodyConvert {

  private Long id;
  private String name;
  private String enName;
  private Long parentId;
  private Long order;

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
    List<WxParty> partyList = new ArrayList<>();
    partyListNode.forEach(partyNode -> partyList.add(parsePartyNode(partyNode)));
    return partyList;
  }

  public static WxParty parsePartyNode(JsonNode partyNode) {
    WxParty party = new WxParty();
    party.setId(partyNode.get("id").asLong());
    party.setName(partyNode.get("name").asText());
    party.setEnName(partyNode.get("name_en").asText());
    party.setParentId(partyNode.get("parentid").asLong());
    party.setOrder(partyNode.get("order").asLong());
    return party;
  }
}
