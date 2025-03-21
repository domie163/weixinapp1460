package com.project.demo.controller;

import com.project.demo.entity.Goods;
import com.project.demo.service.GoodsService;

import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * 商品信息：(Goods)表控制层
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController<Goods, GoodsService> {
    /**
     * 服务对象
     */
    @Autowired
    public GoodsController(GoodsService service) {
        setService(service);
    }

}


