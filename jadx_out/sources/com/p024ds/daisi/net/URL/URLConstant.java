package com.p024ds.daisi.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class URLConstant {
    public static final String BLACK_LIST_NAME = "GetElfBlackList";
    public static final String BLACK_LIST_PACKAGE = "packagename";
    public static final String BLACK_LIST_TYPE = "type";
    public static final String CODES_SOURCE_NAME = "GetCodes";
    public static String MONOMER_INSTALLATION_PACKAGE = "GetMonomerInstallationPackage";
    public static String MONOMER_PACKAGE_NAME = "packagename";
    public static final String MSG_REQUEST_GUID = "guid";
    public static final String MSG_REQUEST_IMIE = "imie";
    public static final String MSG_REQUEST_IS_FREE = "isfree";
    public static final String MSG_REQUEST_NAME = "GetMessage";
    public static final String MSG_REQUEST_PLATFORMS = "platforms";
    public static String TEMPLATE_MANAGER = "GetTemplateManager";
    public static String TEMPLATE_TYPE = "type";
    public static String TEMPLATE_VERSION = "version";
    public static final String TOKEN_PARAMS_APPID = "AppId";
    public static final String TOKEN_PARAMS_DATA = "Data";
    public static final String TOKEN_PARAMS_RCODE = "RCode";
    public static final String TOKEN_PARAMS_SIGN = "Sign";
    public static final String TOKEN_PARAMS_TOKEN = "Token";
    public static final String TOKEN_PARAMS_USERNAME = "UserName";
    public static final String TOKEN_PARAM_TYPE = "Type";
    public static final String TOKEN_REQ_NAME = "Token";
    public static final String USER_ACCOUNT = "phone";
    public static final String USER_APK_PACKAGENAME = "packagename";
    public static final String USER_CHECKED_ACCOUNT_FREEZE = "CheckAccount";
    public static final String USER_SCRIPT_TRY_COUNT = "GetTryCount";
    public static final String USER_TIME_URL = "http://121.41.22.28/api/UserCheckAppTime?Data=";
    public static final String VALUE_TYPE = String.valueOf(0);
    public static final String PAY_OR_Free = String.valueOf(1);
    public static final String UPDATE_REQUEST_NAME = "Update";
    public static final String UPDATE_URL = MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY_VERIFY, UPDATE_REQUEST_NAME).build().toString();
    public static final String LOG_REQUEST_NAME = "SetLog";
    public static final String LOGURL = MyBuildConfig.getBuilder(MyBuildConfig.LOG_AUTHORITY, LOG_REQUEST_NAME).build().toString();
    public static final String ACCOUNT_REQUEST_NAME = "User";
    public static final String ACCOUNT_URL = MyBuildConfig.getBuilder(MyBuildConfig.AUTHORITY, ACCOUNT_REQUEST_NAME).build().toString();
}
