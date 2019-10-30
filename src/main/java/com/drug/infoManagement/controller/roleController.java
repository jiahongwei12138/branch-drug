package com.drug.infoManagement.controller;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.BranchRole;
import com.drug.infoManagement.service.RoleService;
import com.drug.util.ToolClass;

@Controller
public class roleController {
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping("/queryRoleIdName")
    @ResponseBody
    public Map<String, Object> queryRoleIdName(){
	List<BranchRole> queryRoleIdName = roleService.queryRoleIdName();
	Map<String, Object> responseByData = ToolClass.responseByData(queryRoleIdName, 0);
	return responseByData;
	
    }
    
    @RequestMapping("/queryAllRole")
    @ResponseBody
    public Map<String, Object> queryAllRole(int page,int limit ){
	int pages = (page-1)*limit;
	int queryCountRole = roleService.queryCountRole();
	Map<String, Integer> map = new HashMap<>();
	map.put("page", pages);
	map.put("limit", limit);
	List<BranchRole> queryAllRole = roleService.queryAllRole(map);
	Map<String, Object> responseByData = ToolClass.responseByData(queryAllRole, queryCountRole);
	return responseByData;
	
    }
    
    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(BranchRole branchRole) {
	String addRole = roleService.addRole(branchRole);
	return addRole;
	
    }
    
    @RequestMapping("/deletRoleById")
    @ResponseBody
    public boolean deletRoleById(String roleId) {
	try {
	    roleService.deletRoleById(roleId);
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	
    }
    
    @RequestMapping("/updateRoleById")
    @ResponseBody
    public boolean updateRoleById(BranchRole branchRole) {
	try {
	    roleService.updateRoleById(branchRole);
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

}
