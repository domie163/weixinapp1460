package com.project.demo.controller;

import com.project.demo.entity.FoodInformation;
import com.project.demo.service.FoodInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 食物信息：(FoodInformation)表控制层
 *
 */
@RestController
@RequestMapping("/food_information")
public class FoodInformationController extends BaseController<FoodInformation, FoodInformationService> {

    /**
     * 食物信息对象
     */
    @Autowired
    public FoodInformationController(FoodInformationService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
