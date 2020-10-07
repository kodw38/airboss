package com.pl.airboss.crm.cm.bean;

public class CustGroupBeanWithBLOBs extends CustGroupBean {
    private String pnationalGroupName;

    private String groupContactPhone;

    private String rsrvStr7;

    public String getPnationalGroupName() {
        return pnationalGroupName;
    }

    public void setPnationalGroupName(String pnationalGroupName) {
        this.pnationalGroupName = pnationalGroupName == null ? null : pnationalGroupName.trim();
    }

    public String getGroupContactPhone() {
        return groupContactPhone;
    }

    public void setGroupContactPhone(String groupContactPhone) {
        this.groupContactPhone = groupContactPhone == null ? null : groupContactPhone.trim();
    }

    public String getRsrvStr7() {
        return rsrvStr7;
    }

    public void setRsrvStr7(String rsrvStr7) {
        this.rsrvStr7 = rsrvStr7 == null ? null : rsrvStr7.trim();
    }
}