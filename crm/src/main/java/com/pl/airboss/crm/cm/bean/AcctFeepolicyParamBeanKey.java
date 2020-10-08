package com.pl.airboss.crm.cm.bean;

import java.util.Date;

public class AcctFeepolicyParamBeanKey {
    private Long feepolicyInsId;

    private Integer feepolicyParamId;

    private Date startDate;

    public Long getFeepolicyInsId() {
        return feepolicyInsId;
    }

    public void setFeepolicyInsId(Long feepolicyInsId) {
        this.feepolicyInsId = feepolicyInsId;
    }

    public Integer getFeepolicyParamId() {
        return feepolicyParamId;
    }

    public void setFeepolicyParamId(Integer feepolicyParamId) {
        this.feepolicyParamId = feepolicyParamId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}