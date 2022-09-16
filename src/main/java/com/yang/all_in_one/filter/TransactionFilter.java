package com.yang.all_in_one.filter;

import com.yang.all_in_one.util.JDBCUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    //声明集合保存静态资源扩展名,根据扩展名来判断是否是静态资源,避免浪费事务
    private static Set<String> staticResourceExtNameSet;

    static {
        staticResourceExtNameSet = new HashSet<>();
        staticResourceExtNameSet.add(".png");
        staticResourceExtNameSet.add(".jpg");
        staticResourceExtNameSet.add(".css");
        staticResourceExtNameSet.add(".js");
    }

    //主要实现这个方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

        //前置操作:排除静态资源
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();
        //lastIndexOf("char"): 找到""中的字符的位置,从0开始, subString(int):截掉字符串前int个字符,输出剩下的字符串
        if(servletPath.contains(".")) {     //如果不加判断,没有的话返回的-1会报错
            String extName = servletPath.substring(servletPath.lastIndexOf("."));
            if (staticResourceExtNameSet.contains(extName)) {
                //如果检测到当前请求是静态资源,则直接放行,不做事务操作
                filterChain.doFilter(servletRequest, servletResponse);
                //当前方法立即返回,不向下走
                return;
            }
        }

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            //重要操作:关闭自动提交
            connection.setAutoCommit(false);

            //核心操作
            filterChain.doFilter(servletRequest, servletResponse);
            connection.commit();
        } catch (SQLException e) {
            try {
                //回滚事务:
                //确保异常回滚:必须让所有catch块都把编译时异常转换为运行时异常抛出,
                //不然TransactionFilter的catch就无法捕获到底层抛出的异常,那么应该回滚的时候就无法及时回滚
                //抛出异常是从Dao抛到Service,再到Servlet,最后到doFilter(),回滚
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }

            //将捕获到的异常显示到指定页面"/"上
            String message = e.getMessage();
            //setAttribute(String,Object): 设置信息,配合getAttribute(String)
            request.setAttribute("systemMessage:", message);
            //请求转发,转发位置暂时为"/"代表首页,前端页面目前还没有结构,以后再修改
            request.getRequestDispatcher("/").forward(request, servletResponse);

        } finally {
            //释放数据库连接:
            //不能在其他地方释放,因为诸多操作使用的是同一个connection,提前释放的话后续操作就无法正常完成
            JDBCUtils.releaseConnection(connection);
        }
    }

    @Override
    public void destroy() {
    }
}
