package com.pl.airboss.app.services;/**
 * Created by admin on 2020/4/26.
 */

import com.pl.airboss.app.cache.CacheSensitiveWords;
import com.pl.airboss.web.cache.CacheStaticData;
import com.pl.airboss.framework.cache.impl.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName CommonService
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/4/26 15:57
 * @Version 1.0
 **/
@Service
public class CommonService {

    @Autowired
    CacheManager cacheManager;
    
    /**
     *@Description 判断txt是否包含配置在cfg_sensitive_words表中的敏感词
     *@auther Kod Wong
     *@Date 2020/4/26 16:01
     *@Param
     *@return
     *@Version 1.0
     */
    public boolean containsSensitiveWords(String txt){
        return cacheManager.containsPartOfListCacheValue(CacheSensitiveWords.class.getName(),txt);
    }

    /**
     *@Description 根据codeType获取静态数据列表
     *@auther Kod Wong
     *@Date 2020/4/26 16:02
     *@Param
     *@return
     *@Version 1.0
     */
    public List<Map> getStaticByCodeType(String codeType){
        return(List<Map>) cacheManager.getMapCacheValue(CacheStaticData.class.getName(),codeType);

    }
}
