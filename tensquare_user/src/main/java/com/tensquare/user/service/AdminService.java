package com.tensquare.user.service;

import com.tensquare.user.dao.AdminDao;
import com.tensquare.user.pojo.Admin;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 华韵流风
 * @Date 2021-10-09 10:48:19
 */
@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * 根据id查询Admin
     *
     * @param id id
     * @return Admin
     */
    public Admin findById(String id) {
        return adminDao.findById(id).get();
    }

    /**
     * 根据id删除Admin
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    public void remove(String id) {
        adminDao.deleteById(id);
    }

    /**
     * 新增Admin
     *
     * @param admin admin
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(Admin admin) {
        admin.setId(String.valueOf(idWorker.nextId()));
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminDao.save(admin);
    }

    /**
     * 修改Admin
     *
     * @param admin   admin
     * @param adminId adminId
     */
    @Transactional(rollbackFor = Exception.class)
    public void edit(Admin admin, String adminId) {
        admin.setId(adminId);
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminDao.save(admin);
    }

    /**
     * 查询所有Admin
     *
     * @return List<Admin>
     */
    public List<Admin> findAll() {
        return adminDao.findAll();
    }


    /**
     * 登录
     *
     * @param loginname loginname
     * @param password  password
     * @return Admin
     */
    public Admin login(String loginname, String password) {
        Admin admin = adminDao.findByLoginname(loginname);
        if (admin != null && encoder.matches(password, admin.getPassword()) && "1".equals(admin.getState())) {
            return admin;
        } else {
            return null;
        }
    }

    /**
     * 管理员分页
     *
     * @param admin admin
     * @param page  page
     * @param size  size
     * @return Page<Admin>
     */
    public Page<Admin> findByAdminPage(Admin admin, int page, int size) {
        return adminDao.findAll(getSpecification(admin), PageRequest.of(page - 1, size));

    }


    private Specification<Admin> getSpecification(Admin admin) {
        List<Predicate> list = new ArrayList<>();
        return (Specification<Admin>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(admin.getId())) {
                list.add(criteriaBuilder.like(root.get("id").as(String.class), admin.getId()));
            }
            if (!StringUtils.isEmpty(admin.getLoginname())) {
                list.add(criteriaBuilder.like(root.get("loginname").as(String.class), "%" + admin.getLoginname() + "%"));
            }
            if (!StringUtils.isEmpty(admin.getPassword())) {
                list.add(criteriaBuilder.like(root.get("password").as(String.class), admin.getPassword()));
            }
            if (!StringUtils.isEmpty(admin.getState())) {
                list.add(criteriaBuilder.like(root.get("state").as(String.class), admin.getState()));
            }

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };

    }


}
