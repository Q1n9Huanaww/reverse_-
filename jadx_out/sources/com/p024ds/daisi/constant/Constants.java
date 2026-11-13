package com.p024ds.daisi.constant;

import android.content.Context;
import android.os.Environment;
import com.umeng.commonsdk.proguard.C1770bg;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class Constants {
    public static final String ACCOUNT = "account";
    public static final String ANDROID = "android";
    public static final String APPID_SP_KEY = "appid_sp_key";
    public static final String CYJH_PAY_SP = "cyjh_pay_sp";
    public static final String DATABASE_TABLE_MSG = "Message";
    public static final int DEFAULT_NEGATIVE_ONE = -1;
    public static final int DEFAULT_ONE = 1;
    public static final int DEFAULT_ZERO = 0;
    public static final int ENUM_SCRIPT_NEED_UPGRADE = 36;
    public static final String EXIT_LOGIN_FILE = "ExitLogin.txt";
    public static final String FENG_LING_AD_APP_ID = "5e1f6df6cc024c80b7e8b86dc670c100";
    public static final String FENG_LING_AD_BANNER_ID = "101477";
    public static final String FENG_LING_AD_SPLASH_ID = "101478";
    public static final String HAS_LOAD_BOTTOM_AD = "has_load_bottom_ad";
    public static final String HAS_LOAD_SPLASH_FIRST_AD = "has_load_splash_first_ad";
    public static final String HAS_LOAD_SPLASH_SECOND_AD = "has_load_splash_second_ad";
    public static final int HOURS = 24;
    public static final String ISEXIST_NETWORK_REQ_FILE = "network_file_is_exist";
    public static final String IS_LOGIN = "is_login";
    public static final int IS_SCRIPT_HOT_UPGRADE = 1;
    public static final String IS_STUDIO = "is_studio";
    public static final String IS_TIMEOUT_FLAG = "is_timeout_flag";
    public static final String LOGIN_MESSAGE_DIR = "login";
    public static final int MESSAGE_WHAT_GET_ROOT_LEAD_URL_SUCCEED = 1;
    public static final int MINUTES = 60;
    public static final String MSG_DATA = "msgData";
    public static final String NETWORK_MESSAGE = "NetworkMessage";
    public static final String NETWORK_REQUEST_LOG = "NetworkReqLog.txt";
    public static final String NETWORK_RES_MESSAGE_TIME = "save_network_message";
    public static final int ONE_THOUSAND = 1000;
    public static final String POLYMERIC_AD_APP_ID = "589ac20e";
    public static final String POLYMERIC_AD_BOTTOM_ID = "9312C6DA2448C84DB3C4FE8F66E2B394";
    public static final String POLYMERIC_AD_SPLASH_FIRST_ID = "F4F04588C1076DA9017964B229D657DA";
    public static final String POLYMERIC_AD_SPLASH_SECOND_ID = "94CCB814573108BFBD19D8E2BB45AEE0";
    public static final String PUSH_SYSTEM_APP_NAME = "app-release.apk";
    public static final String REBOOT = "110";
    public static final String RED_DOT_FLAG = "red_dot_flag";
    public static final String RED_FINGER_PCKAGENAME = "com.redfinger.appstore";
    public static final int REQUEST_CODE_APP_INSTALL = 10000;
    public static int ROOT_TYPE = 2;
    public static final int ROOT_TYPE_ELFIN = 2;
    public static final String SCRIPT_DAY_COUNT = "script_day_count";
    public static final String SCRIPT_TRY_COUNT = "script_try_count";
    public static final String SCRIPT_TRY_USE_COUNT = "script_count_use";
    public static final String SCRIPT_USER_TYPE = "sciprt_user_type";
    public static final String SCRIPT_USE_COUNT = "count";
    public static final String SCRIPT_USE_MESSAGE = "use";
    public static String SD_CARD_SCRIPT_ASSETS = "assets";
    public static String SD_CARD_SCRIPT_DIR = "script";
    public static String SD_CARD_SCRIPT_NAME = ".config";
    public static final String SINGLE_APK_REQ_TIME = "single_apk_req";
    public static final String SMALL_RED_DOT = "small_red_dot";
    public static final String TEMPLATE_APK_REQ_TIME = "template_apk_req";
    public static final String TOKEN_VERIFY_CODES = "token_verify_codes";
    public static final String TOKEN_VERIFY_KEY = "token_verify_key";
    public static int UPLOAD_INSTANCE_DATA_INTERVAL = 60;
    public static final String USER_MESSAGE = "user_message";
    public static final byte[] DESkey = {19, 31, 97, 26, 10, 80, 11, 37};
    public static final byte[] DESIV = {35, 12, 37, 49, 101, 91, C1770bg.f4124l, 36};

    public static final String getApkPath(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + context.getPackageName();
    }

    public static File getSaveFilePath(Context context, String str, String str2) {
        File file = new File(getApkPath(context), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, str2);
    }

    public static File getCacheDeviceId(Context context) {
        File file = new File(getApkPath(context), "Info");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, "device_id");
    }
}
