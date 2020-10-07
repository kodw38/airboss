package com.pl.airboss.crm.product.bean;

public class FeePolicyBundleBean {
    private Integer feepolicyBundId;

    private String feepolicyBundName;

    private Short minFeepolicyNum;

    private Short maxFeepolicyNum;

    private Integer discntCode;

    private Integer productId;

    public Integer getDiscntCode() {
        return discntCode;
    }

    public void setDiscntCode(Integer discntCode) {
        this.discntCode = discntCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getFeepolicyBundId() {
        return feepolicyBundId;
    }

    public void setFeepolicyBundId(Integer feepolicyBundId) {
        this.feepolicyBundId = feepolicyBundId;
    }

    public String getFeepolicyBundName() {
        return feepolicyBundName;
    }

    public void setFeepolicyBundName(String feepolicyBundName) {
        this.feepolicyBundName = feepolicyBundName == null ? null : feepolicyBundName.trim();
    }

    public Short getMinFeepolicyNum() {
        return minFeepolicyNum;
    }

    public void setMinFeepolicyNum(Short minFeepolicyNum) {
        this.minFeepolicyNum = minFeepolicyNum;
    }

    public Short getMaxFeepolicyNum() {
        return maxFeepolicyNum;
    }

    public void setMaxFeepolicyNum(Short maxFeepolicyNum) {
        this.maxFeepolicyNum = maxFeepolicyNum;
    }
}