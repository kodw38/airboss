package com.pl.airboss.web.dao;

import com.pl.airboss.web.bean.SecLoginOnlineBean;
import com.pl.airboss.web.bean.SecLoginOnlineBeanExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SecLoginOnlineBeanMapper {
    int deleteByPrimaryKey(String sessionId);

    int insert(SecLoginOnlineBean record);

    int insertSelective(SecLoginOnlineBean record);

    List<SecLoginOnlineBean> selectByExample(SecLoginOnlineBeanExample example);

    SecLoginOnlineBean selectByPrimaryKey(String sessionId);

    int updateByPrimaryKeySelective(SecLoginOnlineBean record);

    int updateByPrimaryKey(SecLoginOnlineBean record);

    /**
     * 查询会话集合
     *
     * @param expiredDate 有效期
     * @return 会话集合
     */
    public List<SecLoginOnlineBean> selectOnlineByExpired(Date expiredDate);

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    public void batchDeleteOnline(@Param("sessions") List<String> sessions);

    int save(SecLoginOnlineBean bean);

    List<SecLoginOnlineBean> selectList(SecLoginOnlineBean bean);

    int saveOnline(SecLoginOnlineBean bean);
}