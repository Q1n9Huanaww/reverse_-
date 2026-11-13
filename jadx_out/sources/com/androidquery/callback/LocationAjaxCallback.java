package com.androidquery.callback;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.androidquery.util.AQUtility;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class LocationAjaxCallback extends AbstractAjaxCallback<Location, LocationAjaxCallback> {
    private Listener gpsListener;
    private long initTime;

    /* JADX INFO: renamed from: lm */
    private LocationManager f59lm;
    private Listener networkListener;
    private long timeout = 30000;
    private long interval = 1000;
    private float tolerance = 10.0f;
    private float accuracy = 1000.0f;
    private int iteration = 3;

    /* JADX INFO: renamed from: n */
    private int f60n = 0;
    private boolean networkEnabled = false;
    private boolean gpsEnabled = false;

    public LocationAjaxCallback() {
        type(Location.class).url("device");
    }

    @Override // com.androidquery.callback.AbstractAjaxCallback
    public void async(Context context) {
        this.f59lm = (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        this.gpsEnabled = this.f59lm.isProviderEnabled("gps");
        this.networkEnabled = this.f59lm.isProviderEnabled("network");
        work();
    }

    public LocationAjaxCallback timeout(long j) {
        this.timeout = j;
        return this;
    }

    public LocationAjaxCallback accuracy(float f) {
        this.accuracy = f;
        return this;
    }

    public LocationAjaxCallback tolerance(float f) {
        this.tolerance = f;
        return this;
    }

    public LocationAjaxCallback iteration(int i) {
        this.iteration = i;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void check(Location location) {
        if (location == null || !isBetter(location)) {
            return;
        }
        boolean z = true;
        this.f60n++;
        boolean z2 = this.f60n >= this.iteration;
        boolean zIsAccurate = isAccurate(location);
        boolean zIsDiff = isDiff(location);
        if (this.gpsEnabled && !"gps".equals(location.getProvider())) {
            z = false;
        }
        AQUtility.debug(Integer.valueOf(this.f60n), Integer.valueOf(this.iteration));
        AQUtility.debug("acc", Boolean.valueOf(zIsAccurate));
        AQUtility.debug("best", Boolean.valueOf(z));
        if (zIsDiff) {
            if (!z2) {
                if (zIsAccurate && z) {
                    stop();
                }
                callback(location);
                return;
            }
            if (zIsAccurate && z) {
                stop();
                callback(location);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void callback(Location location) {
        this.result = location;
        status(location, 200);
        callback();
    }

    private void status(Location location, int i) {
        if (this.status == null) {
            this.status = new AjaxStatus();
        }
        if (location != null) {
            this.status.time(new Date(location.getTime()));
        }
        this.status.code(i).done().source(5);
    }

    private boolean isAccurate(Location location) {
        return location.getAccuracy() < this.accuracy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isDiff(Location location) {
        if (this.result == 0 || distFrom(((Location) this.result).getLatitude(), ((Location) this.result).getLongitude(), location.getLatitude(), location.getLongitude()) >= this.tolerance) {
            return true;
        }
        AQUtility.debug("duplicate location");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isBetter(Location location) {
        if (this.result == 0 || ((Location) this.result).getTime() <= this.initTime || !((Location) this.result).getProvider().equals("gps") || !location.getProvider().equals("network")) {
            return true;
        }
        AQUtility.debug("inferior location");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void failure() {
        if (this.gpsListener == null && this.networkListener == null) {
            return;
        }
        AQUtility.debug("fail");
        this.result = null;
        status(null, AjaxStatus.TRANSFORM_ERROR);
        stop();
        callback();
    }

    public void stop() {
        AQUtility.debug("stop");
        Listener listener = this.gpsListener;
        if (listener != null) {
            this.f59lm.removeUpdates(listener);
            listener.cancel();
        }
        Listener listener2 = this.networkListener;
        if (listener2 != null) {
            this.f59lm.removeUpdates(listener2);
            listener2.cancel();
        }
        this.gpsListener = null;
        this.networkListener = null;
    }

    private void work() {
        Location bestLocation = getBestLocation();
        Timer timer = new Timer(false);
        Listener listener = null;
        if (this.networkEnabled) {
            AQUtility.debug("register net");
            this.networkListener = new Listener(this, listener);
            this.f59lm.requestLocationUpdates("network", this.interval, 0.0f, this.networkListener, Looper.getMainLooper());
            timer.schedule(this.networkListener, this.timeout);
        }
        if (this.gpsEnabled) {
            AQUtility.debug("register gps");
            this.gpsListener = new Listener(this, listener);
            this.f59lm.requestLocationUpdates("gps", this.interval, 0.0f, this.gpsListener, Looper.getMainLooper());
            timer.schedule(this.gpsListener, this.timeout);
        }
        if (this.iteration > 1 && bestLocation != null) {
            this.f60n++;
            callback(bestLocation);
        }
        this.initTime = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Location getBestLocation() {
        Location lastKnownLocation = this.f59lm.getLastKnownLocation("gps");
        Location lastKnownLocation2 = this.f59lm.getLastKnownLocation("network");
        if (lastKnownLocation2 == null) {
            return lastKnownLocation;
        }
        return (lastKnownLocation != null && lastKnownLocation.getTime() > lastKnownLocation2.getTime()) ? lastKnownLocation : lastKnownLocation2;
    }

    private static float distFrom(double d, double d2, double d3, double d4) {
        double radians = Math.toRadians(d3 - d) / 2.0d;
        double radians2 = Math.toRadians(d4 - d2) / 2.0d;
        double dSin = (Math.sin(radians) * Math.sin(radians)) + (Math.cos(Math.toRadians(d)) * Math.cos(Math.toRadians(d3)) * Math.sin(radians2) * Math.sin(radians2));
        return ((float) (Math.atan2(Math.sqrt(dSin), Math.sqrt(1.0d - dSin)) * 2.0d * 3958.75d)) * 1609;
    }

    private class Listener extends TimerTask implements LocationListener {
        private Listener() {
        }

        /* synthetic */ Listener(LocationAjaxCallback locationAjaxCallback, Listener listener) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            AQUtility.debug("changed", location);
            LocationAjaxCallback.this.check(location);
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            AQUtility.debug("onStatusChanged");
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            AQUtility.debug("onProviderEnabled");
            LocationAjaxCallback.this.check(LocationAjaxCallback.this.getBestLocation());
            LocationAjaxCallback.this.f59lm.removeUpdates(this);
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            AQUtility.debug("onProviderDisabled");
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            LocationAjaxCallback.this.failure();
        }
    }
}
