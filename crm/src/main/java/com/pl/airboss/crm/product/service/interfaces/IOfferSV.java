package com.pl.airboss.crm.product.service.interfaces;

import com.pl.airboss.crm.product.bean.*;

import javax.xml.ws.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宽带速率等属性放在用户的属性中，根据选择的产品是否包含某个服务，选择服务属性，放在用户订购数据中。
 */
public interface IOfferSV {

    /**
     * 增加服务
     * @param bean
     * @return
     */
    public int addService(ServiceBean bean);

    /**
     * 增加服务属性
     * @param paramBeans
     * @return
     */
    public int addServiceParams(List<ServiceParamBean> paramBeans);

    /**
     * 跟新服务
     * @param bean
     * @return
     */
    public int updateService(ServiceBean bean);

    /**
     * 跟新服务属性
     * @param bean
     * @return
     */
    public int updateServiceParam(ServiceParamBean bean);

    /**
     * 删除服务
     * @param serviceId
     * @return
     */
    public int deleteService(Integer serviceId);

    /**
     * 删除产品属性
     * @param paramId
     * @return
     */
    public int deleteServiceParam(Long paramId);

    /**
     * 跟新产品和服务的关系，包括新增、删除关系
     * 当前一个服务组成一个服务包
     * TD_B_SERVICE_BUNDLECOMP，TD_B_SERVICE_BUNDLE表和TD_B_SERVICE一一对应
     * @param prodId
     * @param serviceIds
     * @return
     */
    public int updateProductServiceRel(Integer prodId,List<Integer> serviceIds)throws Exception;

    /**
     * 根据服务id获取服务属性列表
     * 例如订购宽带时，选择宽带产品时，需要选择宽带服务的属性
     * @param serviceId
     * @return
     */
    public List<ServiceParamBean> queryServiceParams(Integer serviceId);

    /**
     * 订购时，根据服务查询需要选择的属性，按属性类型分类。key为属性类型，value中的属性，当前都是单选。
     * @param serviceId
     * @return
     */
    public Map<String,List<ServiceParamBean>> querySelectServiceParams(Integer serviceId);

    /**
     * 获取产品包含的服务
     * @param prodId
     * @return
     */
    public List<ServiceBean> queryProductService(Integer prodId);


    /**
     * 增加产品信息
     * @param bean
     * @return
     */
    public int addProduct(ProductBean bean);

    /**
     * 跟新产品信息
     * @param bean
     * @return
     */
    public int updateProduct(ProductBean bean);

    /**
     * 删除产品，已经被使用的产品不能被删除
     * @param prodId
     * @return
     */
    public int deleteProduct(Long prodId);

    /**
     * 根据产品id获取产品
     * @param prodId
     * @return
     */
    public ProductBean queryProduct(Long prodId);

    /**
     * 跟新产品，包括产品和服务的关系，和资费的关系。包括关系的增、删
     * @param prod
     * @param serIds
     * @param feeIds
     * @return
     */
    public int updateProductAll(ProductBean prod,List<Long> serIds,List<Long> feeIds);

    /**
     * 获取产品目录列表
     * @return
     */
    public List<ProductCategoryBean> queryProductCategory();

    /**
     * 根据产品目录获取产品列表
     * @param categoryId
     * @return
     */
    public List<ProductBean> queryProductByCategory(Long categoryId);

    /**
     * 根据产品id获取资费列表
     * @param prodId
     * @return
     */
    public List<FeePolicyBean> queryFeePolicyListByProductId(Long prodId);







}
