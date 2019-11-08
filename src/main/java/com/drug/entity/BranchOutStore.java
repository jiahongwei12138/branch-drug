package com.drug.entity;

/**
 * 
 * 描述:出库表实体类
 * 
 * @author 陈俊驰
 * @date 2019年10月23日 下午2:55:18
 */
public class BranchOutStore {
	private Integer osId;			//出库表ID
	private Integer soId;			//销售表ID
	private String osDate;			//出库时间
	private Integer employeeId;		//出库操作人ID
	private Integer storeId;		//仓库ID
	private String outState;		//出库状态
	public Integer getOsId() {
		return osId;
	}
	public void setOsId(Integer osId) {
		this.osId = osId;
	}
	public Integer getSoId() {
		return soId;
	}
	public void setSoId(Integer soId) {
		this.soId = soId;
	}
	public String getOsDate() {
		return osDate;
	}
	public void setOsDate(String osDate) {
		this.osDate = osDate;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOutState() {
		return outState;
	}
	public void setOutState(String outState) {
		this.outState = outState;
	}
	
	
	
}
