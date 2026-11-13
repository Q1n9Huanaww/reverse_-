package mobi.oneway.export.p087g;

import android.location.Location;
import android.location.LocationManager;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import mobi.oneway.export.p078a.C1900b;

/* JADX INFO: renamed from: mobi.oneway.export.g.l */
/* JADX INFO: loaded from: classes.dex */
public class C1958l {

    /* JADX INFO: renamed from: mobi.oneway.export.g.l$a */
    private enum a {
        lat,
        lon
    }

    /* JADX INFO: renamed from: a */
    public static String m5039a() {
        return m5040a(a.lat);
    }

    /* JADX INFO: renamed from: a */
    private static String m5040a(a aVar) {
        Location lastKnownLocation;
        try {
            LocationManager locationManager = (LocationManager) C1900b.m4706a().getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
            if (locationManager.isProviderEnabled("gps") && (lastKnownLocation = locationManager.getLastKnownLocation("gps")) != null) {
                switch (aVar) {
                    case lat:
                        return String.valueOf(lastKnownLocation.getLatitude());
                    case lon:
                        return String.valueOf(lastKnownLocation.getLongitude());
                    default:
                        return "";
                }
            }
            return m5042b(aVar);
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m5041b() {
        return m5040a(a.lon);
    }

    /* JADX INFO: renamed from: b */
    private static String m5042b(a aVar) {
        double latitude;
        Location lastKnownLocation = ((LocationManager) C1900b.m4706a().getSystemService(RequestParameters.SUBRESOURCE_LOCATION)).getLastKnownLocation("network");
        if (lastKnownLocation == null) {
            return "";
        }
        switch (aVar) {
            case lat:
                latitude = lastKnownLocation.getLatitude();
                break;
            case lon:
                latitude = lastKnownLocation.getLongitude();
                break;
            default:
                return "";
        }
        return String.valueOf(latitude);
    }
}
