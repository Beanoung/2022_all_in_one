package com.yang.all_in_one.service.impl;

import com.yang.all_in_one.dao.api.EmpDao;
import com.yang.all_in_one.dao.impl.EmpDaoImpl;
import com.yang.all_in_one.entity.Emp;
import com.yang.all_in_one.exception.LoginFailedException;
import com.yang.all_in_one.service.api.EmpService;
import com.yang.all_in_one.util.ImperialCourtConst;
import com.yang.all_in_one.util.MD5Util;

public class EmpServiceImpl implements EmpService {

    private EmpDao empDao = new EmpDaoImpl();

    @Override
    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword) {

        //1.对密码进行加密
        String encodePassword = MD5Util.encode(loginPassword);

        //2.根据账户和加密密码查询数据库
        Emp emp= empDao.selectEmpByLoginAccount(loginAccount,encodePassword);

        //3.检查Emp对象是否为null
        if(emp !=null) {
            //①不为null则返回Emp
            return emp;
        }else {
            //②为null则抛出异常
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);
        }
    }
}
