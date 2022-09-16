package com.yang.all_in_one.dao;

/**
 * 所有Dao实现的基类
 *
 * @param <T> 实体类的类型
 */

import com.yang.all_in_one.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {

    // QueryRunner: DBUtils工具包提供的数据库操作对象,
    // 主要方法 query(): sql中的select语句, update(): sql中的insert,update,delete语句
    private QueryRunner runner = new QueryRunner();

    /**
     * 返回 多个对象 的查询方法
     *
     * @param sql         sql语句
     * @param entityClass 实体类的class对象
     * @param parameters  传给sql语句的参数
     * @return 查询结果
     */
    public List<T> getBeanList(String sql, Class<T> entityClass, Object... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();

            //变化: BeanHandler ->BeanListHandler     把结果集转为一个 Bean 的 List,并返回
            return runner.query(connection, sql, new BeanListHandler<>(entityClass), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 单个对象 的查询
     *
     * @param sql         sql语句
     * @param entityClass 实体类对应的class对象
     * @param parameters  传给sql语句的参数
     * @return 查询到的实体类对象
     */
    public T getSingleBean(String sql, Class<T> entityClass, Object... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();

            //BeanHandler   把结果集转为一个 Bean,并返回
            return runner.query(connection, sql, new BeanHandler<>(entityClass), parameters);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 通用的 增删改 方法
     *
     * @param sql        sql语句
     * @param parameters 传给sql语句的参数
     * @return 受影响的行数
     */
    public int update(String sql, Object... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();

            int affectedRowNumbers = runner.update(connection, sql, parameters);
            return affectedRowNumbers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
