package com.drug.infoManagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BranchRole;
import com.drug.infoManagement.mapper.RoleMapper;
import com.drug.infoManagement.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<BranchRole> queryRoleIdName() {
	return roleMapper.queryRoleIdName();
    }

    @Override
    public List<BranchRole> queryAllRole(Map<String, Integer> map) {
	return roleMapper.queryAllRole(map);
    }

    @Override
    public int queryCountRole() {
	return roleMapper.queryCountRole();
    }

    @Override
    public String addRole(BranchRole branchRole) {
	BranchRole role = roleMapper.queryRoleName(branchRole.getRoleName());
	if (role!=null&&"1".equals(branchRole.getVisibles())) {
	    return "该角色已经存在";
	}else if (role!=null&&!"1".equals(branchRole.getVisibles())) {
	    roleMapper.updatevisibles(branchRole);
	    return "添加成功";
	}else {
	    roleMapper.addRole(branchRole);
	    return "添加成功";
	}
	
    }

    @Override
    public void deletRoleById(String roleId) {
	roleMapper.delRole(roleId);
	
	roleMapper.delEmpRole(roleId);
	
	roleMapper.delRoleModuleRole(roleId);
	
    }

    @Override
    public void updateRoleById(BranchRole branchRole) {
	roleMapper.updateRoleById( branchRole);
	
    }

}
