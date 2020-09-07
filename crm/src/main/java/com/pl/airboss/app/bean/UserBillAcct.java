package com.pl.airboss.app.bean;/**
 * Created by admin on 2020/5/26.
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ClassName UserBillAcct
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/5/26 10:40
 * @Version 1.0
 **/
public class UserBillAcct {
    private String billId;

    private Integer cycleType;

    private String billCycle;

    private Integer sumSms;
    private Integer sumBytes;

    private Integer totalFee;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Integer adjustNum;


    private Integer status;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public String getBillCycle() {
        return billCycle;
    }

    public void setBillCycle(String billCycle) {
        this.billCycle = billCycle;
    }

    public Integer getSumSms() {
        return sumSms;
    }

    public void setSumSms(Integer sumSms) {
        this.sumSms = sumSms;
    }

    public Integer getSumBytes() {
        return sumBytes;
    }

    public void setSumBytes(Integer sumBytes) {
        this.sumBytes = sumBytes;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAdjustNum() {
        return adjustNum;
    }

    public void setAdjustNum(Integer adjustNum) {
        this.adjustNum = adjustNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
