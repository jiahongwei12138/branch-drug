package com.drug.entity;

/**
 * 
 * 描述:质检表实体类
 * 
 * @author 陈俊驰
 * @date 2019年10月23日 下午4:35:06
 */
public class BranchQuality {
	private Integer quaId;		//质检表Id
	private Integer bpoId;		//采购订单表ID
	private String quaDate;		//质检时间
	private Integer employeeId;//质检人Id
	private String state;
	public Integer getQuaId() {
		return quaId;
	}
	public void setQuaId(Integer quaId) {
		this.quaId = quaId;
	}
	public Integer getBpoId() {
		return bpoId;
	}
	public void setBpoId(Integer bpoId) {
		this.bpoId = bpoId;
	}
	public String getQuaDate() {
		return quaDate;
	}
	public void setQuaDate(String quaDate) {
		this.quaDate = quaDate;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
