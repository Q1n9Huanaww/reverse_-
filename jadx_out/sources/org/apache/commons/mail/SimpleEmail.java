package org.apache.commons.mail;

/* JADX INFO: loaded from: classes.dex */
public class SimpleEmail extends Email {
    @Override // org.apache.commons.mail.Email
    public Email setMsg(String str) throws EmailException {
        if (EmailUtils.isEmpty(str)) {
            throw new EmailException("Invalid message supplied");
        }
        setContent(str, "text/plain");
        return this;
    }
}
