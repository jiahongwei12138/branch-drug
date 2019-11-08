package com.drug.warehouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drug.entity.BranchStore;
import com.drug.util.ToolClass;
import com.drug.warehouse.service.WerehouseService;

@RestController
@RequestMapping("warehouse")
public class WerehouseController {
	@Autowired
	private WerehouseService werehouseService;
	
	@RequestMapping("selectAllStore.do")
	public Map<String, Object> selectAllStore(Integer page,Integer limit){
		page=page-1;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("limit", limit);
		List<BranchStore> list=werehouseService.selectAllStore(map);
		Integer count=werehouseService.getStoreCount();
		Map<String, Object> mapResult=ToolClass.responseByData(list, count);
		return mapResult;
	}
	@RequestMapping("addStore.do")
	public Integer addStore(BranchStore store) {
		try {
			werehouseService.addStore(store);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	@RequestMapping("deleteStoreById.do")
	public Integer deleteStoreById(Integer storeId) {
		try {
			werehouseService.deleteStoreById(storeId);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}
	
}
