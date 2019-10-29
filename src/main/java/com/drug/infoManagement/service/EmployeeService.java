package com.drug.infoManagement.service;

import java.util.List;
import java.util.Map;

import com.drug.entity.BranchEmployee;

public interface EmployeeService {
    
    /**
     * 功能：拿到所有员工
     * @param map 姓名 页面大小，第几页
     * @return 员工列表
     *@datetime2019年10月28日下午3:30:44
     */
    List<BranchEmployee> queryAllEmp(Map<String, Object> map);
    
    /**
     * 功能：拿emp行数
     * @return 行数
     *@datetime2019年10月28日下午3:33:29
     */
    int getCounEmp();
    
    int deleteEmpById(int empid);
}
