package com.drug.infoManagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BranchEmployee;
import com.drug.infoManagement.mapper.EmployeeMapper;
import com.drug.infoManagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeMapper emp;

    @Override
    public List<BranchEmployee> queryAllEmp(Map<String, Object> map) {
	return emp.queryAllEmp(map);
    }

    @Override
    public int getCounEmp() {
	return emp.getCounEmp();
    }

    @Override
    public int deleteEmpById(int empid) {
	return emp.deleteEmpById(empid);
    }

}
