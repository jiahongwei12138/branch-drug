package com.drug.entity;
/**
 * 
 * 类的描述：采购退货类
 * @author 刘鑫旺
 * @dateTime 2019年10月22日上午9:51:04
 * @version 1.0
 */
public class BranchPurchaseReturn {
	private int bprId;//采购退货主键id
	private String bprTime;//退单时间
	private int returnId;//退货员(员工主键employeeId) 
	private int braId;//分店编号(主键braId)
	private double bprMoney;//退货金额
	private String checkStatus;//审核状态(已审核/未审核)
	private int checkId;//审核人编号(员工主键employeeId) 
	private String checkTime;//审核日期
	private String payStatus;//付款状态(未收款/已收款)
	private String bprReason;//退货原因
	public int getBprId() {
		return bprId;
	}
	public void setBprId(int bprId) {
		this.bprId = bprId;
	}
	public String getBprTime() {
		return bprTime;
	}
	public void setBprTime(String bprTime) {
		this.bprTime = bprTime;
	}
	public int getReturnId() {
		return returnId;
	}
	public void setReturnId(int returnId) {
		this.returnId = returnId;
	}
	public int getBraId() {
		return braId;
	}
	public void setBraId(int braId) {
		this.braId = braId;
	}
	public double getBprMoney() {
		return bprMoney;
	}
	public void setBprMoney(double bprMoney) {
		this.bprMoney = bprMoney;
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
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getBprReason() {
		return bprReason;
	}
	public void setBprReason(String bprReason) {
		this.bprReason = bprReason;
	}
	public BranchPurchaseReturn(int bprId, String bprTime, int returnId, int braId, double bprMoney, String checkStatus,
			int checkId, String checkTime, String payStatus, String bprReason) {
		super();
		this.bprId = bprId;
		this.bprTime = bprTime;
		this.returnId = returnId;
		this.braId = braId;
		this.bprMoney = bprMoney;
		this.checkStatus = checkStatus;
		this.checkId = checkId;
		this.checkTime = checkTime;
		this.payStatus = payStatus;
		this.bprReason = bprReason;
	}
	public BranchPurchaseReturn() {
		super();
	}
	
}
