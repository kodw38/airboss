package com.pl.airboss.app.bean;

import java.util.Date;

public class ResPhoneNumSegmentBean {
    private Integer recId;

    private String begNum;

    private String endNum;



    private String regionCode;

    private String spCode;

    private Date doneDate;

    private String remark;

    private Integer state;
    private Integer segType;

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getBegNum() {
        return begNum;
    }

    public void setBegNum(String begNum) {
        this.begNum = begNum == null ? null : begNum.trim();
    }

    public String getEndNum() {
        return endNum;
    }

    public void setEndNum(String endNum) {
        this.endNum = endNum == null ? null : endNum.trim();
    }

    public Integer getSegType() {
        return segType;
    }

    public void setSegType(Integer segType) {
        this.segType = segType;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode == null ? null : spCode.trim();
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}