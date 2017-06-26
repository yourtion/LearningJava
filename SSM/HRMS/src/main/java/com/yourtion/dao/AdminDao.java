package com.yourtion.dao;

import com.yourtion.domain.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * AdminDao
 * Created by Yourtion on 26/06/2017.
 */
@Repository
public interface AdminDao {

    /** 登录
     *
     * @param admin 管理员
     * @return Admin
     */
    public Admin login(Admin admin);

    /** 根据条件查询管理员
     *
     * @param map 条件
     * @return List
     */
    public List<Admin> findAdmins(Map<String, Object> map);

    /** 根据条件查询管理员人数
     *
     * @param map 条件
     * @return Integer
     */
    public Integer getCount(Map<String, Object> map);

    /** 添加管理员
     *
     * @param admin 管理员
     * @return Integer
     */
    public Integer addAdmin(Admin admin);

    /** 修改管理员
     *
     * @param admin 管理员
     * @return Integer
     */
    public Integer updateAdmin(Admin admin);

    /** 删除管理员
     *
     * @param id 管理员ID
     * @return Integer
     */
    public Integer deleteAdmin(Integer id);
}
