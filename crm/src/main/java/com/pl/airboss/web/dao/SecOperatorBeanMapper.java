package com.pl.airboss.web.dao;

import com.pl.airboss.web.bean.SecOperatorBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SecOperatorBeanMapper {
    int deleteByPrimaryKey(Integer recId);

    int insert(SecOperatorBean record);

    int insertSelective(SecOperatorBean record);

    SecOperatorBean selectByPrimaryKey(Integer recId);

    int updateByPrimaryKeySelective(SecOperatorBean record);

    int updateByPrimaryKey(SecOperatorBean record);
    int changeStatus(SecOperatorBean record);

    SecOperatorBean selectUserByLoginCode(String opCode);
    SecOperatorBean selectUserByPhoneNumber(String phoneNum);
    SecOperatorBean selectUserByEmail(String email);

    boolean checkLoginNameUnique(String opCode);

    List<SecOperatorBean> selectAllocatedList(SecOperatorBean bean);
    List<SecOperatorBean> selectUnallocatedList(SecOperatorBean bean);
    List<SecOperatorBean> selectList(SecOperatorBean bean);
    List<SecOperatorBean> selectAvailableList(SecOperatorBean bean);

    Boolean checkLoginCodeExist(String code);
    Boolean checkEmailExist(@Param("email")String email,@Param("recId") Integer recId);
    Boolean checkPhoneNumExist(@Param("phoneNum") String phoneNum, @Param("recId") Integer recId);
    int deleteUserByIds(Long[] ids);

    SecOperatorBean selectWithOrgNameByOpId(Integer opId);

}