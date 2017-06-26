package com.yourtion.service.impl;

import com.yourtion.dao.DepartmentDao;
import com.yourtion.domain.Department;
import com.yourtion.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * DepartmentServiceImpl
 * Created by Yourtion on 26/06/2017.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    // 自动注入 DAO 对象
    @Resource
    private DepartmentDao departmentDao;

    // 实现接口中的方法

    @Override
    public List<Department> findDepartments(Map<String, Object> map) {

        return departmentDao.findDepartments(map);
    }

    @Override
    public Integer getCount(Map<String, Object> map) {

        return departmentDao.getCount(map);
    }

    @Override
    public Integer addDepartment(Department department) {

        return departmentDao.addDepartment(department);
    }

    @Override
    public Integer updateDepartment(Department department) {

        return departmentDao.updateDepartment(department);
    }

    @Override
    public Integer deleteDepartment(Integer id) {
        Integer flag = null;
        // 如果删除关联外键的行记录，抛出异常
        try {
            flag = departmentDao.deleteDepartment(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return flag;
    }
}
