package org.wlf.filedownloader.util;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import net.lingala.zip4j.util.InternalZipConstants;
import org.slf4j.Marker;
import org.wlf.filedownloader.base.Log;

/* JADX INFO: loaded from: classes2.dex */
public class UrlUtil {
    private static final EncodeInfo FRAGMENT_SPLIT = new EncodeInfo("#", "%23");
    private static final EncodeInfo SPACE_SPLIT = new EncodeInfo(Marker.ANY_NON_NULL_MARKER, "%20");
    private static final EncodeInfo[] SPECIAL_CHARACTER_ENCODER_MAP = {SPACE_SPLIT, FRAGMENT_SPLIT, new EncodeInfo(InternalZipConstants.ZIP_FILE_SEPARATOR, "%2F"), new EncodeInfo("?", "%3F"), new EncodeInfo("%", "%25"), new EncodeInfo("&", "%26"), new EncodeInfo("=", "%3D")};

    private static String getEncodedHost(String host, String userInfo, String charset, EncodeInfo[] specials) {
        if (TextUtils.isEmpty(host)) {
            return null;
        }
        StringBuffer bufferEncodedHost = new StringBuffer();
        try {
            String[] splitHost = host.split(".");
            if (!ArrayUtil.isEmpty(splitHost)) {
                for (int i = 0; i < splitHost.length; i++) {
                    String hostSegment = splitHost[i];
                    if (i != 0) {
                        bufferEncodedHost.append(".");
                    }
                    if (!TextUtils.isEmpty(hostSegment)) {
                        if (!isEncoded(hostSegment, charset)) {
                            String encodedHostSegment = URLEncoder.encode(hostSegment, charset);
                            if (!TextUtils.isEmpty(encodedHostSegment)) {
                                if (!ArrayUtil.isEmpty(specials)) {
                                    for (EncodeInfo encodeInfo : specials) {
                                        if (encodeInfo != null && !TextUtils.isEmpty(encodeInfo.needEncode) && !TextUtils.isEmpty(encodeInfo.encoded) && !TextUtils.isEmpty(encodedHostSegment) && encodedHostSegment.contains(encodeInfo.encoded)) {
                                            encodedHostSegment = encodedHostSegment.replace(encodeInfo.encoded, encodeInfo.needEncode);
                                        }
                                    }
                                }
                                bufferEncodedHost.append(encodedHostSegment);
                            }
                        } else {
                            bufferEncodedHost.append(hostSegment);
                        }
                    }
                }
            } else if (!isEncoded(host, charset)) {
                String encodedHost = URLEncoder.encode(host, charset);
                if (!TextUtils.isEmpty(encodedHost)) {
                    if (!ArrayUtil.isEmpty(specials)) {
                        for (EncodeInfo encodeInfo2 : specials) {
                            if (encodeInfo2 != null && !TextUtils.isEmpty(encodeInfo2.needEncode) && !TextUtils.isEmpty(encodeInfo2.encoded) && !TextUtils.isEmpty(encodedHost) && encodedHost.contains(encodeInfo2.encoded)) {
                                encodedHost = encodedHost.replace(encodeInfo2.encoded, encodeInfo2.needEncode);
                            }
                        }
                    }
                    bufferEncodedHost.append(encodedHost);
                }
            } else {
                bufferEncodedHost.append(host);
            }
            if (!TextUtils.isEmpty(userInfo)) {
                bufferEncodedHost.append("@");
                bufferEncodedHost.append(userInfo);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bufferEncodedHost.toString();
    }

    private static String getEncodedFile(String path, String query, String ref, String charset, EncodeInfo[] specials) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        StringBuffer bufferEncodedFile = new StringBuffer();
        try {
            String[] splitPath = path.split(InternalZipConstants.ZIP_FILE_SEPARATOR);
            if (!ArrayUtil.isEmpty(splitPath)) {
                for (int i = 0; i < splitPath.length; i++) {
                    String pathSegment = splitPath[i];
                    if (i != 0) {
                        bufferEncodedFile.append(InternalZipConstants.ZIP_FILE_SEPARATOR);
                    }
                    if (!TextUtils.isEmpty(pathSegment)) {
                        if (!isEncoded(pathSegment, charset)) {
                            String encodedPathSegment = URLEncoder.encode(pathSegment, charset);
                            if (!TextUtils.isEmpty(encodedPathSegment)) {
                                if (!ArrayUtil.isEmpty(specials)) {
                                    for (EncodeInfo encodeInfo : specials) {
                                        if (encodeInfo != null && !TextUtils.isEmpty(encodeInfo.needEncode) && !TextUtils.isEmpty(encodeInfo.encoded) && !TextUtils.isEmpty(encodedPathSegment) && encodedPathSegment.contains(encodeInfo.encoded)) {
                                            encodedPathSegment = encodedPathSegment.replace(encodeInfo.encoded, encodeInfo.needEncode);
                                        }
                                    }
                                }
                                bufferEncodedFile.append(encodedPathSegment);
                            }
                        } else {
                            bufferEncodedFile.append(pathSegment);
                        }
                    }
                }
            } else if (!isEncoded(path, charset)) {
                String encodedPath = URLEncoder.encode(path, charset);
                if (!TextUtils.isEmpty(encodedPath)) {
                    if (!ArrayUtil.isEmpty(specials)) {
                        for (EncodeInfo encodeInfo2 : specials) {
                            if (encodeInfo2 != null && !TextUtils.isEmpty(encodeInfo2.needEncode) && !TextUtils.isEmpty(encodeInfo2.encoded) && !TextUtils.isEmpty(encodedPath) && encodedPath.contains(encodeInfo2.encoded)) {
                                encodedPath = encodedPath.replace(encodeInfo2.encoded, encodeInfo2.needEncode);
                            }
                        }
                    }
                    bufferEncodedFile.append(encodedPath);
                }
            } else {
                bufferEncodedFile.append(path);
            }
            if (!TextUtils.isEmpty(query)) {
                if (query.contains("\\?") || query.contains("&")) {
                    bufferEncodedFile.append("?");
                }
                String[] splitQuery = query.split("&");
                if (!ArrayUtil.isEmpty(splitQuery)) {
                    for (int i2 = 0; i2 < splitQuery.length; i2++) {
                        String querySegment = splitQuery[i2];
                        if (i2 != 0) {
                            bufferEncodedFile.append("&");
                        }
                        if (!TextUtils.isEmpty(querySegment)) {
                            String[] keyValue = querySegment.split("=");
                            if (!ArrayUtil.isEmpty(keyValue) && keyValue.length >= 2) {
                                String key = keyValue[0];
                                String value = keyValue[1];
                                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                                    if (!isEncoded(key, charset)) {
                                        key = URLEncoder.encode(key, charset);
                                        if (!ArrayUtil.isEmpty(specials)) {
                                            for (EncodeInfo encodeInfo3 : specials) {
                                                if (encodeInfo3 != null && !TextUtils.isEmpty(encodeInfo3.needEncode) && !TextUtils.isEmpty(encodeInfo3.encoded) && !TextUtils.isEmpty(key) && key.contains(encodeInfo3.encoded)) {
                                                    key = key.replace(encodeInfo3.encoded, encodeInfo3.needEncode);
                                                }
                                            }
                                        }
                                    }
                                    if (!isEncoded(value, charset)) {
                                        value = URLEncoder.encode(value, charset);
                                        if (!ArrayUtil.isEmpty(specials)) {
                                            for (EncodeInfo encodeInfo4 : specials) {
                                                if (encodeInfo4 != null && !TextUtils.isEmpty(encodeInfo4.needEncode) && !TextUtils.isEmpty(encodeInfo4.encoded) && !TextUtils.isEmpty(value) && value.contains(encodeInfo4.encoded)) {
                                                    value = value.replace(encodeInfo4.encoded, encodeInfo4.needEncode);
                                                }
                                            }
                                        }
                                    }
                                    bufferEncodedFile.append(key);
                                    bufferEncodedFile.append("=");
                                    bufferEncodedFile.append(value);
                                }
                            } else if (!isEncoded(querySegment, charset)) {
                                String encodedQuerySegment = URLEncoder.encode(querySegment, charset);
                                if (!TextUtils.isEmpty(encodedQuerySegment)) {
                                    if (!ArrayUtil.isEmpty(specials)) {
                                        for (EncodeInfo encodeInfo5 : specials) {
                                            if (encodeInfo5 != null && !TextUtils.isEmpty(encodeInfo5.needEncode) && !TextUtils.isEmpty(encodeInfo5.encoded) && !TextUtils.isEmpty(encodedQuerySegment) && encodedQuerySegment.contains(encodeInfo5.encoded)) {
                                                encodedQuerySegment = encodedQuerySegment.replace(encodeInfo5.encoded, encodeInfo5.needEncode);
                                            }
                                        }
                                    }
                                    bufferEncodedFile.append(encodedQuerySegment);
                                }
                            } else {
                                bufferEncodedFile.append(querySegment);
                            }
                        }
                    }
                } else if (!isEncoded(query, charset)) {
                    String encodedQuery = URLEncoder.encode(query, charset);
                    if (!TextUtils.isEmpty(encodedQuery)) {
                        if (!ArrayUtil.isEmpty(specials)) {
                            for (EncodeInfo encodeInfo6 : specials) {
                                if (encodeInfo6 != null && !TextUtils.isEmpty(encodeInfo6.needEncode) && !TextUtils.isEmpty(encodeInfo6.encoded) && !TextUtils.isEmpty(encodedQuery) && encodedQuery.contains(encodeInfo6.encoded)) {
                                    encodedQuery = encodedQuery.replace(encodeInfo6.encoded, encodeInfo6.needEncode);
                                }
                            }
                        }
                        bufferEncodedFile.append(encodedQuery);
                    }
                } else {
                    bufferEncodedFile.append(query);
                }
            }
            if (!TextUtils.isEmpty(ref)) {
                if (!isEncoded(query, charset)) {
                    String encodedRef = URLEncoder.encode(ref, charset);
                    if (!TextUtils.isEmpty(encodedRef)) {
                        bufferEncodedFile.append("#");
                        bufferEncodedFile.append(encodedRef);
                    }
                } else {
                    bufferEncodedFile.append("#");
                    bufferEncodedFile.append(ref);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bufferEncodedFile.toString();
    }

    public static boolean isEncoded(String content, String charset) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        Log.m5156d("wlf", "isEncoded，check content：" + content);
        EncodeInfo[] arr$ = SPECIAL_CHARACTER_ENCODER_MAP;
        for (EncodeInfo info : arr$) {
            if (info != null && content.contains(info.needEncode)) {
                return false;
            }
        }
        String decodedContent = null;
        try {
            decodedContent = URLDecoder.decode(content, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedContent == null || !decodedContent.equalsIgnoreCase(content);
    }

    public static String decode(String content, String charset) {
        try {
            return URLDecoder.decode(content, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getEncodedUrl(String url, String charset, boolean useFragment, boolean blankAsPlus) {
        Log.m5156d("wlf", "getEncodedUrl，--------------------");
        Log.m5156d("wlf", "getEncodedUrl，准备编码URL，url：" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (url != null) {
            url = url.trim();
        }
        String encodedUrl = null;
        try {
            URL unEncodeUrl = new URL(url);
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，unEncodeUrl：" + unEncodeUrl);
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，getProtocol：" + unEncodeUrl.getProtocol());
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，getHost：" + unEncodeUrl.getHost());
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，getUserInfo：" + unEncodeUrl.getUserInfo());
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，getPort：" + unEncodeUrl.getPort());
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，getFile：" + unEncodeUrl.getFile());
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，getPath：" + unEncodeUrl.getPath());
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，getQuery：" + unEncodeUrl.getQuery());
            Log.m5156d("wlf", "getEncodedUrl，开始编码URL，getRef：" + unEncodeUrl.getRef());
            String protocol = unEncodeUrl.getProtocol();
            String host = unEncodeUrl.getHost();
            String userInfo = unEncodeUrl.getUserInfo();
            String host2 = getEncodedHost(host, userInfo, charset, SPECIAL_CHARACTER_ENCODER_MAP);
            int port = unEncodeUrl.getPort();
            unEncodeUrl.getFile();
            String path = unEncodeUrl.getPath();
            String query = unEncodeUrl.getQuery();
            String ref = unEncodeUrl.getRef();
            String file = getEncodedFile(path, query, ref, charset, SPECIAL_CHARACTER_ENCODER_MAP);
            URL tempEncodedUrl = new URL(protocol, host2, port, file);
            encodedUrl = tempEncodedUrl.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (encodedUrl != null && encodedUrl.contains(SPACE_SPLIT.needEncode) && !blankAsPlus) {
            encodedUrl = encodedUrl.replaceAll("\\" + SPACE_SPLIT.needEncode, SPACE_SPLIT.encoded);
        }
        if (encodedUrl != null && encodedUrl.contains(FRAGMENT_SPLIT.needEncode) && !useFragment) {
            encodedUrl = encodedUrl.replaceAll(FRAGMENT_SPLIT.needEncode, FRAGMENT_SPLIT.encoded);
        }
        Log.m5158e("wlf", "getEncodedUrl，编码后URL，encodedUrl：" + encodedUrl);
        return encodedUrl;
    }

    public static boolean isUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        String encodedUrl = getASCIIEncodedUrl(url, "UTF-8");
        return !TextUtils.isEmpty(encodedUrl);
    }

    public static String getASCIIEncodedUrl(String url, String charset) {
        return getEncodedUrl(url, charset, false, false);
    }

    public static String getFileNameByUrl(String url, String charset) {
        String fileName = null;
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (url != null) {
            url = url.trim();
        }
        try {
            if (url.contains(InternalZipConstants.ZIP_FILE_SEPARATOR) && (fileName = url.substring(url.lastIndexOf(47) + 1)) != null && fileName.contains("?")) {
                fileName = fileName.substring(0, fileName.indexOf(63));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(fileName) && isEncoded(fileName, charset)) {
            String decodedContent = decode(fileName, charset);
            if (!TextUtils.isEmpty(decodedContent)) {
                fileName = decodedContent;
            }
        }
        return !TextUtils.isEmpty(fileName) ? fileName : url;
    }

    private static class EncodeInfo {
        public final String encoded;
        public final String needEncode;

        public EncodeInfo(String needEncode, String encoded) {
            this.needEncode = needEncode;
            this.encoded = encoded;
        }
    }
}
