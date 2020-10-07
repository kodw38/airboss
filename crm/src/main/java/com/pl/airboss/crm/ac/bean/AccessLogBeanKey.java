package com.pl.airboss.crm.ac.bean;

public class AccessLogBeanKey {
    private Long accessId;

    private Integer partitionId;

    public Long getAccessId() {
        return accessId;
    }

    public void setAccessId(Long accessId) {
        this.accessId = accessId;
    }

    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }
}