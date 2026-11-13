package org.apache.commons.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.DataSource;

/* JADX INFO: loaded from: classes.dex */
public class ImageHtmlEmail extends HtmlEmail {
    private DataSourceResolver dataSourceResolver;
    public static final String REGEX_IMG_SRC = "(<[Ii][Mm][Gg]\\s*[^>]*?\\s+[Ss][Rr][Cc]\\s*=\\s*[\"'])([^\"']+?)([\"'])";
    private static final Pattern IMG_PATTERN = Pattern.compile(REGEX_IMG_SRC);
    public static final String REGEX_SCRIPT_SRC = "(<[Ss][Cc][Rr][Ii][Pp][Tt]\\s*.*?\\s+[Ss][Rr][Cc]\\s*=\\s*[\"'])([^\"']+?)([\"'])";
    private static final Pattern SCRIPT_PATTERN = Pattern.compile(REGEX_SCRIPT_SRC);

    public DataSourceResolver getDataSourceResolver() {
        return this.dataSourceResolver;
    }

    public void setDataSourceResolver(DataSourceResolver dataSourceResolver) {
        this.dataSourceResolver = dataSourceResolver;
    }

    @Override // org.apache.commons.mail.HtmlEmail, org.apache.commons.mail.MultiPartEmail, org.apache.commons.mail.Email
    public void buildMimeMessage() throws EmailException {
        try {
            setHtmlMsg(replacePattern(replacePattern(this.html, IMG_PATTERN), SCRIPT_PATTERN));
            super.buildMimeMessage();
        } catch (IOException e) {
            throw new EmailException("Building the MimeMessage failed", e);
        }
    }

    private String replacePattern(String str, Pattern pattern) throws EmailException, IOException {
        DataSource dataSourceResolve;
        StringBuffer stringBuffer = new StringBuffer();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String strGroup = matcher.group(2);
            if (map2.get(strGroup) == null) {
                dataSourceResolve = getDataSourceResolver().resolve(strGroup);
                if (dataSourceResolve != null) {
                    map2.put(strGroup, dataSourceResolve);
                }
            } else {
                dataSourceResolve = (DataSource) map2.get(strGroup);
            }
            if (dataSourceResolve != null) {
                String name = dataSourceResolve.getName();
                if (!EmailUtils.isEmpty(name)) {
                    strGroup = name;
                }
                String strEmbed = (String) map.get(strGroup);
                if (strEmbed == null) {
                    strEmbed = embed(dataSourceResolve, strGroup);
                    map.put(strGroup, strEmbed);
                }
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(matcher.group(1) + "cid:" + strEmbed + matcher.group(3)));
            }
        }
        matcher.appendTail(stringBuffer);
        map.clear();
        map2.clear();
        return stringBuffer.toString();
    }
}
