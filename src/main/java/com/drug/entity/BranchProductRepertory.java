package com.drug.entity;

/**
 * 
 * 描述:商品库存表实体类
 * 
 * @author 陈俊驰
 * @date 2019年11月5日 上午9:17:10
 */
public class BranchProductRepertory {
	private Integer proId;			//商品Id
	private Integer storeId;		//仓库Id
	private Integer number;			//库存数量
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
