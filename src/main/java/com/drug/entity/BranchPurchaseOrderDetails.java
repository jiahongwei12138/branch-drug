package com.drug.entity;
/**
 * 
 * 类的描述：采购订单详情类
 * @author 刘鑫旺
 * @dateTime 2019年10月22日上午9:45:05
 * @version 1.0
 */
public class BranchPurchaseOrderDetails {
	private int bpodId;// 详情编号id
	private int proId; // 商品id
	private int bpodQuantity; //商品数量
	private int bpoId;// 采购订单表主键id
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
	public int getBpoId() {
		return bpoId;
	}
	public void setBpoId(int bpoId) {
		this.bpoId = bpoId;
	}
	public BranchPurchaseOrderDetails(int bpodId, int proId, int bpodQuantity, int bpoId) {
		super();
		this.bpodId = bpodId;
		this.proId = proId;
		this.bpodQuantity = bpodQuantity;
		this.bpoId = bpoId;
	}
	public BranchPurchaseOrderDetails() {
		super();
	}
	
}
