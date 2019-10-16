package com.drug.infoManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.MainModel;
import com.drug.infoManagement.service.AdminIndexService;
import com.drug.util.ToolClass;


@Controller
public class AdminIndexController {

	@Autowired
	private AdminIndexService adminIndexService;
	
	@RequestMapping("/queryMenu")
	@ResponseBody
	public Map<String,Object> queryByMenu(){
		List<MainModel> menuList = adminIndexService.queryByMenu();
		Map<String,Object> map = ToolClass.responseByData();
		map.put("data", menuList);
		return map;
	}
}
