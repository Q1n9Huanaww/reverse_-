package com.p024ds.daisi.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GPSUtils {
    public static final String[] PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    private static GPSUtils instance;
    public LocationListener locationListener = new LocationListener() { // from class: com.ds.daisi.util.GPSUtils.1
        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (GPSUtils.this.mOnLocationListener != null) {
                GPSUtils.this.mOnLocationListener.OnLocationChange(location);
            }
        }
    };
    private LocationManager locationManager;
    private Context mContext;
    private OnLocationResultListener mOnLocationListener;

    public interface OnLocationResultListener {
        void OnLocationChange(Location location);

        void onLocationResult(Location location);
    }

    private GPSUtils(Context context) {
        this.mContext = context;
    }

    public static GPSUtils getInstance(Context context) {
        if (instance == null) {
            instance = new GPSUtils(context);
        }
        return instance;
    }

    public String[] getLngAndLatJudgeOperate(OnLocationResultListener onLocationResultListener) {
        this.mOnLocationListener = onLocationResultListener;
        String[] strArr = {String.valueOf(0.0d), String.valueOf(0.0d)};
        if (Build.VERSION.SDK_INT >= 23) {
            return lackPermission() ? strArr : getLngAndLat();
        }
        return getLngAndLat();
    }

    @SuppressLint({"MissingPermission"})
    public String[] getLngAndLat() {
        String str;
        String[] strArr = {String.valueOf(0.0d), String.valueOf(0.0d)};
        this.locationManager = (LocationManager) this.mContext.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        List<String> providers = this.locationManager.getProviders(true);
        if (providers.contains("gps")) {
            str = "gps";
        } else {
            if (!providers.contains("network")) {
                return strArr;
            }
            str = "network";
        }
        String str2 = str;
        Location lastKnownLocation = this.locationManager.getLastKnownLocation(str2);
        if (lastKnownLocation != null) {
            if (this.mOnLocationListener != null) {
                this.mOnLocationListener.onLocationResult(lastKnownLocation);
            }
            strArr[0] = String.valueOf(lastKnownLocation.getLongitude());
            strArr[1] = String.valueOf(lastKnownLocation.getLatitude());
        }
        this.locationManager.requestLocationUpdates(str2, 3000L, 1.0f, this.locationListener);
        return strArr;
    }

    private boolean lackPermission() {
        for (int i = 0; i < PERMISSIONS.length; i++) {
            if (this.mContext.checkCallingOrSelfPermission(PERMISSIONS[i]) == -1) {
                return true;
            }
        }
        return false;
    }

    public void removeListener() {
        this.locationManager.removeUpdates(this.locationListener);
    }
}
