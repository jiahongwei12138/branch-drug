package com.drug.infoManagement.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import com.drug.entity.BranchEmployee;

public interface EmployeeMapper {
    
    /**
     * 功能：拿到所有员工
     * @param map 姓名 页面大小，第几页
     * @return 员工列表
     *@datetime2019年10月28日下午3:30:44
     */
    List<BranchEmployee> queryAllEmp(Map<String, Object> map);
    
    /**
     * 功能：拿emp行数
     * @return 行数
     *@datetime2019年10月28日下午3:33:29
     */
    @Select(value = { "SELECT COUNT(*) FROM branch_employee WHERE empState = '未删除'" })
    int getCounEmp();
    
    /**
     * 功能：根据id删除员工
     * @param empid
     * @return 是否成功
     *@datetime2019年10月29日下午2:50:29
     */
    int deleteEmpById(int empid);
    
    /**
     * 功能：根据id修改员工
     * @param branchEmployee
     *@datetime2019年10月29日下午2:53:01
     */
    void updateEmployeeById(BranchEmployee branchEmployee);
    
    /**
     * 功能：增加emp
     * @param branchEmployee 员工对象
     *@datetime2019年10月29日下午3:58:11
     */
    void addEmployee(BranchEmployee branchEmployee);
    
    void addMoreEmp(List<BranchEmployee> empList);
    
    
}
