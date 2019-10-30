package com.drug.infoManagement.mapper;

import java.util.List;
import java.util.Map;

import com.drug.entity.BranchRole;

public interface RoleMapper {
    
    /**
     * 功能：拿到角色表id和姓名
     * @return 角色列表
     *@datetime2019年10月29日上午10:04:13
     */
    List<BranchRole> queryRoleIdName();
    
    /**
     * 功能：拿到分页的角色表
     * @param map limit 和 page
     * @return 角色表
     *@datetime2019年10月30日上午9:18:32
     */
    List<BranchRole> queryAllRole(Map<String, Integer> map);
    
    /**
     * 功能：得到总行数
     * @return 行数
     *@datetime2019年10月30日上午9:20:08
     */
    int queryCountRole();
    
    /**
     * 功能：新增角色表
     * @param branchRole 角色
     *@datetime2019年10月30日上午10:05:25
     */
    void addRole(BranchRole branchRole);
    
    /**
     * 功能：查询是否已有重名角色
     * @param Rolename 角色名
     * @return 查询对象
     *@datetime2019年10月30日上午10:08:20
     */
    BranchRole queryRoleName(String Rolename);
    
    /**
     * 功能：修改显示状态
     * 
     *@datetime2019年10月30日上午10:22:54
     */
    void updatevisibles(BranchRole branchRole);
    
    /**
     * 功能：删除角色
     * @param roleId 角色id
     *@datetime2019年10月30日上午11:17:52
     */
    void delRole(String roleId);
    
    /**
     * 功能：更改员工角色为0
     * @param roleId
     *@datetime2019年10月30日上午11:18:20
     */
    void delEmpRole(String roleId);
    
    /**
     * 功能：删除权限表中相关角色
     * @param roleId
     *@datetime2019年10月30日上午11:18:56
     */
    void delRoleModuleRole(String roleId);
    
    /**
     * 功能：根据id修改角色
     * @param roleId
     *@datetime2019年10月30日上午11:39:17
     */
    void updateRoleById(BranchRole branchRole);
}
