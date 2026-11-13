package org.wlf.filedownloader.base;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FailReason extends Exception {
    private String mType;
    public static final String TYPE_UNKNOWN = FailReason.class.getName() + "_TYPE_UNKNOWN";
    public static final String TYPE_NULL_POINTER = FailReason.class.getName() + "_TYPE_NULL_POINTER";

    public FailReason(String type) {
        this.mType = TYPE_UNKNOWN;
        this.mType = type;
        if (!isTypeInit()) {
            initType(this);
        }
    }

    public FailReason(String detailMessage, String type) {
        super(detailMessage);
        this.mType = TYPE_UNKNOWN;
        this.mType = type;
        if (!isTypeInit()) {
            initType(this);
        }
    }

    public FailReason(String detailMessage, Throwable throwable, String type) {
        super(detailMessage, throwable);
        this.mType = TYPE_UNKNOWN;
        this.mType = type;
        if (!isTypeInit()) {
            initType(throwable);
        }
    }

    public FailReason(Throwable throwable, String type) {
        super(throwable);
        this.mType = TYPE_UNKNOWN;
        this.mType = type;
        if (!isTypeInit()) {
            initType(throwable);
        }
    }

    public FailReason(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.mType = TYPE_UNKNOWN;
        initType(throwable);
    }

    public FailReason(Throwable throwable) {
        super(throwable);
        this.mType = TYPE_UNKNOWN;
        initType(throwable);
    }

    protected final boolean isTypeInit() {
        return (TYPE_UNKNOWN.equals(this.mType) || TextUtils.isEmpty(this.mType)) ? false : true;
    }

    private void initType(Throwable throwable) {
        if (throwable != null) {
            onInitTypeWithThrowable(throwable);
            if (throwable != throwable.getCause() && !isTypeInit()) {
                initType(throwable.getCause());
            }
        }
    }

    protected void onInitTypeWithThrowable(Throwable throwable) {
        if (!isTypeInit() && throwable != null) {
            if (throwable instanceof FailReason) {
                FailReason failReason = (FailReason) throwable;
                onInitTypeWithOriginalThrowable(failReason.getOriginalCause());
                if (!isTypeInit()) {
                    onInitTypeWithFailReasonInternal(failReason);
                    return;
                }
                return;
            }
            onInitTypeWithOriginalThrowable(throwable);
        }
    }

    private void onInitTypeWithFailReasonInternal(FailReason failReason) {
        if (failReason != null) {
            onInitTypeWithFailReason(failReason);
            if (!isTypeInit()) {
                Throwable cause = failReason.getCause();
                if (cause instanceof FailReason) {
                    onInitTypeWithFailReasonInternal((FailReason) cause);
                }
            }
        }
    }

    protected void onInitTypeWithFailReason(FailReason failReason) {
    }

    protected void onInitTypeWithOriginalThrowable(Throwable originalThrowable) {
        if (originalThrowable != null) {
            String throwableClassName = originalThrowable.getClass().getName();
            if (!TextUtils.isEmpty(throwableClassName) && throwableClassName.equals(NullPointerException.class.getName())) {
                this.mType = TYPE_NULL_POINTER;
            }
        }
    }

    protected final void setType(String type) {
        this.mType = type;
    }

    public String getType() {
        return this.mType;
    }

    public Throwable getOriginalCause() {
        return getOriginalCauseInternal(this);
    }

    private Throwable getOriginalCauseInternal(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        Throwable cause = throwable.getCause();
        if (cause instanceof FailReason) {
            return getOriginalCauseInternal(cause);
        }
        return cause;
    }
}
