package com.ucucs.wxwork.module.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ucucs.wxwork.module.util.JsonUtil;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxTag {

  @JsonProperty("tagid")
  private String id;

  @JsonProperty("tagname")
  private String name;

  public static List<WxTag> parseMsgBody(JsonNode msgNode) {
    ArrayNode tagListNode = msgNode.withArray("taglist");
    return JsonUtil.nodeToBeanList(tagListNode, WxTag.class);
  }
}
