package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class AcctFeepolicyBean extends AcctFeepolicyBeanKey {
    private Long acctId;

    private Integer feepolicyId;

    private Integer productId;

    private Integer packageId;

    private String fpType;

    private String hasParam;

    private Date endDate;

    private Date updateTime;

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Integer getFeepolicyId() {
        return feepolicyId;
    }

    public void setFeepolicyId(Integer feepolicyId) {
        this.feepolicyId = feepolicyId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getFpType() {
        return fpType;
    }

    public void setFpType(String fpType) {
        this.fpType = fpType == null ? null : fpType.trim();
    }

    public String getHasParam() {
        return hasParam;
    }

    public void setHasParam(String hasParam) {
        this.hasParam = hasParam == null ? null : hasParam.trim();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}