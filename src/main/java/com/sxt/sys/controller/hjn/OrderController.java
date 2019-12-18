package com.sxt.sys.controller.hjn;

import com.sxt.sys.service.hjn.CostServicel;
import com.sxt.sys.service.hjn.DetailedServicel;
import com.sxt.sys.service.hjn.OrderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: xterp
 * @description: OrderController
 * @author: hjn
 * @create: 2019-12-16 10:21
 **/
@Controller
public class OrderController {
    @Autowired
    private OrderServiceI orderServiceI;
    @Autowired
    private CostServicel costServicel;
    @Autowired
    private DetailedServicel detailedServicel;

    /**
     * 查询所有未删除的支出与收入记录
     * @return
     */
    @ResponseBody
    @RequestMapping("queryOrdersPage")
    public Map queryOrdersPage(String orderid, Date createtime){
        //查询采购订单
        List<HashMap<String, Object>> listOrders = orderServiceI.findOrders(orderid,createtime);
        HashMap<Integer, List> map = new HashMap<Integer, List>();
        map.put(1,listOrders);
        return map;
    }


}
