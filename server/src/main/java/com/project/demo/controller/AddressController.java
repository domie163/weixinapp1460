package com.project.demo.controller;

import com.project.demo.entity.Address;
import com.project.demo.service.AddressService;

import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * 收货地址：(Address)表控制层
 *
 */
@RestController
@RequestMapping("address")
public class AddressController extends BaseController<Address, AddressService> {
    /**
     * 服务对象
     */
    @Autowired
    public AddressController(AddressService service) {
        setService(service);
    }

}


