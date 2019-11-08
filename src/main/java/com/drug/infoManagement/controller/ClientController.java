package com.drug.infoManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.BranchClient;
import com.drug.infoManagement.service.ClientService;
import com.drug.util.ToolClass;

@Controller
public class ClientController {
    
    @Autowired
    private ClientService Service;
    
    @RequestMapping("/queryAllClient")
    @ResponseBody
    public Map<String, Object> queryAllClient(int page,int limit,String clientName){
	int pages = (page-1)*limit;
	HashMap<String, Object> hashMap = new HashMap<>();
	hashMap.put("page", pages);
	hashMap.put("limit", limit);
	hashMap.put("clientName", clientName);
	List<BranchClient> queryAllClient = Service.queryAllClient(hashMap);
	int countClient = Service.getCountClient();
	Map<String, Object> responseByData = ToolClass.responseByData(queryAllClient, countClient);
	return responseByData;
	
    }
    
    @RequestMapping("/deleteCliById")
    @ResponseBody
    public boolean deleteCliById(String clientId) {
	try {
	    Service.deleteCliById(clientId);
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }
    
    @RequestMapping("/updateCliById")
    @ResponseBody
    public boolean updateCliById(BranchClient branchClient) {
	try {
	    Service.updateCliById(branchClient);
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }
    
    @RequestMapping("/addClient")
    @ResponseBody
    public boolean addClient(BranchClient branchClient) {
	try {
	    Service.addClient(branchClient);
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }
    
}
