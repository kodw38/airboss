package com.pl.airboss.crm.res;/**
 * Created by admin on 2020/9/10.
 */

import com.pl.airboss.app.AirBossApplication;
import com.pl.airboss.crm.res.bean.ResPatternSegmentBean;
import com.pl.airboss.crm.res.bean.ResPhoneNumOriginBean;
import com.pl.airboss.crm.res.bean.ResSelpriceModeBean;
import com.pl.airboss.crm.res.bean.ResSelpriceModeBeanKey;
import com.pl.airboss.crm.res.service.interfaces.IResPhoneNumSV;
import javafx.application.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName testResSV
 * @Description ToDo
 * @Author Kod Wong
 * @Date 2020/9/10 10:33
 * @Version 1.0
 **/
// 配置Spring中的测试环境
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirBossApplication.class)
@WebAppConfiguration
// 指定Spring的配置文件路径

public class JunitTestResSV {

        @Resource
        IResPhoneNumSV resPhoneNumSV;

        @Before
        public void init() {
            System.out.println("开始测试-----------------");
        }

        @After
        public void after() {
            System.out.println("测试结束-----------------");
        }


        @Test
        public void testQueryPriceList(){

            List<ResSelpriceModeBean> list = resPhoneNumSV.queryPriceList(null);
            System.out.println(list.size());
        }
        @Test
        public void testAddPrice(){
            ResSelpriceModeBean b = new ResSelpriceModeBean();
            b.setCreateDate(new Date());
            b.setOpId(1L);
            b.setOrderFee(10000L);
            b.setReserveFee(0L);
            b.setPatternId(10L);
            int n= resPhoneNumSV.addPrice(b);
            System.out.println(n);
        }

        @Test
        public void testDeletePrice(){
            int n = resPhoneNumSV.deletePrice(1L);
            System.out.println(n);
        }
        @Test
        public void testGetOrderFee(){
            ResSelpriceModeBean b= resPhoneNumSV.queryPriceByPrimaryKey(2L);
            System.out.println(b.getOrderFee());
        }

        @Test
        public void testImportSegmentList(){
            ResPatternSegmentBean b1 = new ResPatternSegmentBean();
            ResPatternSegmentBean b2 = new ResPatternSegmentBean();
            b1.setSegExp("87[1-4]????");
            b1.setPatternSegName("22-20-01");
            b1.setNetId("ZHUM-FEEF-22-20-01");
            b1.setState("GEND");
            b2.setSegExp("87[5-9]????");
            b2.setPatternSegName("22-21-01");
            b2.setNetId("ZHUM-FEEF-22-21-01");
            b2.setState("GEND");
            List l = new ArrayList();
            l.add(b1);
            l.add(b2);
            resPhoneNumSV.importSegmentList(l);
        }
        @Test
        public void testQuerySegmentList(){
            ResPatternSegmentBean b = new ResPatternSegmentBean();
            b.setNetId("ZHUM-FEEF-22-21-01");
            List<ResPatternSegmentBean> r = resPhoneNumSV.querySegmentList(b);
            System.out.println(r.get(0).getSegExp());

        }
        @Test
        public void testGeneratorNumBySegment(){
            try {
                resPhoneNumSV.generatorNumBySegment(1013L);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Test
        public void testCleanNumBySegment()throws Exception{
            resPhoneNumSV.cleanNumBySegment(1013L);

        }
        @Test
        public void testUpdateSegment(){
            ResPatternSegmentBean b= new ResPatternSegmentBean();
            b.setPatternSegId(1013L);
            b.setNetId("NNCC199-11411");
            resPhoneNumSV.updateSegment(b);
        }
        @Test
        public void testQueryNum(){
            ResPhoneNumOriginBean b = new ResPhoneNumOriginBean();
            b.setResStatus("GEND");
            List<ResPhoneNumOriginBean> l = resPhoneNumSV.queryNum(b,0,5);
            System.out.println(l.size());

        }
        @Test
        public void testUpdateNum(){
            ResPhoneNumOriginBean b = new ResPhoneNumOriginBean();
            b.setResId("1111");
            resPhoneNumSV.updateNum(b);
        }
        @Test
        public void testOccupy(){
            try {
                resPhoneNumSV.occupyPhoneNum("88765632");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Test
        public void testRelease(){
            try {
                resPhoneNumSV.releasePhoneNum("88765632");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

}
