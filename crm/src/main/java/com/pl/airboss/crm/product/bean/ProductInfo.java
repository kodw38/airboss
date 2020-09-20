package com.pl.airboss.crm.product.bean;

public class ProductInfo {
    private Long relId;

    private Long itemId;  //产品或目录编号

    private int  itemType; //是目录还是产品

    private  Long parentRelId;//上级编号

    private String name;//名称

    private String remark;//说明

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
        this.relId = relId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Long getParentRelId() {
        return parentRelId;
    }

    public void setParentRelId(Long parentRelId) {
        this.parentRelId = parentRelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
