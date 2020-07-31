package com.ucucs.wxwork.core.constant;

public class ApiPathConsts {
  public static final String API_BASE_URL = "https://qyapi.weixin.qq.com/cgi-bin/";

  public static final String GET_JSAPI_TICKET = "get_jsapi_ticket";
  public static final String GET_AGENT_CONFIG_TICKET = "ticket/get?&type=agent_config";
  public static final String MESSAGE_SEND = "message/send";
  public static final String GET_CALLBACK_IP = "getcallbackip";
  public static final String BATCH_REPLACE_PARTY = "batch/replaceparty";
  public static final String BATCH_REPLACE_USER = "batch/replaceuser";
  public static final String BATCH_GET_RESULT = "batch/getresult?jobid=";
  public static final String JSCODE_TO_SESSION = "miniprogram/jscode2session";
  public static final String GET_TOKEN = "gettoken";

  public static class Agent {
    public static final String AGENT_GET = "agent/get?agentid=%d";
    public static final String AGENT_SET = "agent/set";
    public static final String AGENT_LIST = "agent/list";
  }

  public static class OAuth2 {
    public static final String GET_USER_INFO = "user/getuserinfo?code=%s&agentid=%d";
    public static final String GET_USER_DETAIL = "user/getuserdetail";
    public static final String URL_OAUTH2_AUTHORIZE =
        "https://open.weixin.qq.com/connect/oauth2/authorize";
  }

  public static class Chat {
    public static final String APPCHAT_CREATE = "appchat/create";
    public static final String APPCHAT_UPDATE = "appchat/update";
    public static final String APPCHAT_GET_CHATID = "appchat/get?chatid=";
    public static final String APPCHAT_SEND = "appchat/send";
  }

  public static class Department {
    public static final String DEPARTMENT_CREATE = "department/create";
    public static final String DEPARTMENT_UPDATE = "department/update";
    public static final String DEPARTMENT_DELETE = "department/delete";
    public static final String DEPARTMENT_LIST = "department/list";
  }

  public static class Media {
    public static final String MEDIA_GET = "media/get";
    public static final String MEDIA_UPLOAD = "media/upload?type=";
    public static final String IMG_UPLOAD = "media/uploadimg";
    public static final String JSSDK_MEDIA_GET = "media/get/jssdk";
  }

  public static class Menu {
    public static final String MENU_CREATE = "menu/create?agentid=%d";
    public static final String MENU_DELETE = "menu/delete?agentid=%d";
    public static final String MENU_GET = "menu/get?agentid=%d";
  }

  public static class Oa {
    public static final String GET_CHECKIN_DATA = "checkin/getcheckindata";
    public static final String GET_CHECKIN_OPTION = "checkin/getcheckinoption";
    public static final String GET_APPROVAL_INFO = "oa/getapprovalinfo";
    public static final String GET_APPROVAL_DETAIL = "oa/getapprovaldetail";
    public static final String GET_DIAL_RECORD = "dial/get_dial_record";
    public static final String GET_TEMPLATE_DETAIL = "oa/gettemplatedetail";
    public static final String APPLY_EVENT = "oa/applyevent";
  }

  public static class Tag {
    public static final String TAG_CREATE = "tag/create";
    public static final String TAG_UPDATE = "tag/update";
    public static final String TAG_DELETE = "tag/delete";
    public static final String TAG_LIST = "tag/list";
    public static final String TAG_GET = "tag/get";
    public static final String TAG_ADD_TAG_USERS = "tag/addtagusers";
    public static final String TAG_DEL_TAG_USERS = "tag/deltagusers";
  }

  public static class TaskCard {
    public static final String UPDATE_TASK_CARD = "message/update_taskcard";
  }

  public static class Tp {
    public static final String JSCODE_TO_SESSION = "service/miniprogram/jscode2session";
    public static final String GET_CORP_TOKEN = "service/get_corp_token";
    public static final String GET_PERMANENT_CODE = "service/get_permanent_code";
    public static final String GET_SUITE_TOKEN = "service/get_suite_token";
    public static final String GET_PROVIDER_TOKEN = "service/get_provider_token";
    public static final String GET_PREAUTH_CODE = "service/get_pre_auth_code";
    public static final String GET_AUTH_INFO = "service/get_auth_info";
  }

  public static class User {
    public static final String USER_AUTHENTICATE = "user/authsucc";
    public static final String USER_CREATE = "user/create";
    public static final String USER_UPDATE = "user/update";
    public static final String USER_DELETE = "user/delete";
    public static final String USER_BATCH_DELETE = "user/batchdelete";
    public static final String USER_GET = "user/get";
    public static final String USER_LIST = "user/list";
    public static final String USER_SIMPLE_LIST = "user/simplelist";
    public static final String BATCH_INVITE = "batch/invite";
    public static final String USER_CONVERT_TO_OPENID = "user/convert_to_openid";
    public static final String USER_CONVERT_TO_USERID = "user/convert_to_userid";
    public static final String GET_USER_ID = "user/getuserid";
    public static final String GET_EXTERNAL_CONTACT = "crm/get_external_contact";
  }

  public static class ExternalContact {
    public static final String GET_FOLLOW_USER_LIST = "externalcontact/get_follow_user_list";
    public static final String GET_CONTACT_DETAIL = "externalcontact/get?external_userid=";
    public static final String LIST_EXTERNAL_CONTACT = "externalcontact/list?userid=";
    public static final String LIST_UNASSIGNED_CONTACT = "externalcontact/get_unassigned_list";
    public static final String TRANSFER_UNASSIGNED_CONTACT = "externalcontact/transfer";
    public static final String GROUP_CHAT_LIST = "externalcontact/groupchat/list";
    public static final String GROUP_CHAT_INFO = "externalcontact/groupchat/get";
    public static final String LIST_USER_BEHAVIOR_DATA = "externalcontact/get_user_behavior_data";
    public static final String LIST_GROUP_CHAT_DATA = "externalcontact/groupchat/statistic";
  }
}
