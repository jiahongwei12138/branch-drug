package com.drug.infoManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BtanchRole;
import com.drug.infoManagement.mapper.RoleMapper;
import com.drug.infoManagement.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<BtanchRole> queryRoleIdName() {
	return roleMapper.queryRoleIdName();
    }

}
