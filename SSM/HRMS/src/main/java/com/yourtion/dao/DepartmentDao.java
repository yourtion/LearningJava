package com.yourtion.dao;

import com.yourtion.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * DepartmentDao
 * Created by Yourtion on 26/06/2017.
 */
@Repository
public interface DepartmentDao {

    /**
     * 根据条件查询部门
     *
     * @param map 条件
     * @return List
     */
    public List<Department> findDepartments(Map<String, Object> map);

    /**
     * 根据条件查询部门数量
     *
     * @param map 条件
     * @return Integer
     */
    public Integer getCount(Map<String, Object> map);

    /**
     * 添加部门
     *
     * @param department 部门
     * @return Integer
     */
    public Integer addDepartment(Department department);

    /**
     * 修改部门
     *
     * @param department 部门
     * @return Integer
     */
    public Integer updateDepartment(Department department);

    /**
     * 删除部门
     *
     * @param id 部门ID
     * @return Integer
     */
    public Integer deleteDepartment(Integer id);

}
