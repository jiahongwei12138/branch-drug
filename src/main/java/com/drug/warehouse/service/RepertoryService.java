package com.drug.warehouse.service;

import java.util.List;
import java.util.Map;

import com.drug.entity.BranchProduct;
import com.drug.entity.BranchStore;

public interface RepertoryService {
	public List<BranchProduct> selectAllProduct(Map<String, Object> map);
	
	public Integer getProductCount();
	
	public Integer getProductCountByProName(String proName);
	
	public List<BranchProduct> selectByProName(Map<String, Object> map);
	
	public List<BranchStore> getStore();
	
	public List<BranchProduct> selectProductByStoreId(Map<String, Object> map);
	
	public Integer getProductCountByStoreId();
}
