package com.project.demo.controller;

import com.project.demo.constant.FindConfig;
import com.project.demo.entity.Order;
import com.project.demo.service.OrderService;

import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单：(Order)表控制层
 *
 */
@RestController
@RequestMapping("order")
public class OrderController extends BaseController<Order, OrderService> {
    /**
     * 服务对象
     */
    @Autowired
    public OrderController(OrderService service) {
        setService(service);
    }

    @RequestMapping("/get_business_order_list")
    public Map<String, Object> getBusinessOrderList(HttpServletRequest request) {
        Map<String,String> query =  service.readQuery(request);
        String sql = "SELECT\n" +
                "\tt1.* \n" +
                "FROM\n" +
                "\t`order` t1\n" +
                "\tLEFT JOIN goods t2 ON t1.goods_id = t2.goods_id \n" +
                "WHERE\n" +
                "\tt2.user_id = "+ query.get("user_id");
        String countSql = "SELECT\n" +
                "\tcount(t1.order_id) \n" +
                "FROM\n" +
                "\t`order` t1\n" +
                "\tLEFT JOIN goods t2 ON t1.goods_id = t2.goods_id \n" +
                "WHERE\n" +
                "\tt2.user_id = "+ query.get("user_id");
        if (!StringUtils.isEmpty(query.get("order_number"))){
            sql = sql + " and t1.order_number like '%"+query.get("order_number")+"%'";
            countSql = countSql + " and t1.order_number like '%"+query.get("order_number")+"%'";
        }
        if (!StringUtils.isEmpty(query.get("title"))){
            sql = sql + " and t1.title like '%"+query.get("title")+"%'";
            countSql = countSql + " and t1.title like '%"+query.get("title")+"%'";
        }
        if (!StringUtils.isEmpty(query.get("contact_name"))){
            sql = sql + " and t1.contact_name like '%"+query.get("contact_name")+"%'";
            countSql = countSql + " and t1.contact_name like '%"+query.get("contact_name")+"%'";
        }
        if (!StringUtils.isEmpty(query.get("state"))){
            sql = sql + " and t1.state = '"+query.get("state")+"'";
            countSql = countSql + " and t1.state = '"+query.get("state")+"'";
        }
        Map<String,String> config =  service.readConfig(request);

        if (!StringUtils.isEmpty(config.get(FindConfig.PAGE))){
            int page = config.get(FindConfig.PAGE) != null && !"".equals(config.get(FindConfig.PAGE)) ? Integer.parseInt(config.get(FindConfig.PAGE)) : 1;
            int limit = config.get(FindConfig.SIZE) != null && !"".equals(config.get(FindConfig.SIZE)) ? Integer.parseInt(config.get(FindConfig.SIZE)) : 10;
            sql = sql + " limit "+(page-1)*limit+" , "+limit;
        }
        Query select = service.runEntitySql(sql);
        Query countSelect = service.runCountSql(countSql);
        Map<String,Object> map = new HashMap<>();
        map.put("list",select.getResultList());
        map.put("count",countSelect.getSingleResult());
        return success(map);
    }

}


