package com.pl.airboss.crm.product.bean;

public class ServiceBundleCompBean extends ServiceBundleCompBeanKey {
    private String forceTag;

    private String mainTag;

    private Integer feepolicyBundId;

    public String getForceTag() {
        return forceTag;
    }

    public void setForceTag(String forceTag) {
        this.forceTag = forceTag == null ? null : forceTag.trim();
    }

    public String getMainTag() {
        return mainTag;
    }

    public void setMainTag(String mainTag) {
        this.mainTag = mainTag == null ? null : mainTag.trim();
    }

    public Integer getFeepolicyBundId() {
        return feepolicyBundId;
    }

    public void setFeepolicyBundId(Integer feepolicyBundId) {
        this.feepolicyBundId = feepolicyBundId;
    }
}