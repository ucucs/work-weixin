package com.ucucs.wxwork.module.entity.wrap;

import com.fasterxml.jackson.annotation.JsonProperty;
<<<<<<< HEAD
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ucucs.wxwork.module.util.JsonUtil;
=======
import com.ucucs.wxwork.module.entity.UnAssignInfo;
>>>>>>> 917a95a906178c57a24a45cf300d1ce78603509b
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class WxExternalUnAssignPage {

  @JsonProperty("info")
  private List<UnAssignInfo> unAssignInfos;

  @JsonProperty("is_last")
  private Boolean lastFlag;
<<<<<<< HEAD

  public static WxExternalUnAssignPage parseMsgBody(JsonNode msgNode) {
    ArrayNode userListNode = msgNode.withArray("info");
    List<UnAssignInfo> unAssignInfos = JsonUtil.nodeToBeanList(userListNode, UnAssignInfo.class);

    Boolean lastFlag = msgNode.get("is_last").asBoolean();

    WxExternalUnAssignPage unAssignPage = new WxExternalUnAssignPage();
    unAssignPage.setUnAssignInfos(unAssignInfos);
    unAssignPage.setLastFlag(lastFlag);
    return unAssignPage;
  }
=======
>>>>>>> 917a95a906178c57a24a45cf300d1ce78603509b
}
