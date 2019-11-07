package com.drug.entity;
/**
 * 
 * 类的描述：采购订单详情子类
 * @author 刘鑫旺
 * @dateTime 2019年10月24日上午10:34:25
 * @version 1.0
 */
public class BranchPurchaseOrderDetailsChild extends BranchPurchaseOrderDetails{
	private int bpodId;// 详情编号id
	private int proId; // 商品id
	private String proName;//商品名称
	private String enlishName;//英语名称
	private String proCure;//作用
	private String hqtName;//出厂商
	private int bpodQuantity; //商品数量
	private double bpodSubtotal;//商品小计
	private String bpoId;// 采购订单表主键id
	private double proPrice;//商品价格
	
	public String getEnlishName() {
		return enlishName;
	}
	public void setEnlishName(String enlishName) {
		this.enlishName = enlishName;
	}
	public String getProCure() {
		return proCure;
	}
	public void setProCure(String proCure) {
		this.proCure = proCure;
	}
	public String getHqtName() {
		return hqtName;
	}
	public void setHqtName(String hqtName) {
		this.hqtName = hqtName;
	}
	public int getBpodId() {
		return bpodId;
	}
	public void setBpodId(int bpodId) {
		this.bpodId = bpodId;
	}
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	public int getBpodQuantity() {
		return bpodQuantity;
	}
	public void setBpodQuantity(int bpodQuantity) {
		this.bpodQuantity = bpodQuantity;
	}
	public double getBpodSubtotal() {
		return bpodSubtotal;
	}
	public void setBpodSubtotal(double bpodSubtotal) {
		this.bpodSubtotal = bpodSubtotal;
	}
	public String getBpoId() {
		return bpoId;
	}
	public void setBpoId(String bpoId) {
		this.bpoId = bpoId;
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
	
	
	
	public BranchPurchaseOrderDetailsChild() {
		
	}
	
	
	
}
