package com.drug.infoManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.BranchEmployee;
import com.drug.infoManagement.service.EmployeeService;
import com.drug.util.ToolClass;

@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeeService emp;
    
    
    @RequestMapping("/queryAllEmp")
    @ResponseBody
    public Map<String, Object> queryAllemp(Integer page,Integer limit,String empName){
	HashMap<String, Object> hashMap = new HashMap<>();
	Integer pageString = (page-1)*limit;
	hashMap.put("page", pageString);
	hashMap.put("limit", limit);
	hashMap.put("empName", empName);
	List<BranchEmployee> queryAllEmp = emp.queryAllEmp(hashMap);
	int counEmp = emp.getCounEmp();
	Map<String, Object> responseByData = ToolClass.responseByData(queryAllEmp, counEmp);
	return responseByData;
	
    }
    
    @RequestMapping("/deletEmpById")
    @ResponseBody
    public boolean deleteEmpById(int empid) {
	int i = emp.deleteEmpById(empid);
	if (i==1) {
	    return true;
	}else {
	    return false;
	}
	
	
    }
}
