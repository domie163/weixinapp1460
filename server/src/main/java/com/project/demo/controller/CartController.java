package com.project.demo.controller;

import com.project.demo.entity.Cart;
import com.project.demo.service.CartService;

import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * 购物车：(Cart)表控制层
 *
 */
@RestController
@RequestMapping("cart")
public class CartController extends BaseController<Cart, CartService> {
    /**
     * 服务对象
     */
    @Autowired
    public CartController(CartService service) {
        setService(service);
    }

}


