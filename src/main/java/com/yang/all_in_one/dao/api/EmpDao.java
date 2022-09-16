package com.yang.all_in_one.dao.api;

import com.yang.all_in_one.entity.Emp;

public interface EmpDao {
    Emp selectEmpByLoginAccount(String loginAccount, String encodePassword);
}
