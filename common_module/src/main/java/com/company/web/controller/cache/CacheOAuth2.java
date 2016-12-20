package com.company.web.controller.cache;

import java.io.Serializable;

/**
 *
 */
public class CacheOAuth2 implements Serializable {

    private static final long serialVersionUID = 1986558416476641640L;

    private String client_id;
    private Long memberId;
    private String user_id;//mobile
    private String version;
    private String lang;
    private String timezone;

    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public String getClient_id() {
        return client_id;
    }
    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }

}
