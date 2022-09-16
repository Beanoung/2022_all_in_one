package com.yang.all_in_one.servlet.module;

import com.yang.all_in_one.servlet.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 首页(入口)
 */
public class PortalServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //声明要访问的首页的逻辑视图
        String templateName = "index";

        //调用父类的方法,根据逻辑视图名称来渲染视图
        processTemplate(templateName, req, resp);
    }
}
