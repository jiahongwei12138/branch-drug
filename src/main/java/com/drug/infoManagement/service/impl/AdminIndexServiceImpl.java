package com.drug.infoManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.MainModel;
import com.drug.infoManagement.mapper.AdminIndexMapper;
import com.drug.infoManagement.service.AdminIndexService;
@Service
public class AdminIndexServiceImpl implements AdminIndexService {

	@Autowired
	private AdminIndexMapper adminIndexMapper;
	
	@Override
	public List<MainModel> queryByMenu() {
		return adminIndexMapper.queryByMenu();
	}

}
