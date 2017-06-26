package com.yourtion.util;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * ResponseUtil
 * Created by Yourtion on 26/06/2017.
 */
public class ResponseUtil {
    /**
     * 用 HttpServletResponse 返回前台 JSON 格式数据
     *
     * @param response 返回
     * @param o        对象
     * @throws Exception 错误
     */
    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
