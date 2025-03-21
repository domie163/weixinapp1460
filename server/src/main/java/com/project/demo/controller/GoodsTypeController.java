package com.project.demo.controller;

import com.project.demo.entity.GoodsType;
import com.project.demo.service.GoodsTypeService;

import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * (GoodsType)表控制层
 *
 */
@RestController
@RequestMapping("goods_type")
public class GoodsTypeController extends BaseController<GoodsType, GoodsTypeService> {
    /**
     * 服务对象
     */
    @Autowired
    public GoodsTypeController(GoodsTypeService service) {
        setService(service);
    }

}


