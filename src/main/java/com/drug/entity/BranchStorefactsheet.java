package com.drug.entity;

public class BranchStorefactsheet {
    private Integer sfsId;		//分店id
    private String sfsname;		//分店名称
    private String sfslocation;		//地址
    private String sfsopendate;		//开业时间
    private Integer empId;		//店长id(员工表外键)
    private Integer sfsnetarea;		//占地面积
    private Integer sfscrewSize;	//员工人数
    private String sfscontact;		//联系方式
    private String sfsemail;		//电子邮箱
    private double sfsregisiteredamount;//注册金额
    private String field1;		//字段1
    private String field2;		//字段2
    public Integer getSfsId() {
        return sfsId;
    }
    public void setSfsId(Integer sfsId) {
        this.sfsId = sfsId;
    }
    public String getSfsname() {
        return sfsname;
    }
    public void setSfsname(String sfsname) {
        this.sfsname = sfsname;
    }
    public String getSfslocation() {
        return sfslocation;
    }
    public void setSfslocation(String sfslocation) {
        this.sfslocation = sfslocation;
    }
    public String getSfsopendate() {
        return sfsopendate;
    }
    public void setSfsopendate(String sfsopendate) {
        this.sfsopendate = sfsopendate;
    }
    public Integer getEmpId() {
        return empId;
    }
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
    public Integer getSfsnetarea() {
        return sfsnetarea;
    }
    public void setSfsnetarea(Integer sfsnetarea) {
        this.sfsnetarea = sfsnetarea;
    }
    public Integer getSfscrewSize() {
        return sfscrewSize;
    }
    public void setSfscrewSize(Integer sfscrewSize) {
        this.sfscrewSize = sfscrewSize;
    }
    public String getSfscontact() {
        return sfscontact;
    }
    public void setSfscontact(String sfscontact) {
        this.sfscontact = sfscontact;
    }
    public String getSfsemail() {
        return sfsemail;
    }
    public void setSfsemail(String sfsemail) {
        this.sfsemail = sfsemail;
    }
    public double getSfsregisiteredamount() {
        return sfsregisiteredamount;
    }
    public void setSfsregisiteredamount(double sfsregisiteredamount) {
        this.sfsregisiteredamount = sfsregisiteredamount;
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
