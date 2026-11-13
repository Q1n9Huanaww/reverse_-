package com.didi.virtualapk.internal.utils;

import android.content.Context;
import android.content.pm.PackageParser;
import android.os.Build;
import com.didi.virtualapk.utils.Reflector;
import com.umeng.analytics.pro.C1708b;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class PackageParserCompat {
    public static final PackageParser.Package parsePackage(Context context, File file, int i) {
        try {
            if (Build.VERSION.SDK_INT < 28 && (Build.VERSION.SDK_INT != 27 || Build.VERSION.PREVIEW_SDK_INT == 0)) {
                if (Build.VERSION.SDK_INT >= 24) {
                    return PackageParserV24.parsePackage(context, file, i);
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    return PackageParserLollipop.parsePackage(context, file, i);
                }
                return PackageParserLegacy.parsePackage(context, file, i);
            }
            return PackageParserPPreview.parsePackage(context, file, i);
        } catch (Throwable th) {
            throw new RuntimeException(C1708b.f3698J, th);
        }
    }

    private static final class PackageParserPPreview {
        private PackageParserPPreview() {
        }

        static final PackageParser.Package parsePackage(Context context, File file, int i) throws Throwable {
            PackageParser packageParser = new PackageParser();
            PackageParser.Package r6 = packageParser.parsePackage(file, i);
            Reflector.with(packageParser).method("collectCertificates", PackageParser.Package.class, Boolean.TYPE).call(r6, false);
            return r6;
        }
    }

    private static final class PackageParserV24 {
        private PackageParserV24() {
        }

        static final PackageParser.Package parsePackage(Context context, File file, int i) throws Throwable {
            PackageParser packageParser = new PackageParser();
            PackageParser.Package r7 = packageParser.parsePackage(file, i);
            Reflector.with(packageParser).method("collectCertificates", PackageParser.Package.class, Integer.TYPE).call(r7, Integer.valueOf(i));
            return r7;
        }
    }

    private static final class PackageParserLollipop {
        private PackageParserLollipop() {
        }

        static final PackageParser.Package parsePackage(Context context, File file, int i) throws Throwable {
            PackageParser packageParser = new PackageParser();
            PackageParser.Package r1 = packageParser.parsePackage(file, i);
            packageParser.collectCertificates(r1, i);
            return r1;
        }
    }

    private static final class PackageParserLegacy {
        private PackageParserLegacy() {
        }

        static final PackageParser.Package parsePackage(Context context, File file, int i) throws Throwable {
            PackageParser packageParser = new PackageParser(file.getAbsolutePath());
            PackageParser.Package r6 = packageParser.parsePackage(file, file.getAbsolutePath(), context.getResources().getDisplayMetrics(), i);
            Reflector.with(packageParser).method("collectCertificates", PackageParser.Package.class, Integer.TYPE).call(r6, Integer.valueOf(i));
            return r6;
        }
    }
}
