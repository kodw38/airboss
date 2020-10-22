package com.pl.airboss.crm.product.service.impl;

import com.pl.airboss.crm.ac.bean.FeeCycleBean;
import com.pl.airboss.crm.ac.bean.FeeInterfaceBean;
import com.pl.airboss.crm.ac.dao.FeeCycleBeanMapper;
import com.pl.airboss.crm.ac.dao.FeeInterfaceBeanMapper;
import com.pl.airboss.crm.product.bean.*;
import com.pl.airboss.crm.product.dao.*;
import com.pl.airboss.crm.product.service.interfaces.IOfferSV;
import com.pl.airboss.web.utils.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfferSVImpl implements IOfferSV {

    @Autowired
    ProductBeanMapper productBeanMapper;

    @Autowired
    ProductCategroyRelBeanMapper productCategroyRelBeanMapper;

    @Autowired
    ProductCategoryBeanMapper productCategoryBeanMapper;

    @Autowired
    ProductParamBeanMapper productParamBeanMapper;

    @Autowired
    ServiceBeanMapper serviceBeanMapper;

    @Autowired
    ServiceParamBeanMapper serviceParamBeanMapper;

    @Autowired
    ServiceBundleCompBeanMapper serviceBundleCompBeanMapper;

    @Autowired
    ServiceBundleBeanMapper serviceBundleBeanMapper;

    @Autowired
    ProductCompBeanMapper productCompBeanMapper;

    @Autowired
    FeeInterfaceBeanMapper feeInterfaceBeanMapper;

    @Autowired
    FeePolicyBundleBeanMapper feePolicyBundleBeanMapper;

    @Autowired
    FeeCycleBeanMapper feeCycleBeanMapper;

    public List<ServiceBean> queryServices(ServiceBean bean){
        return serviceBeanMapper.select(bean);
    }

    public ServiceBean queryServiceById(int id){
        return serviceBeanMapper.selectByPrimaryKey(id);
    }
    @Override
    public int addService(ServiceBean bean) {
        return serviceBeanMapper.insert(bean);
    }

    @Override
    public int addServiceParams(List<ServiceParamBean> paramBeans) {
        return serviceParamBeanMapper.insertList(paramBeans);
    }
    public int addServiceParam(ServiceParamBean bean){
        return serviceParamBeanMapper.insert(bean);
    }

    @Override
    public List<Ztree> selectFeePolicyBundleTree(FeePolicyBundleBean cond) {
        List<FeePolicyBundleBean> list = feePolicyBundleBeanMapper.selectList(cond);
        List<Ztree> ztrees = initZtree(list);
        return ztrees;
    }
    /**
     * 对象树
     *
     * @param list 列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<FeePolicyBundleBean> list)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (FeePolicyBundleBean data : list)
        {
            Ztree ztree = new Ztree();
            ztree.setId(data.getFeepolicyBundId().longValue());
            ztree.setpId(0L);
            ztree.setName(data.getFeepolicyBundName());
            ztree.setTitle(data.getFeepolicyBundName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public ServiceParamBean queryServiceParam(Long propertyId){
        return serviceParamBeanMapper.selectByPrimaryKey(propertyId);
    }

    @Override
    public int updateService(ServiceBean bean) {
        return serviceBeanMapper.updateByPrimaryKey(bean);
    }

    @Override
    public int updateServiceParam(ServiceParamBean bean) {
        return serviceParamBeanMapper.updateByPrimaryKey(bean);
    }

    @Override
    public int deleteService(Integer serviceId) {
        return serviceBeanMapper.deleteByPrimaryKey(serviceId);
    }

    @Override
    public int deleteServiceParam(Long paramId) {
        return serviceParamBeanMapper.deleteByPrimaryKey(paramId);
    }

    @Override
    @Transactional
    public int updateProductServiceRel(Integer prodId, List<Integer> serviceIds) throws Exception{
        ProductBean prod = productBeanMapper.selectByPrimaryKey(prodId);
        if(null == prod){
            throw new Exception("该产品不存在");
        }
        if(null != serviceIds) {
            for(Integer sid:serviceIds) {
                ServiceBean b = serviceBeanMapper.selectByPrimaryKey(sid);
                if(null == b){
                    throw new Exception("主键"+sid+" 服务不存在");
                }
                ServiceBundleCompBean comp=serviceBundleCompBeanMapper.selectByServiceId(sid);
                //查找服务包，如果不存在则新增，服务包和服务一对一
                Integer compId;
                if(null != comp){
                    compId=comp.getServBundId();
                }else{
                    ServiceBundleCompBean ncomp = new ServiceBundleCompBean();
                    ncomp.setServSpecId(sid);
                    serviceBundleCompBeanMapper.insert(ncomp);
                    compId = ncomp.getServBundId();
                }
                ServiceBundleBean bundle = serviceBundleBeanMapper.selectByPrimaryKey(compId);
                if(null == bundle) {
                    ServiceBundleBean bu = new ServiceBundleBean();
                    bu.setServBundId(compId);
                    bu.setServBundName(b.getServiceName());
                    serviceBundleBeanMapper.insert(bu);
                }
                ProductCompBean pc = productCompBeanMapper.selectByProductIdAndServiceBundleId(prodId,compId);
                if(null == pc){
                    ProductCompBean npc = new ProductCompBean();
                    npc.setProductId(prodId);
                    npc.setServBundId(compId);
                    productCompBeanMapper.insert(npc);
                }
            }
        }
        return 1;
    }

    public int addProductService(Integer productId,Integer serviceId)throws Exception{
        ProductBean prod = productBeanMapper.selectByPrimaryKey(productId);
        if(null == prod){
            throw new Exception("该产品不存在");
        }
                ServiceBean b = serviceBeanMapper.selectByPrimaryKey(serviceId);
                if(null == b){
                    throw new Exception("主键"+serviceId+" 服务不存在");
                }
                ServiceBundleCompBean comp=serviceBundleCompBeanMapper.selectByServiceId(serviceId);
                //查找服务包，如果不存在则新增，服务包和服务一对一
                Integer compId;
                if(null != comp){
                    compId=comp.getServBundId();
                }else{
                    ServiceBundleCompBean ncomp = new ServiceBundleCompBean();
                    ncomp.setServSpecId(serviceId);
                    serviceBundleCompBeanMapper.insert(ncomp);
                    compId = ncomp.getServBundId();
                }
                ServiceBundleBean bundle = serviceBundleBeanMapper.selectByPrimaryKey(compId);
                if(null == bundle) {
                    ServiceBundleBean bu = new ServiceBundleBean();
                    bu.setServBundId(compId);
                    bu.setServBundName(b.getServiceName());
                    serviceBundleBeanMapper.insert(bu);
                }
                ProductCompBean pc = productCompBeanMapper.selectByProductIdAndServiceBundleId(productId,compId);
                if(null == pc){
                    ProductCompBean npc = new ProductCompBean();
                    npc.setProductId(productId);
                    npc.setServBundId(compId);
                    productCompBeanMapper.insert(npc);
                }


        return 1;
    }

    public int deleteProductService(Integer productId,Integer serviceId)throws Exception{
        ProductBean prod = productBeanMapper.selectByPrimaryKey(productId);
        if(null == prod){
            throw new Exception("该产品不存在");
        }
        ServiceBean b = serviceBeanMapper.selectByPrimaryKey(serviceId);
        if(null == b){
            throw new Exception("主键"+serviceId+" 服务不存在");
        }
        ServiceBundleCompBean comp=serviceBundleCompBeanMapper.selectByServiceId(serviceId);
        //查找服务包，如果不存在则新增，服务包和服务一对一
        Integer compId;
        if(null != comp){
            compId=comp.getServBundId();
            ServiceBundleBean bundle = serviceBundleBeanMapper.selectByPrimaryKey(compId);
            ProductCompBean pc = productCompBeanMapper.selectByProductIdAndServiceBundleId(productId,compId);
            if(null != pc) {
                ProductCompBeanKey key = new ProductCompBeanKey();
                key.setProductId(productId);
                key.setServBundId(compId);
                productCompBeanMapper.deleteByPrimaryKey(key);
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<ServiceParamBean> queryServiceParams(Integer serviceId) {
        return serviceParamBeanMapper.selectByServiceId(serviceId);
    }

    @Override
    public Map<String, List<ServiceParamBean>> querySelectServiceParams(Integer serviceId) {
        List<ServiceParamBean> list= queryServiceParams(serviceId);
        Map<String, List<ServiceParamBean>> ret = new HashMap<>();
        for(ServiceParamBean b:list){
            if(!ret.containsKey(b.getParamType())){
                ret.put(b.getParamType(),new ArrayList<>());
            }
            ret.get(b.getParamType()).add(b);
        }
        return ret;
    }

    @Override
    public List<ServiceBean> queryProductService(Integer prodId) {
        return productBeanMapper.selectServices(prodId);
    }

    @Override
    public int addProduct(ProductBean bean) {
        return 0;
    }

    @Override
    public int updateProduct(ProductBean bean) {
        return 0;
    }

    @Override
    public int deleteProduct(Long prodId) {
        return 0;
    }

    @Override
    public ProductBean queryProduct(Long prodId) {
        return null;
    }

    @Override
    public int updateProductAll(ProductBean prod, List<Long> serIds, List<Long> feeIds) {
        return 0;
    }

    @Override
    public List<ProductCategoryBean> queryProductCategory() {
        return null;
    }

    @Override
    public List<ProductBean> queryProductByCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<FeePolicyBean> queryFeePolicyListByProductId(Long prodId) {
        return null;
    }

    public List<FeeInterfaceBean> queryFeeInterface(FeeInterfaceBean cond){
        return feeInterfaceBeanMapper.select(cond);
    }

    public List<FeeInterfaceBean> queryFeeInterfaceByBundleId(Long bundleId){
        return feeInterfaceBeanMapper.selectByBundleId(bundleId);
    }

    public int addFeeInterface(FeeInterfaceBean bean){
        return feeInterfaceBeanMapper.insert(bean);
    }

    public FeeInterfaceBean getFeeInterface(Integer feeId){
        return feeInterfaceBeanMapper.selectByPrimaryKey(feeId);
    }

    public int updateFeeInterface(FeeInterfaceBean bean){
        return feeInterfaceBeanMapper.updateByPrimaryKey(bean);
    }

    public int deleteFeeInterface(Integer recId){
        return feeInterfaceBeanMapper.deleteByPrimaryKey(recId);
    }

    public List<FeeCycleBean> listCycle(){
        return feeCycleBeanMapper.list();
    }

    public List<Ztree> selectProductCategoryTree(){
        List<ProductCategoryBean> list = productCategoryBeanMapper.selectAll();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (ProductCategoryBean data : list)
        {
            Ztree ztree = new Ztree();
            ztree.setId(data.getCategoryId().longValue());
            ztree.setpId(data.getParentId());
            ztree.setName(data.getCategoryName());
            ztree.setTitle(data.getCategoryName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public List<FeeInterfaceBean> queryFeeInterfaceListByProductId(Long productId){
        return productBeanMapper.selectFeeListByProductId(productId);
    }

    public int addProductFee(Integer prodId,Integer feeId){
        FeePolicyBundleBean bean = new FeePolicyBundleBean();
        bean.setProductId(prodId);
        bean.setDiscntCode(feeId);
        return feePolicyBundleBeanMapper.insert(bean);
    }

    public FeePolicyBundleBean getProductFeeBundle(Integer prodId,Integer feeId){
        FeePolicyBundleBean c = new FeePolicyBundleBean();
        c.setDiscntCode(feeId);
        c.setProductId(prodId);
        List<FeePolicyBundleBean> ls = feePolicyBundleBeanMapper.selectList(c);
        if(null != ls && ls.size()==1){
            return ls.get(0);
        }else{
            return null;
        }
    }

    public int deleteFeePolicyBundle(Integer bundleId){
        return feePolicyBundleBeanMapper.deleteByPrimaryKey(bundleId);
    }

    @Override
    public boolean checkServiceUnique(String serviceName, Integer serviceId) {
        return serviceBeanMapper.checkServiceUnique(serviceName,serviceId);
    }

    @Override
    public boolean checkPropertyUnique(Long paramId, String paramName,Long serviceId) {
        return serviceParamBeanMapper.checkPropertyUnique(paramId,paramName,serviceId);
    }
}
