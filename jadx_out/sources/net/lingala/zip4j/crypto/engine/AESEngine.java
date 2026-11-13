package net.lingala.zip4j.crypto.engine;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.umeng.commonsdk.proguard.C1770bg;
import java.lang.reflect.Array;
import net.lingala.zip4j.exception.ZipException;

/* JADX INFO: loaded from: classes2.dex */
public class AESEngine {

    /* JADX INFO: renamed from: C0 */
    private int f5038C0;

    /* JADX INFO: renamed from: C1 */
    private int f5039C1;

    /* JADX INFO: renamed from: C2 */
    private int f5040C2;

    /* JADX INFO: renamed from: C3 */
    private int f5041C3;
    private int rounds;
    private int[][] workingKey = (int[][]) null;

    /* JADX INFO: renamed from: S */
    private static final byte[] f5036S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, C1770bg.f4126n, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, C1770bg.f4124l, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, C1770bg.f4123k, -65, -26, 66, 104, 65, -103, 45, C1770bg.f4125m, -80, 84, -69, 22};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, UiMessage.CommandToUi.Command_Type.SET_FW_TEXT_VIEW_VALUE, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, UiMessage.CommandToUi.Command_Type.SET_FONT_VALUE, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};

    /* JADX INFO: renamed from: T0 */
    private static final int[] f5037T0 = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};

    public AESEngine(byte[] key) throws ZipException {
        init(key);
    }

    private void init(byte[] key) throws ZipException {
        this.workingKey = generateWorkingKey(key);
    }

    private int[][] generateWorkingKey(byte[] key) throws ZipException {
        int kc = key.length / 4;
        if ((kc != 4 && kc != 6 && kc != 8) || kc * 4 != key.length) {
            throw new ZipException("invalid key length (not 128/192/256)");
        }
        this.rounds = kc + 6;
        int[][] W = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, this.rounds + 1, 4);
        int t = 0;
        int i = 0;
        while (i < key.length) {
            W[t >> 2][t & 3] = (key[i] & 255) | ((key[i + 1] & 255) << 8) | ((key[i + 2] & 255) << 16) | (key[i + 3] << 24);
            i += 4;
            t++;
        }
        int k = (this.rounds + 1) << 2;
        for (int i2 = kc; i2 < k; i2++) {
            int temp = W[(i2 - 1) >> 2][(i2 - 1) & 3];
            if (i2 % kc == 0) {
                temp = subWord(shift(temp, 8)) ^ rcon[(i2 / kc) - 1];
            } else if (kc > 6 && i2 % kc == 4) {
                temp = subWord(temp);
            }
            W[i2 >> 2][i2 & 3] = W[(i2 - kc) >> 2][(i2 - kc) & 3] ^ temp;
        }
        return W;
    }

    public int processBlock(byte[] in, byte[] out) throws ZipException {
        return processBlock(in, 0, out, 0);
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws ZipException {
        if (this.workingKey == null) {
            throw new ZipException("AES engine not initialised");
        }
        if (inOff + 16 > in.length) {
            throw new ZipException("input buffer too short");
        }
        if (outOff + 16 > out.length) {
            throw new ZipException("output buffer too short");
        }
        stateIn(in, inOff);
        encryptBlock(this.workingKey);
        stateOut(out, outOff);
        return 16;
    }

    private void stateIn(byte[] bytes, int off) {
        int index = off + 1;
        this.f5038C0 = bytes[off] & 255;
        int index2 = index + 1;
        this.f5038C0 |= (bytes[index] & 255) << 8;
        int index3 = index2 + 1;
        this.f5038C0 |= (bytes[index2] & 255) << 16;
        int index4 = index3 + 1;
        this.f5038C0 |= bytes[index3] << 24;
        int index5 = index4 + 1;
        this.f5039C1 = bytes[index4] & 255;
        int index6 = index5 + 1;
        this.f5039C1 |= (bytes[index5] & 255) << 8;
        int index7 = index6 + 1;
        this.f5039C1 |= (bytes[index6] & 255) << 16;
        int index8 = index7 + 1;
        this.f5039C1 |= bytes[index7] << 24;
        int index9 = index8 + 1;
        this.f5040C2 = bytes[index8] & 255;
        int index10 = index9 + 1;
        this.f5040C2 |= (bytes[index9] & 255) << 8;
        int index11 = index10 + 1;
        this.f5040C2 |= (bytes[index10] & 255) << 16;
        int index12 = index11 + 1;
        this.f5040C2 |= bytes[index11] << 24;
        int index13 = index12 + 1;
        this.f5041C3 = bytes[index12] & 255;
        int index14 = index13 + 1;
        this.f5041C3 |= (bytes[index13] & 255) << 8;
        int index15 = index14 + 1;
        this.f5041C3 |= (bytes[index14] & 255) << 16;
        int i = index15 + 1;
        this.f5041C3 |= bytes[index15] << 24;
    }

    private void stateOut(byte[] bytes, int off) {
        int index = off + 1;
        bytes[off] = (byte) this.f5038C0;
        int index2 = index + 1;
        bytes[index] = (byte) (this.f5038C0 >> 8);
        int index3 = index2 + 1;
        bytes[index2] = (byte) (this.f5038C0 >> 16);
        int index4 = index3 + 1;
        bytes[index3] = (byte) (this.f5038C0 >> 24);
        int index5 = index4 + 1;
        bytes[index4] = (byte) this.f5039C1;
        int index6 = index5 + 1;
        bytes[index5] = (byte) (this.f5039C1 >> 8);
        int index7 = index6 + 1;
        bytes[index6] = (byte) (this.f5039C1 >> 16);
        int index8 = index7 + 1;
        bytes[index7] = (byte) (this.f5039C1 >> 24);
        int index9 = index8 + 1;
        bytes[index8] = (byte) this.f5040C2;
        int index10 = index9 + 1;
        bytes[index9] = (byte) (this.f5040C2 >> 8);
        int index11 = index10 + 1;
        bytes[index10] = (byte) (this.f5040C2 >> 16);
        int index12 = index11 + 1;
        bytes[index11] = (byte) (this.f5040C2 >> 24);
        int index13 = index12 + 1;
        bytes[index12] = (byte) this.f5041C3;
        int index14 = index13 + 1;
        bytes[index13] = (byte) (this.f5041C3 >> 8);
        int index15 = index14 + 1;
        bytes[index14] = (byte) (this.f5041C3 >> 16);
        int i = index15 + 1;
        bytes[index15] = (byte) (this.f5041C3 >> 24);
    }

    private void encryptBlock(int[][] KW) {
        this.f5038C0 ^= KW[0][0];
        this.f5039C1 ^= KW[0][1];
        this.f5040C2 ^= KW[0][2];
        this.f5041C3 ^= KW[0][3];
        int r = 1;
        while (r < this.rounds - 1) {
            int r0 = (((f5037T0[this.f5038C0 & 255] ^ shift(f5037T0[(this.f5039C1 >> 8) & 255], 24)) ^ shift(f5037T0[(this.f5040C2 >> 16) & 255], 16)) ^ shift(f5037T0[(this.f5041C3 >> 24) & 255], 8)) ^ KW[r][0];
            int r1 = (((f5037T0[this.f5039C1 & 255] ^ shift(f5037T0[(this.f5040C2 >> 8) & 255], 24)) ^ shift(f5037T0[(this.f5041C3 >> 16) & 255], 16)) ^ shift(f5037T0[(this.f5038C0 >> 24) & 255], 8)) ^ KW[r][1];
            int r2 = (((f5037T0[this.f5040C2 & 255] ^ shift(f5037T0[(this.f5041C3 >> 8) & 255], 24)) ^ shift(f5037T0[(this.f5038C0 >> 16) & 255], 16)) ^ shift(f5037T0[(this.f5039C1 >> 24) & 255], 8)) ^ KW[r][2];
            int r3 = r + 1;
            int r4 = (((f5037T0[this.f5041C3 & 255] ^ shift(f5037T0[(this.f5038C0 >> 8) & 255], 24)) ^ shift(f5037T0[(this.f5039C1 >> 16) & 255], 16)) ^ shift(f5037T0[(this.f5040C2 >> 24) & 255], 8)) ^ KW[r][3];
            this.f5038C0 = (((f5037T0[r0 & 255] ^ shift(f5037T0[(r1 >> 8) & 255], 24)) ^ shift(f5037T0[(r2 >> 16) & 255], 16)) ^ shift(f5037T0[(r4 >> 24) & 255], 8)) ^ KW[r3][0];
            this.f5039C1 = (((f5037T0[r1 & 255] ^ shift(f5037T0[(r2 >> 8) & 255], 24)) ^ shift(f5037T0[(r4 >> 16) & 255], 16)) ^ shift(f5037T0[(r0 >> 24) & 255], 8)) ^ KW[r3][1];
            this.f5040C2 = (((f5037T0[r2 & 255] ^ shift(f5037T0[(r4 >> 8) & 255], 24)) ^ shift(f5037T0[(r0 >> 16) & 255], 16)) ^ shift(f5037T0[(r1 >> 24) & 255], 8)) ^ KW[r3][2];
            r = r3 + 1;
            this.f5041C3 = (((f5037T0[r4 & 255] ^ shift(f5037T0[(r0 >> 8) & 255], 24)) ^ shift(f5037T0[(r1 >> 16) & 255], 16)) ^ shift(f5037T0[(r2 >> 24) & 255], 8)) ^ KW[r3][3];
        }
        int r5 = (((f5037T0[this.f5038C0 & 255] ^ shift(f5037T0[(this.f5039C1 >> 8) & 255], 24)) ^ shift(f5037T0[(this.f5040C2 >> 16) & 255], 16)) ^ shift(f5037T0[(this.f5041C3 >> 24) & 255], 8)) ^ KW[r][0];
        int r6 = (((f5037T0[this.f5039C1 & 255] ^ shift(f5037T0[(this.f5040C2 >> 8) & 255], 24)) ^ shift(f5037T0[(this.f5041C3 >> 16) & 255], 16)) ^ shift(f5037T0[(this.f5038C0 >> 24) & 255], 8)) ^ KW[r][1];
        int r7 = (((f5037T0[this.f5040C2 & 255] ^ shift(f5037T0[(this.f5041C3 >> 8) & 255], 24)) ^ shift(f5037T0[(this.f5038C0 >> 16) & 255], 16)) ^ shift(f5037T0[(this.f5039C1 >> 24) & 255], 8)) ^ KW[r][2];
        int r8 = r + 1;
        int r9 = (((f5037T0[this.f5041C3 & 255] ^ shift(f5037T0[(this.f5038C0 >> 8) & 255], 24)) ^ shift(f5037T0[(this.f5039C1 >> 16) & 255], 16)) ^ shift(f5037T0[(this.f5040C2 >> 24) & 255], 8)) ^ KW[r][3];
        this.f5038C0 = ((((f5036S[r5 & 255] & 255) ^ ((f5036S[(r6 >> 8) & 255] & 255) << 8)) ^ ((f5036S[(r7 >> 16) & 255] & 255) << 16)) ^ (f5036S[(r9 >> 24) & 255] << 24)) ^ KW[r8][0];
        this.f5039C1 = ((((f5036S[r6 & 255] & 255) ^ ((f5036S[(r7 >> 8) & 255] & 255) << 8)) ^ ((f5036S[(r9 >> 16) & 255] & 255) << 16)) ^ (f5036S[(r5 >> 24) & 255] << 24)) ^ KW[r8][1];
        this.f5040C2 = ((((f5036S[r7 & 255] & 255) ^ ((f5036S[(r9 >> 8) & 255] & 255) << 8)) ^ ((f5036S[(r5 >> 16) & 255] & 255) << 16)) ^ (f5036S[(r6 >> 24) & 255] << 24)) ^ KW[r8][2];
        this.f5041C3 = ((((f5036S[r9 & 255] & 255) ^ ((f5036S[(r5 >> 8) & 255] & 255) << 8)) ^ ((f5036S[(r6 >> 16) & 255] & 255) << 16)) ^ (f5036S[(r7 >> 24) & 255] << 24)) ^ KW[r8][3];
    }

    private int shift(int r, int shift) {
        return (r >>> shift) | (r << (-shift));
    }

    private int subWord(int x) {
        return (f5036S[x & 255] & 255) | ((f5036S[(x >> 8) & 255] & 255) << 8) | ((f5036S[(x >> 16) & 255] & 255) << 16) | (f5036S[(x >> 24) & 255] << 24);
    }
}
