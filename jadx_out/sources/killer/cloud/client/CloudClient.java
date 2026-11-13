package killer.cloud.client;

import android.os.Handler;
import com.alibaba.fastjson.JSON;
import com.umeng.commonsdk.amap.UMAmapConfig;
import java.net.URI;
import killer.core.entity.CloudDevice;
import killer.elfin.util.ToastUtil;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

/* JADX INFO: loaded from: classes2.dex */
public class CloudClient extends WebSocketClient {
    private static final int RECONNECT_INTERVAL = 10000;
    private static final String TAG = "CloudClient";
    public CloudDevice cloudDevice;
    private final CloudService cloudService;
    private Handler handler;
    private final Runnable heartbeatRunnable;
    public boolean isLogin;
    private boolean reconnect;
    private final Runnable reconnectRunnable;

    public CloudClient(URI serverUri) {
        super(serverUri, new Draft_6455());
        this.reconnect = true;
        this.isLogin = true;
        this.reconnectRunnable = new Runnable() { // from class: killer.cloud.client.CloudClient.1
            @Override // java.lang.Runnable
            public void run() {
                if (CloudClient.this.isClosed() && CloudClient.this.reconnect) {
                    CloudClient.this.reconnect();
                }
            }
        };
        this.heartbeatRunnable = new Runnable() { // from class: killer.cloud.client.CloudClient.2
            @Override // java.lang.Runnable
            public void run() {
                if (CloudClient.this.isOpen()) {
                    CloudClient.this.send("ping");
                }
                CloudClient.this.handler.postDelayed(this, 20000L);
            }
        };
        this.handler = new Handler();
        this.cloudService = CloudService.get();
    }

    @Override // org.java_websocket.client.WebSocketClient
    public void onOpen(ServerHandshake handshakedata) {
        this.cloudService.login(this);
    }

    @Override // org.java_websocket.client.WebSocketClient
    public void onMessage(String message) {
        if (!"pong".equals(message)) {
            CloudMessage cloudMessage = (CloudMessage) JSON.parseObject(message, CloudMessage.class);
            switch (cloudMessage.action) {
                case 1000:
                    closeClient();
                    ToastUtil.toast("[云控]" + cloudMessage.message, 3000, 10, 10);
                    break;
                case CloudService.LOGIN_SUCCESS /* 1102 */:
                    this.handler.postDelayed(this.heartbeatRunnable, UMAmapConfig.AMAP_CACHE_WRITE_TIME);
                    this.cloudService.loginSuccess(this, cloudMessage);
                    break;
                case CloudService.START_SCRIPT /* 3101 */:
                    this.cloudService.commandScriptStart();
                    break;
                case CloudService.STOP_SCRIPT /* 3102 */:
                    this.cloudService.commandScriptStop();
                    break;
                case CloudService.SAVE_SETTING /* 3300 */:
                    this.cloudService.commandSaveScriptCfg(cloudMessage);
                    break;
                case CloudService.HOT_UPDATE /* 40002 */:
                    this.cloudService.commandHotUpdate(cloudMessage);
                    break;
            }
        }
    }

    @Override // org.java_websocket.client.WebSocketClient
    public void onClose(int code, String reason, boolean remote) {
        this.isLogin = false;
        reconnectWs();
    }

    @Override // org.java_websocket.client.WebSocketClient
    public void onError(Exception ex) {
    }

    public void closeClient() {
        this.isLogin = false;
        this.reconnect = false;
        this.handler.removeCallbacksAndMessages(null);
        close();
    }

    private void reconnectWs() {
        this.handler.removeCallbacksAndMessages(null);
        if (this.reconnect) {
            this.handler.postDelayed(this.reconnectRunnable, UMAmapConfig.AMAP_CACHE_WRITE_TIME);
        }
    }

    public void sendCloudMessage(CloudMessage message) {
        if (isOpen()) {
            send(JSON.toJSONString(message));
        }
    }
}
