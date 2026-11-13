package com.example.keyverificationonline;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import net.lingala.zip4j.util.InternalZipConstants;
import okhttp366.OkHttpClient;
import okhttp366.ResponseBody;
import okhttp366.logging.HttpLoggingInterceptor;
import retrofit266.Call;
import retrofit266.Callback;
import retrofit266.Response;
import retrofit266.Retrofit;

/* JADX INFO: loaded from: classes2.dex */
public class DialogUtil {
    private static ApiService apiService;
    public static Properties conf;
    private static AlertDialog dialog;
    private static View dialogView;

    /* JADX INFO: renamed from: sp */
    private static SharedPreferences f1711sp;
    private static String deviceID = null;
    private static String accountId = "";
    private static String expireDate = "";
    private static String channelId = "";
    private static int randomID = new Random().nextInt(999999) + 1;
    private static String noticeMsg = "";
    private static Handler checkHandler = new Handler();
    private static Runnable checkRunnable = null;
    public static String localConfig = "";
    private static String identifierCode = "";
    private static String httpSign = "";
    private static String appVersion = "";
    private static long checkInterval = 600000;
    private static boolean downloadState = false;

    public static void show(Context context) {
        try {
            InputStreamReader reader = new InputStreamReader(context.getResources().getAssets().open("Jq"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder result = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                } else {
                    result.append(line);
                }
            }
            bufferedReader.close();
            reader.close();
            localConfig = result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            showToast("初始化失败！", context);
        }
        if (conf == null) {
            conf = new Properties();
            try {
                conf.load(new ByteArrayInputStream(Base64.decode(localConfig.getBytes(), 0)));
                identifierCode = conf.getProperty("Code");
                httpSign = conf.getProperty(URLConstant.TOKEN_PARAMS_SIGN);
                appVersion = conf.getProperty("Ver");
                String checkIntervalString = conf.getProperty("Time");
                if (checkIntervalString != null && !checkIntervalString.isEmpty()) {
                    checkInterval = ((long) Integer.parseInt(checkIntervalString)) * 1000;
                }
            } catch (IOException e2) {
                showToast("初始化失败：" + e2.getMessage(), context);
            }
        }
        f1711sp = context.getSharedPreferences("config", 0);
        deviceID = DeviceUtil.getDeviceID(context);
        if (deviceID == null) {
            showToast("机器码获取失败！", context);
            System.exit(0);
        }
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() { // from class: com.example.keyverificationonline.DialogUtil.1
            @Override // okhttp366.logging.HttpLoggingInterceptor.Logger
            public void log(String message) {
                Log.d("Http", message + "");
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.BASE_URL + InternalZipConstants.ZIP_FILE_SEPARATOR).client(client).build();
        apiService = (ApiService) retrofit.create(ApiService.class);
        notice(context);
    }

    public static void notice(final Context context) {
        String data = identifierCode + "|" + httpSign;
        Call<ResponseBody> call = apiService.userLogin(Config.BASE_URL + "?action=GetInfo&data=" + data);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.example.keyverificationonline.DialogUtil.2
            @Override // retrofit266.Callback
            public void onResponse(Call<ResponseBody> p0, Response<ResponseBody> response) {
                try {
                    String result = response.body() != null ? response.body().string() : "";
                    if (!result.contains("|")) {
                        DialogUtil.verificationDialog(context);
                        return;
                    }
                    String[] values = result.split("\\|");
                    if (values.length == 0) {
                        DialogUtil.verificationDialog(context);
                        return;
                    }
                    String code = values[0];
                    if (code.equals("1")) {
                        String unused = DialogUtil.noticeMsg = values[5];
                        DialogUtil.verificationDialog(context);
                        String updateState = values[3];
                        updateState.equals(AccountManager.TYPE_LOGIN);
                        boolean isFocusUpdate = false;
                        if (updateState.equals("1")) {
                            isFocusUpdate = true;
                        }
                        String updateUrl = values[4];
                        String cloudAppVersion = values[1];
                        DialogUtil.updateDialog(context, isFocusUpdate, updateUrl, cloudAppVersion);
                        return;
                    }
                    DialogUtil.showToast("公告获取失败！", context);
                    DialogUtil.verificationDialog(context);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override // retrofit266.Callback
            public void onFailure(Call<ResponseBody> p0, Throwable p1) {
                DialogUtil.showToast("公告获取出错！", context);
                DialogUtil.verificationDialog(context);
            }
        });
    }

    public static void verificationDialog(final Context context) {
        CharSequence code = f1711sp.getString("activeCode", "");
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setPadding(0, 50, 0, 0);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(0);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout2.setPadding(50, 0, 50, 50);
        final EditText codeEditText = new EditText(context);
        codeEditText.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        codeEditText.setMaxLines(1);
        codeEditText.setSingleLine(true);
        LinearLayout.LayoutParams codeEditTextParams = (LinearLayout.LayoutParams) codeEditText.getLayoutParams();
        codeEditTextParams.weight = 1.0f;
        codeEditText.setLayoutParams(codeEditTextParams);
        Button activeBtn = new Button(context);
        activeBtn.setText("激活");
        activeBtn.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout2.addView(codeEditText);
        linearLayout2.addView(activeBtn);
        TextView noticeMsgText = new TextView(context);
        noticeMsgText.setText(noticeMsg);
        noticeMsgText.setPadding(50, 0, 50, 50);
        linearLayout.addView(linearLayout2);
        linearLayout.addView(noticeMsgText);
        codeEditText.setText(code);
        LinearLayout buttonContainer = new LinearLayout(context);
        buttonContainer.setOrientation(0);
        buttonContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        View verticalLine = new View(context);
        LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(1, -1);
        verticalLine.setLayoutParams(lineParams);
        verticalLine.setBackgroundColor(-7829368);
        Button neutralButton = new Button(context);
        neutralButton.setText("点我试用");
        neutralButton.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        neutralButton.setBackgroundColor(Color.parseColor("#ea702e"));
        neutralButton.setTextColor(Color.parseColor("#FFFFFF"));
        Button positiveButton = new Button(context);
        positiveButton.setText("退出");
        positiveButton.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        positiveButton.setBackgroundColor(Color.parseColor("#4b77bf"));
        positiveButton.setTextColor(Color.parseColor("#FFFFFF"));
        buttonContainer.addView(neutralButton);
        buttonContainer.addView(verticalLine);
        buttonContainer.addView(positiveButton);
        linearLayout.addView(buttonContainer);
        dialogView = linearLayout;
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setMessage("卡密激活").setCancelable(false).setView(dialogView);
        dialog = builder.show();
        neutralButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.keyverificationonline.DialogUtil$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.requestLogin(InterfaceRelatedConstants.CHANNEL_ANJIAN_DEBUG, context);
            }
        });
        positiveButton.setOnClickListener(new View.OnClickListener() { // from class: com.example.keyverificationonline.DialogUtil$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.lambda$verificationDialog$1(context, view);
            }
        });
        activeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.example.keyverificationonline.DialogUtil$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialogUtil.lambda$verificationDialog$2(codeEditText, context, view);
            }
        });
    }

    static /* synthetic */ void lambda$verificationDialog$1(Context context, View v) {
        showToast("退出软件", context);
        System.exit(0);
    }

    static /* synthetic */ void lambda$verificationDialog$2(EditText codeEditText, Context context, View v) {
        String activeCode = codeEditText.getText().toString().trim();
        if (activeCode.isEmpty()) {
            showToast("注册码不能为空！", context);
        } else if (activeCode.contains(" ")) {
            showToast("注册码不存在！", context);
        } else {
            requestLogin(activeCode, context);
        }
    }

    public static void updateDialog(final Context context, boolean isFocusUpdate, final String updateUrl, String cloudAppVersion) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "app_update.apk");
        if (file.exists()) {
            file.delete();
        }
        if (updateUrl == null || updateUrl.isEmpty()) {
            showToast("更新链接无效", context);
            return;
        }
        if (!appVersion.equals(cloudAppVersion)) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            linearLayout.setPadding(50, 40, 50, 40);
            TextView messageTextView = new TextView(context);
            messageTextView.setText("当前版本 " + appVersion + "，新版本 " + cloudAppVersion);
            messageTextView.setTextSize(16.0f);
            messageTextView.setPadding(0, 0, 0, 30);
            linearLayout.addView(messageTextView);
            final LinearLayout progressLayout = new LinearLayout(context);
            progressLayout.setOrientation(0);
            progressLayout.setPadding(16, 16, 16, 16);
            final ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
            progressBar.setMax(100);
            LinearLayout.LayoutParams progressBarParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
            progressBar.setLayoutParams(progressBarParams);
            progressLayout.addView(progressBar);
            final TextView progressTextView = new TextView(context);
            progressTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            progressTextView.setPadding(8, 0, 0, 0);
            progressTextView.setText("0%");
            progressLayout.addView(progressTextView);
            progressLayout.setVisibility(8);
            linearLayout.addView(progressLayout);
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setCancelable(false).setTitle("提示").setView(linearLayout).setPositiveButton("更新", (DialogInterface.OnClickListener) null);
            if (!isFocusUpdate) {
                builder.setNegativeButton("暂不更新", (DialogInterface.OnClickListener) null);
            }
            AlertDialog updateDialog = builder.show();
            updateDialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.example.keyverificationonline.DialogUtil$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DialogUtil.lambda$updateDialog$3(context, progressLayout, progressBar, progressTextView, updateUrl, view);
                }
            });
        }
    }

    static /* synthetic */ void lambda$updateDialog$3(final Context context, LinearLayout progressLayout, final ProgressBar progressBar, final TextView progressTextView, String updateUrl, View v) {
        if (23 <= Build.VERSION.SDK_INT && Build.VERSION.SDK_INT <= 29 && ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            showToast("请授予存储权限！", context);
            ActivityCompat.requestPermissions((Activity) context, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
        } else {
            progressLayout.setVisibility(0);
            new DownloadFileTask(context, new DownloadFileTask.DownloadListener() { // from class: com.example.keyverificationonline.DialogUtil.3
                @Override // com.example.keyverificationonline.DownloadFileTask.DownloadListener
                public void onProgress(int progress) {
                    progressBar.setProgress(progress);
                    progressTextView.setText(progress + "%");
                }

                @Override // com.example.keyverificationonline.DownloadFileTask.DownloadListener
                public void onDownloadFinished(String filePath) {
                    DialogUtil.installApk(context, filePath);
                }
            }).execute(updateUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void requestLogin(final String activeCode, final Context context) {
        String data = activeCode.replace("\n", "").replace(" ", "") + "|" + Config.PASSWORD + "|" + deviceID + "|" + identifierCode + "|" + deviceID + "|" + randomID + "|" + httpSign;
        Call<ResponseBody> call = apiService.userLogin(Config.BASE_URL + "?action=UserLogin&data=" + data);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.example.keyverificationonline.DialogUtil.4
            @Override // retrofit266.Callback
            public void onResponse(Call<ResponseBody> p0, Response<ResponseBody> response) {
                try {
                    String result = response.body() != null ? response.body().string() : "";
                    if (result.contains("|")) {
                        String[] values = result.split("\\|");
                        if (values.length == 0) {
                            return;
                        }
                        String code = values[0];
                        if (code.equals("1")) {
                            String currentDate = values[4];
                            if (DialogUtil.checkSystemDate(currentDate)) {
                                MsgUtils.alert(context, "时间校验失败！请检查设备时间是否准确");
                            }
                            if (!activeCode.toLowerCase().equals(InterfaceRelatedConstants.CHANNEL_ANJIAN_DEBUG)) {
                                SharedPreferences.Editor editor = DialogUtil.f1711sp.edit();
                                editor.putString("activeCode", activeCode);
                                editor.apply();
                            }
                            String unused = DialogUtil.accountId = values[2];
                            String unused2 = DialogUtil.expireDate = values[3];
                            String unused3 = DialogUtil.channelId = values[7];
                            DialogUtil.showToast("到期时间：" + DialogUtil.expireDate, context);
                            DialogUtil.dialog.dismiss();
                            DialogUtil.startCheck(context, activeCode);
                        }
                        if (code.equals("2")) {
                            DialogUtil.showToast(values[1], context);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override // retrofit266.Callback
            public void onFailure(Call<ResponseBody> p0, Throwable p1) {
                DialogUtil.showToast("请求失败！", context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startCheck(final Context context, final String activeCode) {
        checkRunnable = new Runnable() { // from class: com.example.keyverificationonline.DialogUtil.5
            @Override // java.lang.Runnable
            public void run() {
                DialogUtil.checkExpireDate(context, activeCode);
                DialogUtil.checkHandler.postDelayed(this, DialogUtil.checkInterval);
            }
        };
        checkHandler.postDelayed(checkRunnable, checkInterval);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void stopCheck() {
        if (checkRunnable != null) {
            checkHandler.removeCallbacks(checkRunnable);
            checkRunnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkExpireDate(final Context context, String activeCode) {
        String data = accountId + "|" + activeCode + "|" + deviceID + "|" + deviceID + "|" + randomID + "|" + identifierCode + "|" + channelId;
        Call<ResponseBody> call = apiService.checkExpire(Config.BASE_URL + "?action=CheckTime&data=" + data);
        call.enqueue(new Callback<ResponseBody>() { // from class: com.example.keyverificationonline.DialogUtil.6
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code duplicated, block: B:33:0x0068  */
            @Override // retrofit266.Callback
            public void onResponse(Call<ResponseBody> p0, Response<ResponseBody> response) {
                boolean pass = false;
                String errorMessage = "";
                try {
                    String result = response.body() != null ? response.body().string() : "";
                    switch (result) {
                        case "1":
                            errorMessage = "帐号被锁定";
                            break;
                        case "2":
                            DialogUtil.f1711sp.edit().putString("activeCode", "").apply();
                            errorMessage = "帐号过期";
                            break;
                        case "3":
                            DialogUtil.showToast("内部错误", context);
                            break;
                        case "4":
                            DialogUtil.f1711sp.edit().putString("activeCode", "").apply();
                            errorMessage = "帐号已在别处登录";
                            break;
                        case "5":
                            DialogUtil.showToast("软件停止运营", context);
                            break;
                        case "6":
                            DialogUtil.showToast("软件维护中", context);
                            break;
                        case "7":
                            DialogUtil.showToast("帐号点数不足", context);
                            break;
                        default:
                            pass = true;
                            break;
                    }
                    if (!pass) {
                        DialogUtil.stopCheck();
                        DialogUtil.dialog.show();
                        if (errorMessage.isEmpty()) {
                            errorMessage = "未知错误";
                        }
                        MsgUtils.alert(context, errorMessage);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override // retrofit266.Callback
            public void onFailure(Call<ResponseBody> p0, Throwable p1) {
                MsgUtils.alert(context, "过期校验出错");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void installApk(Context context, String filePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(268435456);
        Uri apkUri = null;
        if ((Build.VERSION.SDK_INT >= 29 && filePath.contains("content://")) || Build.VERSION.SDK_INT >= 24 || filePath.contains("file://")) {
            apkUri = Uri.parse(filePath);
        }
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        intent.addFlags(1);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean checkSystemDate(String currentDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long local = Calendar.getInstance().getTimeInMillis();
        try {
            return Math.abs(sdf.parse(currentDateString).getTime() - local) >= 120000;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showToast(String msg, Context context) {
        Toast.makeText(context, msg, 0).show();
    }

    private static void fullscreenDialog(Context context, String title, String message, final DialogInterface.OnClickListener listener) {
        AlertDialog dialog2 = new AlertDialog.Builder(context, C1096R.style.FULLSCREEN).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton("退出", new DialogInterface.OnClickListener() { // from class: com.example.keyverificationonline.DialogUtil.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog3, int which) {
                listener.onClick(dialog3, which);
            }
        }).create();
        dialog2.show();
    }

    private static void crashReport(String message) {
        String.valueOf(Build.VERSION.SDK_INT);
    }
}
