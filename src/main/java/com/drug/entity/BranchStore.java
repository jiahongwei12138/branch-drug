package com.drug.entity;

/**
 * 
 * 描述:仓库属性表实体类
 * 
 * @author 陈俊驰
 * @date 2019年10月23日 下午2:54:13
 */
public class BranchStore {
 	private Integer storeId;	//仓库Id
 	private String storeName;	//仓库名
 	private Integer capacity;	//仓库容量
 	private String address;		//仓库位置
 	private String creationTime;//创建时间
 	private String state;		//容量状态
 	
 	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
 	
 	
}
