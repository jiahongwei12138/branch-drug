package com.drug.entity;
/**
 * 
 * 类的描述：分店采购订单子类
 * @author 刘鑫旺
 * @dateTime 2019年10月23日上午8:40:46
 * @version 1.0
 */
public class BranchPurchaseOrderChild extends BranchPurchaseOrder{
	private String bpoId;  //订单编号
	private String bpoTime; //下单时间
	private int bpurchaseId;  // 采购员(员工主键employeeId)
	private int braId;    //分店编号(主键braId)
	private double bpoTotalPrices; //采购订单总金额
	private String checkStatus; //审核状态(未审核/已批准/未批准/已通过)
	private int checkId; //审核人编号(员工主键employeeId) 
	private int checkTime; //审核日期 
	private String payStatus; //付款状态(未付款/已付款)
	private String storeStatus; //入库状态(未入库/已入库)
	private String qualityStatus; //质检状态(未质检/已质检)
	private String bpurchaseName;//采购员
	private String checkName;//审核员
	public String getBpoId() {
		return bpoId;
	}
	public void setBpoId(String bpoId) {
		this.bpoId = bpoId;
	}
	public String getBpoTime() {
		return bpoTime;
	}
	public void setBpoTime(String bpoTime) {
		this.bpoTime = bpoTime;
	}
	public int getBpurchaseId() {
		return bpurchaseId;
	}
	public void setBpurchaseId(int bpurchaseId) {
		this.bpurchaseId = bpurchaseId;
	}
	public int getBraId() {
		return braId;
	}
	public void setBraId(int braId) {
		this.braId = braId;
	}
	public double getBpoTotalPrices() {
		return bpoTotalPrices;
	}
	public void setBpoTotalPrices(double bpoTotalPrices) {
		this.bpoTotalPrices = bpoTotalPrices;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public int getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
	public String getQualityStatus() {
		return qualityStatus;
	}
	public void setQualityStatus(String qualityStatus) {
		this.qualityStatus = qualityStatus;
	}
	public String getBpurchaseName() {
		return bpurchaseName;
	}
	public void setBpurchaseName(String bpurchaseName) {
		this.bpurchaseName = bpurchaseName;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	
	public BranchPurchaseOrderChild(){
		
	}
	
}
