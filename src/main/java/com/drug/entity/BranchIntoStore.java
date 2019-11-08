package com.drug.entity;

/**
 * 
 * 描述:入库表实体类
 * 
 * @author 陈俊驰
 * @date 2019年10月23日 下午2:54:58
 */
public class BranchIntoStore {
	private Integer isId;		//入库ID
	private Integer quaId;		//质检表ID
	private String isDate;		//入库时间
	private Integer employeeId;//入库操作人Id
	private Integer storeId;	//仓库Id
	private String intoState;	//入库状态
	public Integer getIsId() {
		return isId;
	}
	public void setIsId(Integer isId) {
		this.isId = isId;
	}
	public Integer getQuaId() {
		return quaId;
	}
	public void setQuaId(Integer quaId) {
		this.quaId = quaId;
	}
	public String getIsDate() {
		return isDate;
	}
	public void setIsDate(String isDate) {
		this.isDate = isDate;
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
	public String getIntoState() {
		return intoState;
	}
	public void setIntoState(String intoState) {
		this.intoState = intoState;
	}
	
	
}
