package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class UserServBean {
    private Long servInsId;

    private Short partitionId;

    private Long userId;

    private Integer servId;

    private Date priorOrderTime;

    private String mainTag;

    private Integer productId;

    private Integer servBundId;

    private Date startDate;

    private Date endDate;

    private Date updateTime;

    private String updateDepartId;

    private String updateStaffId;

    public Long getServInsId() {
        return servInsId;
    }

    public void setServInsId(Long servInsId) {
        this.servInsId = servInsId;
    }

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

    public Date getPriorOrderTime() {
        return priorOrderTime;
    }

    public void setPriorOrderTime(Date priorOrderTime) {
        this.priorOrderTime = priorOrderTime;
    }

    public String getMainTag() {
        return mainTag;
    }

    public void setMainTag(String mainTag) {
        this.mainTag = mainTag == null ? null : mainTag.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getServBundId() {
        return servBundId;
    }

    public void setServBundId(Integer servBundId) {
        this.servBundId = servBundId;
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
}