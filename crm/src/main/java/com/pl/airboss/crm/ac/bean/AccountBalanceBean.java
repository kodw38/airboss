package com.pl.airboss.crm.ac.bean;

import java.util.Date;

public class AccountBalanceBean extends AccountBalanceBeanKey {
    private Long acctBalance;

    private Long creditBalance;

    private Long realFee;

    private Date updateTime;

    private Date expireTime;

    public Long getAcctBalance() {
        return acctBalance;
    }

    public void setAcctBalance(Long acctBalance) {
        this.acctBalance = acctBalance;
    }

    public Long getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(Long creditBalance) {
        this.creditBalance = creditBalance;
    }

    public Long getRealFee() {
        return realFee;
    }

    public void setRealFee(Long realFee) {
        this.realFee = realFee;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}