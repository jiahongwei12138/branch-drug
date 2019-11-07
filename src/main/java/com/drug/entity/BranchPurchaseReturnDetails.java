package com.drug.entity;
/**
 * 
 * 类的描述：采购退货详情类
 * @author 刘鑫旺
 * @dateTime 2019年10月22日上午10:01:37
 * @version 1.0
 */
public class BranchPurchaseReturnDetails {
	private int bprdId;//退货详情主键id
	private int proId;//商品id(商品表主键id)
	private String proName;//商品名称
	private double proPrice;//商品价格
	private int bprdQuantity;//商品数量
	private double bprdSubtotal;//商品小计
	private int bprId;//退货单ID(退货订单表主键id)
	public int getBprdId() {
		return bprdId;
	}
	public void setBprdId(int bprdId) {
		this.bprdId = bprdId;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public int getBprdQuantity() {
		return bprdQuantity;
	}
	public void setBprdQuantity(int bprdQuantity) {
		this.bprdQuantity = bprdQuantity;
	}
	
	public int getBprId() {
		return bprId;
	}
	public void setBprId(int bprId) {
		this.bprId = bprId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public double getProPrice() {
		return proPrice;
	}
	public void setProPrice(double proPrice) {
		this.proPrice = proPrice;
	}
	public double getBprdSubtotal() {
		return bprdSubtotal;
	}
	public void setBprdSubtotal(double bprdSubtotal) {
		this.bprdSubtotal = bprdSubtotal;
	}
	public BranchPurchaseReturnDetails() {
		super();
	}
	
}
