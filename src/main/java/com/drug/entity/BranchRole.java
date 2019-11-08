package com.drug.entity;

/**
 * 功能：角色表
 * @author 彭可旺
 * 2019年10月29日上午9:53:26
 * @version 1.0
 */
public class BranchRole {
    private Integer roleId;		//角色表id
    private String roleName;	//角色名称
    private String roleDescribe;//角色描述
    private Integer visibles;	//是否显示
    private String field1;		//字段1
    private String field2;		//字段2
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleDescribe() {
        return roleDescribe;
    }
    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }
    public Integer getVisibles() {
        return visibles;
    }
    public void setVisibles(Integer visibles) {
        this.visibles = visibles;
    }
    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }
    public String getField2() {
        return field2;
    }
    public void setField2(String field2) {
        this.field2 = field2;
    }
    
    
}
