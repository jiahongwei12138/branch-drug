package com.drug.infoManagement.service.impl;

import java.util.HashMap;
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

    @Override
    public List<Integer> queryModelIdByRoleId(Integer roleId) {
	return roleMapper.queryModelIdByRoleId(roleId);
    }

    @Override
    public void assignAuthority(String roleId, Integer[] modelIds) {
	//将角色id和模块id数组方法map集合
	Map<String, Object> map=new HashMap<String, Object>();
	map.put("roleId", roleId);
	map.put("modelIds", modelIds);
	//先根据角色id删除该角色所拥有的模块id
	roleMapper.deleteAuthorityByRoleId(roleId);
	//再为该角色添加新分配的模块id
	roleMapper.addAuthority(map);

    }

}
