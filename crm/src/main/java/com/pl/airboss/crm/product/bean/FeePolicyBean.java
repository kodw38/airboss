package com.pl.airboss.crm.product.bean;

public class FeePolicyBean extends FeePolicyBeanKey {
    private String feepolicyExplain;

    public String getFeepolicyExplain() {
        return feepolicyExplain;
    }

    public void setFeepolicyExplain(String feepolicyExplain) {
        this.feepolicyExplain = feepolicyExplain == null ? null : feepolicyExplain.trim();
    }
}