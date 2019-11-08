package com.drug.warehouse.mapper;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.drug.entity.BranchProduct;
import com.drug.entity.BranchStore;
@Repository
public interface RepertoryMapper {
	public List<BranchProduct> selectAllProduct(Map<String, Object> map);
	
	public Integer getProductCount();
	
	public Integer getProductCountByProName(String proName);
	
	public List<BranchProduct> selectByProName(Map<String, Object> map);
	
	public List<BranchStore> getStore();
	
	public List<BranchProduct> selectProductByStoreId(Map<String, Object> map);
	
	public Integer getProductCountByStoreId();
}
