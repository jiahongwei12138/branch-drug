package com.drug.infoManagement.service;

import java.util.List;
import java.util.Map;

import com.drug.entity.BranchRole;

public interface RoleService {
    
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
     * 功能：新增角色
     * @param branchRole 角色
     * @return 返回消息
     *@datetime2019年10月30日上午10:16:40
     */
    String addRole(BranchRole branchRole);
    
    /**
     * 功能：根据角色id删除角色及相关
     * @param roleId
     * @return
     *@datetime2019年10月30日上午11:26:51
     */
    void deletRoleById(String roleId);
    
    /**
     * 功能：根据id修改角色
     * @param roleId
     *@datetime2019年10月30日上午11:39:17
     */
    void updateRoleById(BranchRole branchRole );
    
    List<Integer> queryModelIdByRoleId(Integer roleId);
    
    void assignAuthority(String roleId, Integer[] modelIds);
}
