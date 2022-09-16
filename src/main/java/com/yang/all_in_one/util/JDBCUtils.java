package com.yang.all_in_one.util;

/*
 * JDBCUtils.java   JDBC工具类
 * 功能:
 * 1. 从数据源获取数据库连接
 * 2. 将数据库连接绑定到本地线程 (借助 ThreadLocal )
 * 3. 释放线程时和本地线程解除绑定
 *
 * */

/*
 * java.lang.ThreadLocal<T> 知识点
 * 泛型 T :要绑定到 ThreadLocal 的数据的类型
 * 目的: sql事务的一致性
 * 1.Connection conn=JDBCUtils.getConnection();  无法传参到 chain.doFilter(request,response);中间有很多方法
 * 2.doFilter 调用 Servlet 方法,再调用 Service 方法,最后 Dao 方法通过 conn 对象操作数据库
 * 3.需要 conn 对象是同一个对象,即同一个数据库连接
 * 4.因此用 ThreadLocal k-v 来绑定 conn 为 v 值, k是 ThreadLocal ,用 static 声明从而保证唯一性
 *
 * ThreadLocal 三个主要方法 set(T value); get(); remove();
 *
 * */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.lang.ThreadLocal;

public class JDBCUtils {

    //数据源   DataSource可以看作数据源，它封装了数据库参数，连接数据库，程序中操作DataSource对象即可对数据库进行增删改查操作
    private static DataSource datasource;
    // ThreadLocal ,声明为 static 保证唯一性
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    //初始化数据源
    static {
        try {
            //读取数据库连接信息,通过基准来获取 jdbc.properties 这个文件->可移植性,即无论在本地还是在服务器运行都是可行的,不需要改动
            //基准:类路径的根目录, resources 目录下的内容经过构建操作中的打包操作后会放在 WEB-INF/classes 下
            //类路径 WEB-INF/classes:因为 WEB-INF/classes 目录存放编译好的 *.class 文件

            //1.获取类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            //2.从类路径目录下读取文件,使用 inputStream 流读取文件
            InputStream stream = classLoader.getResourceAsStream("jdbc.properties");
            //3.封装数据    properties 文件可以直接读取和解析一些配置文件,eg. xml文件、properties文件
            Properties properties = new Properties();
            properties.load(stream);
            //4.根据封装的信息创建数据源对象, Druid:数据库连接池
            datasource = DruidDataSourceFactory.createDataSource(properties);
            DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取数据库连接并返回
     * @return 返回连接
     * */

    public static Connection getConnection() {
        Connection connection = null;

        try {
            //检查 connection 对象是否为 null
            connection = threadLocal.get();

            if (connection == null) {
                //如果为 null,则从数据源获取数据库连接
                connection = datasource.getConnection();

                //绑定到 ThreadLocal
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return connection;
    }

    //释放数据库连接
    public static void releaseConnection(Connection connection) {

        if (connection != null) {
            try {
                //close() 在数据库连接池中将当前连接对象标记为空闲,并没有真正的释放
                connection.close();

                //将当前数据库连接从 ThreadLocal 上移除
                threadLocal.remove();
                if (threadLocal.get() == null && connection.isClosed() == true) {
                    System.out.println("releaseConnection:succeed!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
