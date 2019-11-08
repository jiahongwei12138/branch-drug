package com.drug.warehouse.service;

import java.util.List;
import java.util.Map;

import com.drug.entity.BranchStore;

public interface WerehouseService {
	public List<BranchStore> selectAllStore(Map<String, Object> map);
	
	public Integer getStoreCount();
	
	public Integer addStore(BranchStore store);
	
	public Integer deleteStoreById(Integer storeId);
}
