package com.google.p028a.p047g;

import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.google.p028a.p033b.p034a.C1173k;
import com.p024ds.daisi.fragment.SplashFragment;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.google.a.g.m */
/* JADX INFO: compiled from: EANManufacturerOrgSupport.java */
/* JADX INFO: loaded from: classes.dex */
final class C1289m {

    /* JADX INFO: renamed from: a */
    final List<int[]> f2501a = new ArrayList();

    /* JADX INFO: renamed from: b */
    final List<String> f2502b = new ArrayList();

    C1289m() {
    }

    /* JADX INFO: renamed from: a */
    private String m2182a(String str) {
        int[] iArr;
        int i;
        m2184a();
        int i2 = Integer.parseInt(str.substring(0, 3));
        int size = this.f2501a.size();
        for (int i3 = 0; i3 < size && i2 >= (i = (iArr = this.f2501a.get(i3))[0]); i3++) {
            if (iArr.length != 1) {
                i = iArr[1];
            }
            if (i2 <= i) {
                return this.f2502b.get(i3);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private void m2183a(int[] iArr, String str) {
        this.f2501a.add(iArr);
        this.f2502b.add(str);
    }

    /* JADX INFO: renamed from: a */
    final synchronized void m2184a() {
        if (this.f2501a.isEmpty()) {
            m2183a(new int[]{0, 19}, "US/CA");
            m2183a(new int[]{30, 39}, "US");
            m2183a(new int[]{60, 139}, "US/CA");
            m2183a(new int[]{300, 379}, "FR");
            m2183a(new int[]{380}, "BG");
            m2183a(new int[]{383}, "SI");
            m2183a(new int[]{385}, "HR");
            m2183a(new int[]{387}, "BA");
            m2183a(new int[]{400, 440}, "DE");
            m2183a(new int[]{450, 459}, "JP");
            m2183a(new int[]{460, 469}, "RU");
            m2183a(new int[]{471}, "TW");
            m2183a(new int[]{474}, "EE");
            m2183a(new int[]{475}, "LV");
            m2183a(new int[]{476}, "AZ");
            m2183a(new int[]{477}, "LT");
            m2183a(new int[]{478}, "UZ");
            m2183a(new int[]{479}, "LK");
            m2183a(new int[]{480}, "PH");
            m2183a(new int[]{481}, "BY");
            m2183a(new int[]{482}, "UA");
            m2183a(new int[]{484}, "MD");
            m2183a(new int[]{485}, "AM");
            m2183a(new int[]{486}, "GE");
            m2183a(new int[]{487}, "KZ");
            m2183a(new int[]{489}, "HK");
            m2183a(new int[]{490, 499}, "JP");
            m2183a(new int[]{SplashFragment.DELAY_500_MILLIS, 509}, "GB");
            m2183a(new int[]{520}, "GR");
            m2183a(new int[]{528}, C1173k.f2012b);
            m2183a(new int[]{529}, "CY");
            m2183a(new int[]{531}, "MK");
            m2183a(new int[]{535}, "MT");
            m2183a(new int[]{539}, "IE");
            m2183a(new int[]{540, 549}, "BE/LU");
            m2183a(new int[]{560}, "PT");
            m2183a(new int[]{569}, "IS");
            m2183a(new int[]{570, 579}, "DK");
            m2183a(new int[]{590}, "PL");
            m2183a(new int[]{594}, "RO");
            m2183a(new int[]{599}, "HU");
            m2183a(new int[]{600, UiMessage.CommandToUi.Command_Type.LOAD_PROFILE_VALUE}, "ZA");
            m2183a(new int[]{UiMessage.CommandToUi.Command_Type.SET_FW_SMOOTH_VALUE}, "GH");
            m2183a(new int[]{UiMessage.CommandToUi.Command_Type.FW_SET_LEFT_VALUE}, "BH");
            m2183a(new int[]{UiMessage.CommandToUi.Command_Type.FW_SET_TOP_VALUE}, "MU");
            m2183a(new int[]{UiMessage.CommandToUi.Command_Type.FW_SET_HEIGHT_VALUE}, "MA");
            m2183a(new int[]{613}, "DZ");
            m2183a(new int[]{616}, "KE");
            m2183a(new int[]{618}, "CI");
            m2183a(new int[]{619}, "TN");
            m2183a(new int[]{621}, "SY");
            m2183a(new int[]{622}, "EG");
            m2183a(new int[]{624}, "LY");
            m2183a(new int[]{625}, "JO");
            m2183a(new int[]{626}, "IR");
            m2183a(new int[]{627}, "KW");
            m2183a(new int[]{628}, "SA");
            m2183a(new int[]{629}, "AE");
            m2183a(new int[]{640, 649}, "FI");
            m2183a(new int[]{690, 695}, "CN");
            m2183a(new int[]{700, 709}, "NO");
            m2183a(new int[]{729}, "IL");
            m2183a(new int[]{730, 739}, "SE");
            m2183a(new int[]{740}, "GT");
            m2183a(new int[]{741}, "SV");
            m2183a(new int[]{742}, "HN");
            m2183a(new int[]{743}, "NI");
            m2183a(new int[]{744}, "CR");
            m2183a(new int[]{745}, "PA");
            m2183a(new int[]{746}, "DO");
            m2183a(new int[]{750}, "MX");
            m2183a(new int[]{754, 755}, "CA");
            m2183a(new int[]{759}, "VE");
            m2183a(new int[]{760, 769}, "CH");
            m2183a(new int[]{770}, "CO");
            m2183a(new int[]{773}, "UY");
            m2183a(new int[]{775}, "PE");
            m2183a(new int[]{777}, "BO");
            m2183a(new int[]{779}, "AR");
            m2183a(new int[]{780}, "CL");
            m2183a(new int[]{784}, "PY");
            m2183a(new int[]{785}, "PE");
            m2183a(new int[]{786}, "EC");
            m2183a(new int[]{789, 790}, "BR");
            m2183a(new int[]{800, 839}, "IT");
            m2183a(new int[]{840, 849}, "ES");
            m2183a(new int[]{850}, "CU");
            m2183a(new int[]{858}, "SK");
            m2183a(new int[]{859}, "CZ");
            m2183a(new int[]{860}, "YU");
            m2183a(new int[]{865}, "MN");
            m2183a(new int[]{867}, "KP");
            m2183a(new int[]{868, 869}, "TR");
            m2183a(new int[]{870, 879}, "NL");
            m2183a(new int[]{880}, "KR");
            m2183a(new int[]{885}, "TH");
            m2183a(new int[]{888}, "SG");
            m2183a(new int[]{890}, "IN");
            m2183a(new int[]{893}, "VN");
            m2183a(new int[]{896}, "PK");
            m2183a(new int[]{899}, "ID");
            m2183a(new int[]{900, 919}, "AT");
            m2183a(new int[]{930, 939}, "AU");
            m2183a(new int[]{940, 949}, "AZ");
            m2183a(new int[]{955}, "MY");
            m2183a(new int[]{958}, "MO");
        }
    }
}
