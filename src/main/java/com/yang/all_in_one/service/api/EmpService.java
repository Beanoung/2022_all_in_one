package com.yang.all_in_one.service.api;

import com.yang.all_in_one.entity.Emp;

public interface EmpService {
    Emp getEmpByLoginAccount(String loginAccount, String loginPassword);
}
