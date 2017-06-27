package com.yourtion.controller;

import com.yourtion.domain.Admin;
import com.yourtion.service.AdminService;
import com.yourtion.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AdminController
 * 类中的所有响应方法都被映射到 /admin 路径下
 * Created by Yourtion on 26/06/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    // 自动注入 adminService
    @Resource
    private AdminService adminService;

    /**
     * 处理登录请求
     *
     * @param admin   管理员
     * @param request 请求
     * @param session 会话
     * @return 登录信息
     */
    @RequestMapping("/login")
    public String login(Admin admin, HttpServletRequest request, HttpSession session) {
        Admin resultAdmin = adminService.login(admin);
        // 如果该登录的管理员用户名或密码错误返回错误信息
        if (resultAdmin == null) {
            request.setAttribute("admin", admin);
            request.setAttribute("errorMsg", "Please check your username and password!");
            return "login";
        } else {
            // 登录成功， Session 保存该管理员的信息
            session.setAttribute("currentAdmin", resultAdmin);
            session.setAttribute("username", resultAdmin.getUsername());
            return "redirect:main";
        }
    }

    /**
     * 处理跳转至主页请求
     *
     * @param model 模型
     * @return 请求返回
     * @throws Exception 错误
     */
    @RequestMapping(value = "/main")
    public String test(Model model) throws Exception {
        return "home_page";
    }

    /**
     * 处理查询管理员请求
     *
     * @param admin    管理员
     * @param response 请求
     * @return 请求返回
     * @throws Exception 错误
     */
    @RequestMapping("/list")
    public String list(Admin admin, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断查询条件是否为空，如果是，对条件做数据库模糊查询的处理
        if (admin.getUsername() != null && !"".equals(admin.getUsername().trim())) {
            map.put("username", "%" + admin.getUsername() + "%");
        }
        List<Admin> adminList = adminService.findAdmins(map);
        Integer total = adminService.getCount(map);
        // 将数据以 JSON 格式返回前端
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(adminList);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 处理保存管理员请求
     *
     * @param admin    管理员
     * @param request  请求
     * @param response 返回
     * @return 请求返回
     * @throws Exception 错误
     */
    @RequestMapping("/save")
    public String save(Admin admin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        // 如果 id 不为空，则添加管理员，否则修改管理员
        if (admin.getId() == null) {
            resultTotal = adminService.addAdmin(admin);
        } else {
            resultTotal = adminService.updateAdmin(admin);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 处理删除管理员请求
     *
     * @param ids      管理员IDs
     * @param response 请求
     * @param session  会话
     * @return 请求返回
     * @throws Exception 错误
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response, HttpSession session) throws Exception {
        JSONObject result = new JSONObject();
        // 将要删除的管理员的 id 进行处理
        String[] idsStr = ids.split(",");
        for (String anIdsStr : idsStr) {
            // 不能删除超级管理员（superadmin） 和当前登录的管理员
            if (anIdsStr.equals("1") || anIdsStr.equals(((Admin) session.getAttribute("currentAdmin")).getId().toString())) {
                result.put("success", false);
            } else {
                adminService.deleteAdmin(Integer.parseInt(anIdsStr));
                result.put("success", true);
            }
        }
        ResponseUtil.write(response, result);
        return null;
    }

    /**
     * 处理退出请求
     *
     * @param session 会话
     * @return 请求返回
     * @throws Exception 错误
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/login.jsp";
    }
}
