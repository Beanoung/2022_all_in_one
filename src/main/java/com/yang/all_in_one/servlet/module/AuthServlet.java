package com.yang.all_in_one.servlet.module;

import com.yang.all_in_one.entity.Emp;
import com.yang.all_in_one.exception.LoginFailedException;
import com.yang.all_in_one.service.api.EmpService;
import com.yang.all_in_one.service.impl.EmpServiceImpl;
import com.yang.all_in_one.servlet.base.ModelBaseServlet;
import com.yang.all_in_one.util.ImperialCourtConst;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 身份验证 Servlet
 */
public class AuthServlet extends ModelBaseServlet {

    private EmpService empService = new EmpServiceImpl();

    //进朝
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //1.获取请求参数
            String loginAccount = request.getParameter("loginAccount");
            String loginPassword = request.getParameter("loginPassword");

            //2.调用EmpService方法执行登录逻辑
            Emp emp = empService.getEmpByLoginAccount(loginAccount, loginPassword);

            //3.通过request获取HttpSession对象
            HttpSession session = request.getSession();

            //4.将查询到的Emp对象放到session域
            session.setAttribute(ImperialCourtConst.LOGIN_EMP_ATTR_NAME, emp);

            //5.前往指定的页面视图
            //临时页面,因为还没写
//            String templateName = "temp";
//            processTemplate(templateName, request, response);

            //前往正式页面
            response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");


        } catch (Exception e) {
            e.printStackTrace();

            //6.判断此处捕获到的异常是否是登录失败的异常
            if (e instanceof LoginFailedException) {
                //7如果是则跳转回登录界面
                //①将异常信息存入请求域
                request.setAttribute("message", e.getMessage());

                //②处理视图:index
                processTemplate("index", request, response);

            } else {
                //8.如果不是则封装为运行时异常抛出
                throw new RuntimeException(e);
            }
        }
    }

    //退朝
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.通过request对象获取HttpSession对象
        HttpSession session = request.getSession();

        //2.将HttpSession对象强制失效
        session.invalidate();

        //3.回到首页,index.html
        String template = "index";
        processTemplate(template, request, response);
    }
}
