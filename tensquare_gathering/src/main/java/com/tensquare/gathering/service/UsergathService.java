package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.UsergathDao;
import com.tensquare.gathering.pojo.Usergath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 华韵流风
 * @Date 2021-10-24 16:30:11
 */
@Service
public class UsergathService {

    @Autowired
    private UsergathDao usergathDao;

    /**
     * 根据id查询usergath
     *
     * @param id id
     * @return Usergath
     */
    public Usergath findById(String id) {
        return usergathDao.findById(id).get();
    }

    /**
     * 根据id删除usergath
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    public void remove(String id) {
        usergathDao.deleteById(id);
    }

    /**
     * 新增usergath
     *
     * @param usergath usergath
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(Usergath usergath) {
        usergathDao.save(usergath);
    }

    /**
     * 修改usergath
     *
     * @param usergath usergath
     */
    @Transactional(rollbackFor = Exception.class)
    public void edit(Usergath usergath) {
        usergathDao.save(usergath);
    }

    /**
     * 查询所有usergath
     *
     * @return List<Usergath>
     */
    public List<Usergath> findAll() {
        return usergathDao.findAll();
    }
}
