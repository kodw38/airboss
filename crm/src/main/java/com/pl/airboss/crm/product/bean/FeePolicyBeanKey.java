package com.pl.airboss.crm.product.bean;

public class FeePolicyBeanKey {
    private Integer servId;

    private String brandCode;

    public Integer getServId() {
        return servId;
    }

    public void setServId(Integer servId) {
        this.servId = servId;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }
}