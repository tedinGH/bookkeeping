package com.fourth.bookkeeping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    @RequestMapping(value = "/demo")
    public String demo(){
        return "1";
    }
}
