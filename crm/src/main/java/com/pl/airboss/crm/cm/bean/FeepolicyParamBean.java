package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class FeepolicyParamBean {
    private Integer partitionId;

    private Long feepolicyInsId;

    private Long userId;

    private Long feepolicyId;

    private Integer feepolicyParamId;

    private String feepolicyParamValue;

    private Date updateTime;

    private Date startDate;

    private Date endDate;

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

    public Long getFeepolicyId() {
        return feepolicyId;
    }

    public void setFeepolicyId(Long feepolicyId) {
        this.feepolicyId = feepolicyId;
    }

    public Integer getFeepolicyParamId() {
        return feepolicyParamId;
    }

    public void setFeepolicyParamId(Integer feepolicyParamId) {
        this.feepolicyParamId = feepolicyParamId;
    }

    public String getFeepolicyParamValue() {
        return feepolicyParamValue;
    }

    public void setFeepolicyParamValue(String feepolicyParamValue) {
        this.feepolicyParamValue = feepolicyParamValue == null ? null : feepolicyParamValue.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}