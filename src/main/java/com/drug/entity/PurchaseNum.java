package com.drug.entity;
/**
 * 
 * 类的描述：采购报表界面数据实体类
 * @author 刘鑫旺
 * @dateTime 2019年11月6日下午10:31:05
 * @version 1.0
 */

import java.util.List;

public class PurchaseNum {
	private String month;//月份
	private List<Integer> iList;//数量集合
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<Integer> getiList() {
		return iList;
	}
	public void setiList(List<Integer> iList) {
		this.iList = iList;
	}
	public PurchaseNum(String month, List<Integer> iList) {
		super();
		this.month = month;
		this.iList = iList;
	}
	public PurchaseNum() {
		super();
	}
	
}
