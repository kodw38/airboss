package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class FeepolicyBean {
    private Integer partitionId;

    private Long feepolicyInsId;

    private Long userId;

    private Integer feepolicyId;

    private Integer productId;

    private Integer packageId;

    private Short fpType;

    private Long relaId;

    private String sameAcct;

    private String hasParam;

    private Long outFeepolicyInsId;

    private Date startDate;

    private Date endDate;

    private Date updateTime;

    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }

    public Long getFeepolicyInsId() {
        return feepolicyInsId;
    }

    public void setFeepolicyInsId(Long feepolicyInsId) {
        this.feepolicyInsId = feepolicyInsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Short getFpType() {
        return fpType;
    }

    public void setFpType(Short fpType) {
        this.fpType = fpType;
    }

    public Long getRelaId() {
        return relaId;
    }

    public void setRelaId(Long relaId) {
        this.relaId = relaId;
    }

    public String getSameAcct() {
        return sameAcct;
    }

    public void setSameAcct(String sameAcct) {
        this.sameAcct = sameAcct == null ? null : sameAcct.trim();
    }

    public String getHasParam() {
        return hasParam;
    }

    public void setHasParam(String hasParam) {
        this.hasParam = hasParam == null ? null : hasParam.trim();
    }

    public Long getOutFeepolicyInsId() {
        return outFeepolicyInsId;
    }

    public void setOutFeepolicyInsId(Long outFeepolicyInsId) {
        this.outFeepolicyInsId = outFeepolicyInsId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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