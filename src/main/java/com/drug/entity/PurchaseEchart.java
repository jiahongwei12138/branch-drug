package com.drug.entity;
/**
 * 
 * 类的描述：报表类
 * @author 刘鑫旺
 * @dateTime 2019年11月5日下午7:00:07
 * @version 1.0
 */
public class PurchaseEchart {
	private String proName;//商品名称
	private int total; //商品数量
	private String months;//月份
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	
}
