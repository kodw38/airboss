package com.pl.airboss.crm.cm.service.interfaces;

import com.pl.airboss.crm.cm.bean.*;

import java.util.List;

public interface IOrderSV {
    public CustPersonBean queryIndividualCustomer(String psptTypeCode, String psptId);
    public CustGroupBean queryGroupCustomer(String psptTypeCode, String psptId);

    public List<UserBean> queryUserByCustId(Long custId);
    public List<UserBean> queryGroupUserByGroupCustId(Long custId);

    public List<AccountBean> queryCustAccount(Long custId);

    public List<CustGroupBean> queryCustGroup(CustGroupBean cond);

    public boolean payment(Double fee);

    public Long submitSubscribe(CustPersonBean personBean, String phoneNumber, Integer productId, Long accountId, Long groupCustId, Long groupRoleId, Integer prepayTag,String[] filePaths)throws Exception;

    public List<UserBean> queryUser(UserBean bean);

    public int addUser2Group(Long userId,Long groupId,Long accountId,String roleType)throws Exception;

    public int addAccount(AccountBean bean);

    public int addCustGroup(CustGroupBean bean);
    public int removeGroupCust(Long custId)throws Exception;


    public List<CustGroupBean> queryGroupCustomer(CustGroupBean bean);

    boolean checkGroupCustomerUnique(Long custId, String psptTypeCode, String psptId);

    int updateGroupCustomer(CustGroupBean bean);
}
