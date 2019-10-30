package com.drug.infoManagement.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BranchEmployee;
import com.drug.entity.BranchModel;
import com.drug.entity.BranchStorefactsheet;
import com.drug.infoManagement.mapper.BranchIndexMapper;
import com.drug.infoManagement.service.BranchIndexService;
@Service
public class BranchIndexServiceImpl implements BranchIndexService {

	@Autowired
	private BranchIndexMapper branchIndexMapper;
	
	@Override
	public List<BranchModel> queryByMenu() {
		return branchIndexMapper.queryByMenu();
	}

	@Override
	public BranchEmployee login(BranchEmployee branchEmployee, HttpSession httpSession) {
	    BranchEmployee employee = branchIndexMapper.login(branchEmployee);
	    httpSession.setAttribute("employee", employee);
	    return employee;
	}

	@Override
	public List<BranchModel> queryByMenuById(Integer roleId) {
	    return branchIndexMapper.queryByMenuById(roleId);
	}

	@Override
	public BranchStorefactsheet queryStorefactsheet() {
	    return branchIndexMapper.queryStorefactsheet();
	}

}
