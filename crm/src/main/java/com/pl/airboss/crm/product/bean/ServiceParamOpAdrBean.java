package com.pl.airboss.crm.product.bean;

public class ServiceParamOpAdrBean {
    private Long adrId;

    private Long paramId;

    private String adrType;

    private String adrValue;

    public Long getAdrId() {
        return adrId;
    }

    public void setAdrId(Long adrId) {
        this.adrId = adrId;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public String getAdrType() {
        return adrType;
    }

    public void setAdrType(String adrType) {
        this.adrType = adrType == null ? null : adrType.trim();
    }

    public String getAdrValue() {
        return adrValue;
    }

    public void setAdrValue(String adrValue) {
        this.adrValue = adrValue == null ? null : adrValue.trim();
    }
}