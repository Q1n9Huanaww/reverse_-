package com.cyjh.feedback.lib.runnable;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.cyjh.feedback.lib.entities.PicDataBean;
import com.cyjh.feedback.lib.entities.PicPathBean;
import com.cyjh.feedback.lib.utils.ImageReaderLoacl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TraversePicRunnable implements Runnable {
    private List<String> mAddList;
    private Context mContext;
    private Handler mHandler;

    public TraversePicRunnable(Context context, Handler handler, List<String> list) {
        this.mContext = context;
        this.mHandler = handler;
        this.mAddList = list;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        PicDataBean picDataBean = new PicDataBean();
        ArrayList arrayList = new ArrayList();
        if (this.mAddList != null && this.mAddList.size() > 0) {
            Iterator<String> it2 = this.mAddList.iterator();
            while (it2.hasNext()) {
                String strImg2Base64 = ImageReaderLoacl.img2Base64(this.mContext, it2.next());
                PicPathBean picPathBean = new PicPathBean();
                picPathBean.setImage(strImg2Base64);
                arrayList.add(picPathBean);
            }
        } else {
            PicPathBean picPathBean2 = new PicPathBean();
            picPathBean2.setImage(String.valueOf(0));
            arrayList.add(picPathBean2);
        }
        picDataBean.setRdata(arrayList);
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.obj = picDataBean;
        messageObtainMessage.what = 0;
        this.mHandler.sendMessage(messageObtainMessage);
    }
}
