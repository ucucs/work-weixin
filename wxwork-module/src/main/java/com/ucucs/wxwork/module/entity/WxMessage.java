package com.ucucs.wxwork.module.entity;

import static com.ucucs.wxwork.core.constant.MessageConsts.MsgType;

import com.ucucs.wxwork.core.util.MapBuilder;
import com.ucucs.wxwork.module.entity.wrap.Article;
import com.ucucs.wxwork.module.entity.wrap.MpArticle;
import com.ucucs.wxwork.module.entity.wrap.TaskCardButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * coding.
 *
 * @author ucucs.
 */
@Getter
@Setter
public class WxMessage {

  private String toUserIds;
  private String toPartyIds;
  private String toTags;
  private String msgType;
  private Integer agentId;
  private String content;
  private Integer safe;
  private String mediaId;
  private String thumbMediaId;
  private String title;
  private String description;
  private String url;
  private String btnTxt;
  private String appId;
  private String page;
  private Boolean emphasisFirstItem;
  private Map<String, String> contentItems;

  private List<Article> articles = new ArrayList<>();
  private List<MpArticle> mpArticles = new ArrayList<>();

  private String taskId;
  private List<TaskCardButton> taskButtons = new ArrayList<>();

  public Map<String, Object> wrapMsgBody() {
    MapBuilder<String, Object> body = new MapBuilder<>();
    body.put("touser", toUserIds)
        .put("toparty", toPartyIds)
        .put("totag", toTags)
        .put("msgtype", msgType)
        .put("agentid", agentId)
        .put("safe", safe);

    handleMsgType(body);

    return body.build();
  }

  private void handleMsgType(MapBuilder<String, Object> body) {
    switch (this.getMsgType()) {
      case MsgType.TEXT:
        MapBuilder<String, Object> textBuilder = new MapBuilder<>();
        textBuilder.put("content", content);
        body.put("text", textBuilder.build());
        break;
      case MsgType.MARKDOWN:
        MapBuilder<String, Object> markdownBuilder = new MapBuilder<>();
        markdownBuilder.put("content", content);
        body.put("markdown", markdownBuilder.build());
        break;
      case MsgType.TEXTCARD:
        MapBuilder<String, Object> textCardBuilder = new MapBuilder<>();
        textCardBuilder
            .put("title", title)
            .put("description", description)
            .put("url", url)
            .put("btntxt", btnTxt);
        body.put("textcard", textCardBuilder.build());
        break;
      case MsgType.IMAGE:
        MapBuilder<String, Object> imageBuilder = new MapBuilder<>();
        imageBuilder.put("media_id", mediaId);
        body.put("image", imageBuilder.build());
        break;
      case MsgType.FILE:
        MapBuilder<String, Object> fileBuilder = new MapBuilder<>();
        fileBuilder.put("media_id", mediaId);
        body.put("file", fileBuilder.build());
        break;
      case MsgType.VOICE:
        MapBuilder<String, Object> voiceBuilder = new MapBuilder<>();
        voiceBuilder.put("media_id", mediaId);
        body.put("voice", voiceBuilder.build());
        break;
      case MsgType.VIDEO:
        MapBuilder<String, Object> videoBuilder = new MapBuilder<>();
        videoBuilder
            .put("media_id", mediaId)
            .put("thumb_media_id", thumbMediaId)
            .put("title", title)
            .put("description", description);
        body.put("video", videoBuilder.build());
        break;
      case MsgType.NEWS:
        MapBuilder<String, Object> newsBuilder = new MapBuilder<>();

        List<Map<String, Object>> articleList = new ArrayList<>();
        for (Article article : articles) {
          MapBuilder<String, Object> articleBuilder = new MapBuilder<>();
          articleBuilder
              .put("title", article.getTitle())
              .put("description", article.getDescription())
              .put("url", article.getUrl())
              .put("picurl", article.getPicUrl());
          articleList.add(articleBuilder.build());
        }
        newsBuilder.put("articles", articleList);

        body.put("news", newsBuilder.build());
        break;
      case MsgType.MPNEWS:
        MapBuilder<String, Object> mpNewsBuilder = new MapBuilder<>();

        List<Map<String, Object>> mpArticleList = new ArrayList<>();
        for (MpArticle article : mpArticles) {
          MapBuilder<String, Object> articleBuilder = new MapBuilder<>();
          articleBuilder
              .put("title", article.getTitle())
              .put("thumb_media_id", article.getThumbMediaId())
              .put("author", article.getAuthor())
              .put("content_source_url", article.getContentSourceUrl())
              .put("content", article.getContent())
              .put("digest", article.getDigest());
          mpArticleList.add(articleBuilder.build());
        }
        mpNewsBuilder.put("articles", mpArticleList);

        body.put("mpnews", mpNewsBuilder.build());
        break;
      case MsgType.TASKCARD:
        MapBuilder<String, Object> taskCardBuilder = new MapBuilder<>();

        taskCardBuilder
            .put("title", title)
            .put("description", description)
            .put("url", url)
            .put("task_id", taskId);

        List<Map<String, Object>> buttonList = new ArrayList<>();
        for (TaskCardButton button : taskButtons) {
          MapBuilder<String, Object> buttonBuilder = new MapBuilder<>();
          buttonBuilder
              .put("key", button.getKey())
              .put("name", button.getName())
              .put("replace_name", button.getReplaceName())
              .put("color", button.getColor())
              .put("is_bold", button.getBold());

          buttonList.add(buttonBuilder.build());
        }
        taskCardBuilder.put("btn", buttonList);

        body.put("taskcard", taskCardBuilder.build());
        break;
      case MsgType.MINIPROGRAM_NOTICE:
        MapBuilder<String, Object> miniBuilder = new MapBuilder<>();

        miniBuilder
            .put("appid", appId)
            .put("page", page)
            .put("title", title)
            .put("description", description)
            .put("emphasis_first_item", emphasisFirstItem);

        List<Map<String, Object>> contentList = new ArrayList<>();
        for (Map.Entry<String, String> item : this.getContentItems().entrySet()) {
          MapBuilder<String, Object> contentBuilder = new MapBuilder<>();
          contentBuilder.put("key", item.getKey()).put("value", item.getValue());
          contentList.add(contentBuilder.build());
        }
        miniBuilder.put("content_item", contentList);

        body.put("miniprogram_notice", miniBuilder.build());
        break;
      default:
        break;
    }
  }
}
