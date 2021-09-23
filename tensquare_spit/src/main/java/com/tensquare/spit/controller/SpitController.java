package com.tensquare.spit.controller;

import com.tensquare.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 华韵流风
 * @ClassName SpitController
 * @Date 2021/9/22 20:20
 * @packageName com.tensquare.spit.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/spit/spit")
@CrossOrigin
public class SpitController {

    @Autowired
    private SpitService spitService;

}
