package com.yourtion.dao;

import com.yourtion.domain.Employee;
import com.yourtion.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * EmployeeDao
 * Created by Yourtion on 26/06/2017.
 */
@Repository
public interface EmployeeDao {

    /**
     * 根据条件查询员工
     *
     * @param map 条件
     * @return List
     */
    public List<Post> findEmployees(Map<String, Object> map);

    /**
     * 根据条件查询员工数量
     *
     * @param map 条件
     * @return Integer
     */
    public Integer getCount(Map<String, Object> map);

    /**
     * 添加员工
     *
     * @param employee 员工
     * @return Integer
     */
    public Integer addEmployee(Employee employee);

    /**
     * 修改员工
     *
     * @param employee 员工
     * @return Integer
     */
    public Integer updateEmployee(Employee employee);

    /**
     * 删除员工
     *
     * @param id 员工ID
     * @return Integer
     */
    public Integer deleteEmployee(String id);
}
