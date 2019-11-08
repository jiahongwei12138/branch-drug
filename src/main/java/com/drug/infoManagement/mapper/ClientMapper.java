package com.drug.infoManagement.mapper;

import java.util.List;
import java.util.Map;

import com.drug.entity.BranchClient;

public interface ClientMapper {
    
    /**
     * 功能：得到所有客户对象
     * @param map 分页/模糊查询
     * @return 客户列表
     *@datetime2019年11月4日下午1:43:10
     */
    List<BranchClient> queryAllClient(Map<String, Object> map);
    
    /**
     * 功能：得到客户表总行数
     * @return 行数
     *@datetime2019年11月4日下午1:58:57
     */
    int getCountClient();
    
    /**
     * 功能：根据id删除客户
     * @param clientId 客户id
     *@datetime2019年11月4日下午2:16:39
     */
    void deleteCliById(String clientId);
    
    /**
     * 功能：根据id修改用户表
     * @param branchClient
     *@datetime2019年11月4日下午2:38:10
     */
    void updateCliById(BranchClient branchClient);
    
    /**
     * 功能：新增客户
     * @param branchClient
     *@datetime2019年11月4日下午3:40:50
     */
    void addClient(BranchClient branchClient);
}
