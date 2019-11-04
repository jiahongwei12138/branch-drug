package com.drug.infoManagement.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.drug.entity.BranchEmployee;
import com.drug.entity.BranchModel;
import com.drug.entity.BranchStorefactsheet;


public interface BranchIndexMapper {

	List<BranchModel> queryByMenu();
	
	BranchEmployee login(BranchEmployee branchEmployee);
	
	List<BranchModel> queryByMenuById(Integer roleId);
	
	@Select(value = { "SELECT * FROM branch_storefactsheet WHERE sfsid = 1000" })
	BranchStorefactsheet queryStorefactsheet();

}
