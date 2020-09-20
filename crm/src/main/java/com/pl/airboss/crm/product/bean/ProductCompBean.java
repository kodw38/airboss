package com.pl.airboss.crm.product.bean;

public class ProductCompBean extends ProductCompBeanKey {
    private String forceTag;

    public String getForceTag() {
        return forceTag;
    }

    public void setForceTag(String forceTag) {
        this.forceTag = forceTag == null ? null : forceTag.trim();
    }
}