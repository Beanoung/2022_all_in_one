package com.yang.all_in_one.servlet.module;

import com.yang.all_in_one.entity.Memorials;
import com.yang.all_in_one.service.api.MemorialsService;
import com.yang.all_in_one.service.impl.MemorialsServiceImpl;
import com.yang.all_in_one.servlet.base.ModelBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 奏折页面
 */
public class WorkServlet extends ModelBaseServlet {

    private MemorialsService memorialsService=new MemorialsServiceImpl();

    protected void showMemorialsDigestList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.调用Service方法查询数据
        List<Memorials> memorialsList=memorialsService.getAllMemorialsDigestList();

        //2.将查询到的数据存入请求域
        request.setAttribute("memorialsList",memorialsList);

        //3.渲染视图
        String templateName="memorials-list";
        processTemplate(templateName,request,response);
    }


    protected void showMemorialsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.从请求参数读取memorialsID
        String memorialsID=request.getParameter("memorialsID");

        //2.根据memorialsID从Service中查询Memorials对象
        Memorials memorials=memorialsService.getMemorialsDetailByID(memorialsID);

        // **********************补充功能**********************
        // 获取当前奏折对象的状态
        Integer memorialsStatus = memorials.getMemorialsStatus();

        // 判断奏折状态
        if (memorialsStatus == 0) {
            // 更新奏折状态：数据库修改
            memorialsService.updateMemorialsStatusToRead(memorialsID);

            // 更新奏折状态：当前对象修改
            memorials.setMemorialsStatus(1);
        }
        // **********************补充功能**********************

        //3.将Memorials对象存入请求域
        request.setAttribute("memorials",memorials);

        //4.解析渲染视图
        String templateName="memorials_detail";
        processTemplate(templateName,request,response);
    }

    protected void feedBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取表单提交的请求参数
        String memorialsID = request.getParameter("memorialsID");
        String feedbackContent = request.getParameter("feedbackContent");

        // 执行更新
        memorialsService.updateMemorialsFeedBack(memorialsID, feedbackContent);

        // 重定向回显示奏折列表页面
        response.sendRedirect(request.getContextPath() + "/work?method=showMemorialsDigestList");
    }
}
