package com.yourtion.util;

import com.yourtion.domain.Department;
import com.yourtion.domain.Employee;
import com.yourtion.domain.Position;

/**
 * IntegrateObject
 * Created by Yourtion on 26/06/2017.
 */
public class IntegrateObject {
    /**
     * 完成 Employee 与 Department, Position 对象的关联映射
     * <p>
     * 由于部门和职位在 Employee 中是对象关联映射，
     * 所以不能直接接收参数，需要创建 Department 对象和 Position 对象
     *
     * @param dept_id  部门ID
     * @param pos_id   公告 ID
     * @param employee 员工
     */
    public static void genericAssociation(Integer dept_id, Integer pos_id, Employee employee) {
        Department department = new Department();
        department.setId(dept_id);
        Position position = new Position();
        position.setId(pos_id);
        employee.setDepartment(department);
        employee.setPosition(position);
    }
}
