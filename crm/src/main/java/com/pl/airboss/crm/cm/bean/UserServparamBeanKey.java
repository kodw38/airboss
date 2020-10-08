package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class UserServparamBeanKey {
    private Long servInsId;

    private Integer servParamId;

    private Date startDate;

    public Long getServInsId() {
        return servInsId;
    }

    public void setServInsId(Long servInsId) {
        this.servInsId = servInsId;
    }

    public Integer getServParamId() {
        return servParamId;
    }

    public void setServParamId(Integer servParamId) {
        this.servParamId = servParamId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}