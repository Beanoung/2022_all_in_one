package com.yang.all_in_one.dao.impl;

import com.yang.all_in_one.dao.BaseDao;
import com.yang.all_in_one.dao.api.EmpDao;
import com.yang.all_in_one.entity.Emp;

public class EmpDaoImpl extends BaseDao<Emp> implements EmpDao {
    @Override
    public Emp selectEmpByLoginAccount(String loginAccount, String encodePassword) {

        //1.编写sql语句
        String sql = "select emp_id empID," +
                "emp_name empName," +
                "emp_position empPosition," +
                "login_account loginAccount," +
                "login_password loginPassword " +
                "from t_emp where login_account=? and login_password=?";

        //2.调用父类方法查询单个对象
        Emp emp = super.getSingleBean(sql, Emp.class, loginAccount, encodePassword);

        return emp;
    }
}
