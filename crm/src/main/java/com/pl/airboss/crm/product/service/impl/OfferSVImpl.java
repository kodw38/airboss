package com.pl.airboss.crm.product.service.impl;

import com.pl.airboss.crm.product.bean.*;
import com.pl.airboss.crm.product.dao.*;
import com.pl.airboss.crm.product.service.interfaces.IOfferSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    public int addService(ServiceBean bean) {
        return serviceBeanMapper.insert(bean);
    }

    @Override
    public int addServiceParams(List<ServiceParamBean> paramBeans) {
        return serviceParamBeanMapper.insertList(paramBeans);
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
}
