package com.drug.infoManagement.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.drug.entity.BranchEmployee;
import com.drug.entity.BranchModel;
import com.drug.entity.BtanchStorefactsheet;

public interface BranchIndexService {

	List<BranchModel> queryByMenu();
	
	BranchEmployee login(BranchEmployee branchEmployee,HttpSession httpSession);
	
	List<BranchModel> queryByMenuById(Integer roleId);
	
	BtanchStorefactsheet queryStorefactsheet();
	
}
