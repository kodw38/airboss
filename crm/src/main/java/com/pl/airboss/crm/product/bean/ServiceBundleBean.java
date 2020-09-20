package com.pl.airboss.crm.product.bean;

public class ServiceBundleBean {
    private Integer servBundId;

    private String servBundName;

    private String mainTag;

    private Integer feepolicyBundId;

    private Short minServiceNum;

    private Short maxServiceNum;

    public Integer getServBundId() {
        return servBundId;
    }

    public void setServBundId(Integer servBundId) {
        this.servBundId = servBundId;
    }

    public String getServBundName() {
        return servBundName;
    }

    public void setServBundName(String servBundName) {
        this.servBundName = servBundName == null ? null : servBundName.trim();
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

    public Short getMinServiceNum() {
        return minServiceNum;
    }

    public void setMinServiceNum(Short minServiceNum) {
        this.minServiceNum = minServiceNum;
    }

    public Short getMaxServiceNum() {
        return maxServiceNum;
    }

    public void setMaxServiceNum(Short maxServiceNum) {
        this.maxServiceNum = maxServiceNum;
    }
}