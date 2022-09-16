package com.yang;

import com.yang.all_in_one.dao.BaseDao;
import com.yang.all_in_one.entity.Emp;
import com.yang.all_in_one.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class all_in_oneTest {

    private BaseDao<Emp> baseDao = new BaseDao<Emp>();

    @Test
    public void testGetSingleBean() {
        //数据库字段和实体类属性不一样就需要别名
        String sql = "select emp_id empID,emp_name empName,emp_position empPosition," +
                "login_account loginAccount,login_password loginPassword from t_emp where emp_id=?";
        Emp emp = baseDao.getSingleBean(sql, Emp.class, 1);
        System.out.println("#emp:" + emp);
    }

    @Test
    public void testGetBeanList() {
        String sql = "select emp_id empID,emp_name empName,emp_position empPosition," +
                "login_account loginAccount,login_password loginPassword from t_emp";

        List<Emp> empList = baseDao.getBeanList(sql, Emp.class);
        for (Emp emp : empList) {
            System.out.println(emp);
        }
    }

    @Test
    public void testUpdate(){
        String sql="update t_emp set emp_position=? where emp_id=?";

        String empPosition="minister";
        String empID="3";

        int affectedRowNumber=baseDao.update(sql,empPosition,empID);
        System.out.println("affectedRowNumber:"+affectedRowNumber);
    }

    @Test
    public void testConnection() {

        Connection connection = JDBCUtils.getConnection();
        System.out.println("connection:" + connection);

        JDBCUtils.releaseConnection(connection);
    }
}
