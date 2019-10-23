package com.drug.infoManagement.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.BranchEmployee;
import com.drug.entity.BranchModel;
import com.drug.entity.BtanchStorefactsheet;
import com.drug.infoManagement.service.BranchIndexService;
import com.drug.util.ToolClass;


@Controller
public class BranchIndexController {

	@Autowired
	private BranchIndexService branchIndexService;
	
	
	@RequestMapping("/login")
	@ResponseBody
	public boolean login(BranchEmployee branchEmployee,HttpSession httpSession){
	    BranchEmployee employee = branchIndexService.login(branchEmployee, httpSession);
	    
	    if (employee!=null) {
		return true;
	    }else {
		return false;
	    }
	    
	}
	
	@RequestMapping("/queryMenu")
	@ResponseBody
	public Map<String, Object> queryByMenuById(HttpSession httpSession){
	    BranchEmployee employee = (BranchEmployee) httpSession.getAttribute("employee");
	    List<BranchModel> queryByMenuById = branchIndexService.queryByMenuById(employee.getRoleId());
	    Map<String, Object> responseByData = ToolClass.responseByData();
	    responseByData.put("data", queryByMenuById);
	    return responseByData;
	    
	}
	
	@RequestMapping("/queryStorefactsheet")
	@ResponseBody
	public Map<String, Object> queryStorefactsheet(HttpSession httpSession){
	    BtanchStorefactsheet queryStorefactsheet = branchIndexService.queryStorefactsheet();
	    if (queryStorefactsheet!=null) {
		httpSession.setAttribute("Storefactsheet", queryStorefactsheet);
	    }
	    Map<String, Object> responseByData = ToolClass.responseByData();
	    responseByData.put("data", queryStorefactsheet);
	    return responseByData;
	    
	}
}
