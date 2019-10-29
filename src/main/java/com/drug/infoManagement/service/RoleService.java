package com.drug.infoManagement.service;

import java.util.List;

import com.drug.entity.BtanchRole;

public interface RoleService {
    
    /**
     * 功能：拿到角色表id和姓名
     * @return 角色列表
     *@datetime2019年10月29日上午10:04:13
     */
    List<BtanchRole> queryRoleIdName();
}
