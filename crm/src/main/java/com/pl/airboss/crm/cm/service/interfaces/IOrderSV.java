package com.pl.airboss.crm.cm.service.interfaces;

import com.pl.airboss.crm.cm.bean.*;

import java.util.List;

public interface IOrderSV {
    public CustPersonBean queryIndividualCustomer(String psptTypeCode, String psptId);

    public List<UserBean> queryUserByCustId(Long custId);

    public List<AccountBean> queryCustAccount(Long custId);

    public List<CustGroupBean> queryCustGroup(CustGroupBean cond);

    public boolean payment(Double fee);

    public Long submitSubscribe(CustPersonBean personBean, String phoneNumber, Integer productId, Long accountId, Long groupCustId, Long groupRoleId, Integer prepayTag,String[] filePaths)throws Exception;



}
