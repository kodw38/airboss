package com.pl.airboss.web.service.impl;

import com.pl.airboss.utils.StringUtils;
import com.pl.airboss.web.bean.SecOrganizeBean;
import com.pl.airboss.web.utils.Ztree;
import com.pl.airboss.web.dao.SecOrganizeBeanMapper;
import com.pl.airboss.framework.bean.BusinessException;
import com.pl.airboss.web.bean.UserConstants;
import com.pl.airboss.web.service.ISysDeptService;
import com.pl.airboss.web.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 部门管理 服务实现
 * 
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService
{
    @Autowired
    private SecOrganizeBeanMapper deptMapper;

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    //@DataScope(deptAlias = "d")
    public List<SecOrganizeBean> selectDeptList(SecOrganizeBean dept)
    {
        return deptMapper.selectOrganizeList(dept);
    }

    /**
     * 查询部门管理树
     * 
     * @param dept 部门信息
     * @return 所有部门信息
     */
    @Override
    //@DataScope(deptAlias = "d")
    public List<Ztree> selectDeptTree(SecOrganizeBean dept)
    {
        List<SecOrganizeBean> deptList = deptMapper.selectOrganizeList(dept);
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }

    /**
     * 根据角色ID查询部门（数据权限）
     *
     * @param role 角色对象
     * @return 部门列表（数据权限）
     */
    /*@Override
    public List<Ztree> roleDeptTreeData(SysRole role)
    {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysDept> deptList = selectDeptList(new SysDept());
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleDeptList = deptMapper.selectRoleDeptTree(roleId);
            ztrees = initZtree(deptList, roleDeptList);
        }
        else
        {
            ztrees = initZtree(deptList);
        }
        return ztrees;
    }*/

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SecOrganizeBean> deptList)
    {
        return initZtree(deptList, null);
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SecOrganizeBean> deptList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (SecOrganizeBean dept : deptList)
        {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(dept.getRecId().longValue());
                ztree.setpId(dept.getParentId().longValue());
                ztree.setName(dept.getOrganizeName());
                ztree.setTitle(dept.getOrganizeName());
                if (isCheck)
                {
                    ztree.setChecked(roleDeptList.contains(dept.getRecId() + dept.getOrganizeName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询部门人数
     * 
     * @param parentId 部门ID
     * @return 结果
     */
    @Override
    public int selectDeptCount(Long parentId)
    {
        SecOrganizeBean dept = new SecOrganizeBean();
        dept.setParentId(parentId.intValue());
        return deptMapper.selectOrganizeCount(dept);
    }

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId)
    {
        int result = deptMapper.checkOrganizeExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId)
    {
        return deptMapper.deleteByPrimaryKey(deptId.intValue());
    }

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(SecOrganizeBean dept)
    {
        SecOrganizeBean info = deptMapper.selectByPrimaryKey(dept.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new BusinessException("部门停用，不允许新增");
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        dept.setCreateDate(new Date());
        dept.setDoneDate(new Date());
        dept.setOpId(ShiroUtils.getUserId());
        dept.setStatus(1);
        return deptMapper.insert(dept);
    }

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDept(SecOrganizeBean dept)
    {
        SecOrganizeBean newParentDept = deptMapper.selectByPrimaryKey(dept.getParentId());
        SecOrganizeBean oldDept = selectDeptById(dept.getRecId().longValue());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getRecId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getRecId().longValue(), newAncestors, oldAncestors);
        }
        dept.setDoneDate(new Date());
        dept.setOpId(ShiroUtils.getUserId());
        int result = deptMapper.updateByPrimaryKey(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return result;
    }

    /**
     * 修改该部门的父级部门状态
     * 
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SecOrganizeBean dept)
    {
        //String updateBy = dept.getUpdateBy();
        dept = deptMapper.selectByPrimaryKey(dept.getRecId());
        //dept.setUpdateBy(updateBy);
        deptMapper.updateByPrimaryKey(dept);
    }

    /**
     * 修改子元素关系
     * 
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors)
    {
        List<SecOrganizeBean> children = deptMapper.selectChildrenOrganizeById(deptId);
        for (SecOrganizeBean child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            deptMapper.updateOrganizeChildren(children);
        }
    }

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SecOrganizeBean selectDeptById(Long deptId)
    {
        SecOrganizeBean ret= deptMapper.selectByPrimaryKey(deptId.intValue());
        if(null != ret){
            SecOrganizeBean parent = deptMapper.selectByPrimaryKey(ret.getParentId());
            if(null != parent) {
                ret.setParentName(parent.getOrganizeName());
            }
        }
        return ret;
    }

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public Boolean checkDeptNameUnique(SecOrganizeBean dept)
    {
        return deptMapper.checkOrganizeNameUnique(dept.getOrganizeName(), dept.getParentId());

    }
}
