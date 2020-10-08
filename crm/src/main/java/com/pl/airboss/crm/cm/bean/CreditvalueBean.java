package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class CreditvalueBean {
    private Long creditValueId;

    private Integer partitionId;

    private Long userId;

    private Long creditValue;

    private Date startDate;

    private Date endDate;

    private Date updateTime;

    public Long getCreditValueId() {
        return creditValueId;
    }

    public void setCreditValueId(Long creditValueId) {
        this.creditValueId = creditValueId;
    }

    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(Long creditValue) {
        this.creditValue = creditValue;
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