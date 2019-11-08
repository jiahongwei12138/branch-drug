package com.drug.warehouse.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BranchProduct;
import com.drug.entity.BranchStore;
import com.drug.warehouse.mapper.RepertoryMapper;
import com.drug.warehouse.service.RepertoryService;
@Service
public class RepertoryServiceImpl implements RepertoryService{
	@Autowired
	private RepertoryMapper repertoryMapper;
	
	@Override
	public List<BranchProduct> selectAllProduct(Map<String, Object> map) {
		return repertoryMapper.selectAllProduct(map);
	}

	@Override
	public Integer getProductCount() {
		return repertoryMapper.getProductCount();
	}

	@Override
	public List<BranchProduct> selectByProName(Map<String, Object> map) {
		return repertoryMapper.selectByProName(map);
	}

	@Override
	public Integer getProductCountByProName(String proName) {
		return repertoryMapper.getProductCountByProName(proName);
	}

	@Override
	public List<BranchStore> getStore() {
		return repertoryMapper.getStore();
	}

	@Override
	public List<BranchProduct> selectProductByStoreId(Map<String, Object> map) {
		return repertoryMapper.selectProductByStoreId(map);
	}

	@Override
	public Integer getProductCountByStoreId() {
		return repertoryMapper.getProductCountByStoreId();
	}

	

}
