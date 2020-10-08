package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class UserServstateBean extends UserServstateBeanKey {
    private Short partitionId;

    private Long userId;

    private Integer servId;

    private String mainTag;

    private String servStateCode;

    private Date endDate;

    private String updateDepartId;

    private String updateStaffId;

    private Date updateTime;

    public Short getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Short partitionId) {
        this.partitionId = partitionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getServId() {
        return servId;
    }

    public void setServId(Integer servId) {
        this.servId = servId;
    }

    public String getMainTag() {
        return mainTag;
    }

    public void setMainTag(String mainTag) {
        this.mainTag = mainTag == null ? null : mainTag.trim();
    }

    public String getServStateCode() {
        return servStateCode;
    }

    public void setServStateCode(String servStateCode) {
        this.servStateCode = servStateCode == null ? null : servStateCode.trim();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUpdateDepartId() {
        return updateDepartId;
    }

    public void setUpdateDepartId(String updateDepartId) {
        this.updateDepartId = updateDepartId == null ? null : updateDepartId.trim();
    }

    public String getUpdateStaffId() {
        return updateStaffId;
    }

    public void setUpdateStaffId(String updateStaffId) {
        this.updateStaffId = updateStaffId == null ? null : updateStaffId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}