package com.foodkonnekt.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.foodkonnekt.service.FutureOrderService;

@Controller
public class FutureOrderController {

    @Autowired
    private FutureOrderService futureOrderService;

}
