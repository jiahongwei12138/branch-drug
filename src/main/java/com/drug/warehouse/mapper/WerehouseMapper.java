package com.drug.warehouse.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.drug.entity.BranchStore;
@Repository
public interface WerehouseMapper {
	public List<BranchStore> selectAllStore(Map<String, Object> map);
	
	public Integer getStoreCount();
	
	public Integer addStore(BranchStore store);
	
	public Integer deleteStoreById(Integer storeId);
}
