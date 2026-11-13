package org.java_websocket.exceptions;

import android.support.v7.widget.ActivityChooserView;

/* JADX INFO: loaded from: classes2.dex */
public class LimitExceededException extends InvalidDataException {
    private static final long serialVersionUID = 6908339749836826785L;
    private final int limit;

    public LimitExceededException() {
        this(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public LimitExceededException(int limit) {
        super(1009);
        this.limit = limit;
    }

    public LimitExceededException(String s, int limit) {
        super(1009, s);
        this.limit = limit;
    }

    public LimitExceededException(String s) {
        this(s, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public int getLimit() {
        return this.limit;
    }
}
