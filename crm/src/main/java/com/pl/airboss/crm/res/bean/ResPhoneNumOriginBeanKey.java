package com.pl.airboss.crm.res.bean;

public class ResPhoneNumOriginBeanKey {
    private String resId;

    private Long secOrgId;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public Long getSecOrgId() {
        return secOrgId;
    }

    public void setSecOrgId(Long secOrgId) {
        this.secOrgId = secOrgId;
    }
}