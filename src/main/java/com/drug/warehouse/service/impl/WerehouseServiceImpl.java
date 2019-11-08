package com.drug.warehouse.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BranchStore;
import com.drug.warehouse.mapper.WerehouseMapper;
import com.drug.warehouse.service.WerehouseService;
@Service
public class WerehouseServiceImpl implements WerehouseService {
	@Autowired
	private WerehouseMapper werehouseMapper;
	
	@Override
	public List<BranchStore> selectAllStore(Map<String, Object> map) {
		return werehouseMapper.selectAllStore(map);
	}

	@Override
	public Integer getStoreCount() {
		return werehouseMapper.getStoreCount();
	}

	@Override
	public Integer addStore(BranchStore store) {
		return werehouseMapper.addStore(store);
	}

	@Override
	public Integer deleteStoreById(Integer storeId) {
		return werehouseMapper.deleteStoreById(storeId);
	}

}
