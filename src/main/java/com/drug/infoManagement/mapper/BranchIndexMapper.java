package com.drug.infoManagement.mapper;

import java.util.List;

import com.drug.entity.BranchEmployee;
import com.drug.entity.BranchModel;


public interface BranchIndexMapper {

	List<BranchModel> queryByMenu();
	
	BranchEmployee login(BranchEmployee branchEmployee);
	
	List<BranchModel> queryByMenuById(Integer roleId);

}
