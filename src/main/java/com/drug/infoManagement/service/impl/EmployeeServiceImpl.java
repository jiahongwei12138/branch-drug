package com.drug.infoManagement.service.impl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public void updateEmployeeById(BranchEmployee branchEmployee) {
	emp.updateEmployeeById(branchEmployee);
    }

    @Override
    public void addEmployee(BranchEmployee branchEmployee) {
	emp.addEmployee(branchEmployee);

    }

    @Override
    public boolean addMoreEmp(MultipartFile file) {
	return false;
    }



}
