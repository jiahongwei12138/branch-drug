package com.drug.infoManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.BtanchRole;
import com.drug.infoManagement.service.RoleService;
import com.drug.util.ToolClass;

@Controller
public class roleController {
    
    @Autowired
    private RoleService roleService;
    
    @RequestMapping("/queryRoleIdName")
    @ResponseBody
    public Map<String, Object> queryRoleIdName(){
	List<BtanchRole> queryRoleIdName = roleService.queryRoleIdName();
	Map<String, Object> responseByData = ToolClass.responseByData(queryRoleIdName, 0);
	return responseByData;
	
    }

}
