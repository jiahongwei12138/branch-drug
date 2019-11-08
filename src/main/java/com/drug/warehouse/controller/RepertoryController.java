

package com.drug.warehouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drug.entity.BranchProduct;
import com.drug.entity.BranchStore;
import com.drug.util.ToolClass;
import com.drug.warehouse.service.RepertoryService;

@RestController 
@RequestMapping("warehouse")
public class RepertoryController {
	@Autowired
	private RepertoryService repertoryService;
	
	@RequestMapping("selectAllProduct.do")
	public Map<String, Object>  selectAllProduct(Integer page,Integer limit) {
		/*
		 * String pageString=request.getParameter("page"); Integer page=0;
		 * if(pageString!=null&&!pageString.equals("")) {
		 * page=Integer.parseInt(pageString)-1; } String
		 * limitString=request.getParameter("limit"); Integer limit=0;
		 * if(limitString!=null&&!limitString.equals("")) {
		 * limit=Integer.parseInt(limitString); }
		 */
		page=page-1;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("limit", limit);
		List<BranchProduct> list=repertoryService.selectAllProduct(map);
		Integer count=repertoryService.getProductCount();
		Map<String, Object> mapResult=ToolClass.responseByData(list, count);
		return mapResult;
	}
	@RequestMapping("selectByProName.do")
	public Map<String, Object> selectByProName(String condition,Integer page,Integer limit){
		page=page-1;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("page", page);
		map.put("limit", limit);
		map.put("proName", condition);
		List<BranchProduct> list=repertoryService.selectByProName(map);
		Integer count=repertoryService.getProductCountByProName(condition);
		Map<String, Object> mapResult=ToolClass.responseByData(list, count);
		return mapResult;
	}
	
	@RequestMapping("getStore.do")
	public Map<String, Object> getStore(){
		List<BranchStore> list=repertoryService.getStore();
		Map<String, Object> mapResult=ToolClass.responseByData(list, null);
		return mapResult;
	}
	
	@RequestMapping("selectProductByStoreId.do")
	public Map<String, Object> selectProductByStoreId(Integer storeId,Integer page,Integer limit){
		page=page-1;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("page",page);
		map.put("limit",limit);
		List<BranchProduct> list=null;
		Integer count=null;
		if(storeId!=0) {
			map.put("storeId",storeId);
			list=repertoryService.selectProductByStoreId(map);
			count=repertoryService.getProductCountByStoreId();
		}else {
			list=repertoryService.selectAllProduct(map);
			count=repertoryService.getProductCount();
		}
		Map<String, Object> mapResult=ToolClass.responseByData(list, count);
		return mapResult;
	}
	
}
