package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 华韵流风
 * @ClassName SpitService
 * @Date 2021/9/22 20:24
 * @packageName com.tensquare.spit.service
 * @Description TODO
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;


}
