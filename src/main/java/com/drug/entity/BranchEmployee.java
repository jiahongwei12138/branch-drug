package com.drug.entity;

public class BranchEmployee {
    private Integer empId;	//员工Id
    private String empName;	//员工姓名
    private String empPwd;	//员工密码
    private Integer empAge;	//员工年龄
    private String empSex;	//员工性别
    private String empTel;	//联系电话
    private String headUrl;	//头像路径
    private String joinTime;	//入职时间
    private String studyUndergo;//学历
    private String empState;	//员工状态
    private Integer roleId;	//角色id（外键）
    private String field1;	//字段1
    private String field2;	//字段2
    public Integer getEmpId() {
        return empId;
    }
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getEmpPwd() {
        return empPwd;
    }
    public void setEmpPwd(String empPwd) {
        this.empPwd = empPwd;
    }
    public Integer getEmpAge() {
        return empAge;
    }
    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }
    public String getEmpSex() {
        return empSex;
    }
    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }
    public String getEmpTel() {
        return empTel;
    }
    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }
    public String getHeadUrl() {
        return headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getJoinTime() {
        return joinTime;
    }
    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }
    public String getStudyUndergo() {
        return studyUndergo;
    }
    public void setStudyUndergo(String studyUndergo) {
        this.studyUndergo = studyUndergo;
    }
    public String getEmpState() {
        return empState;
    }
    public void setEmpState(String empState) {
        this.empState = empState;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
