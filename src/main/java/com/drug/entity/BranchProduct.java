package com.drug.entity;


/*proId INT(11) NOT NULL AUTO_INCREMENT COMMENT '产品id',
proName VARCHAR(20) NOT NULL COMMENT '产品名称',
enlishName VARCHAR(20) DEFAULT NULL COMMENT '英文名称',
shape VARCHAR(20) DEFAULT NULL COMMENT '形状',
methodOfApplication VARCHAR(20) DEFAULT NULL COMMENT '用法用量',
packaging VARCHAR(20) DEFAULT NULL COMMENT '包装',
proBatchNumber VARCHAR(20) DEFAULT NULL COMMENT '产品批号',
expirationdate VARCHAR(50) DEFAULT NULL COMMENT '保质期',
storageCondition VARCHAR(20) DEFAULT NULL COMMENT '存储环境',
specification VARCHAR(20) DEFAULT NULL COMMENT '规格',
thebarcode VARCHAR(30) DEFAULT NULL COMMENT '条码编号',
proImageUrl VARCHAR(20) DEFAULT NULL COMMENT '产品图片路径',
proCure VARCHAR(30) DEFAULT NULL COMMENT '功效描述',
proForbidden VARCHAR(30) DEFAULT NULL COMMENT '禁用描述',
proBadness VARCHAR(30) DEFAULT NULL COMMENT '不良反应描述',
costPice DOUBLE DEFAULT NULL COMMENT '成本价',
retailPrice DOUBLE DEFAULT NULL COMMENT '售价',
hqtId INT(11) DEFAULT NULL COMMENT '生产商id（总店id）',
field1 VARCHAR(100) DEFAULT NULL COMMENT '字段1',
field2 VARCHAR(100) DEFAULT NULL COMMENT '字段2',*/

/**
 * 
 * 描述:商品实体类
 * 
 * @author 陈俊驰
 * @date 2019年10月23日 上午10:01:50
 */
public class BranchProduct {
	private Integer proid;						//产品id
	private String proName;						//产品名称
	private String enlishName;					//英文名称
	private String shape;						//形状
	private String methodOfApplication;		//用法用量
	private String packaging;					//包装
	private String proBatchNumber;				//产品批号
	private String expirationdate;				//保质期
	private String storageCondition;			//存储环境
	private String specification;				//规格
	private String thebarcode;					//条码编号
	private String proImageUrl;					//产品图片路径
	private String proCure;						//功效描述
	private String proForbidden;				//禁用描述
	private String proBadness;					//不良反应描述
	private double costPice;					//成本价
	private double retailPrice;				//售价
	private BranchStore branchStore;			//仓库对象
	private Integer number;						//商品库存数量
	
	public BranchStore getBranchStore() {
		return branchStore;
	}
	public void setBranchStore(BranchStore branchStore) {
		this.branchStore = branchStore;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getProid() {
		return proid;
	}
	public void setProid(Integer proid) {
		this.proid = proid;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getEnlishName() {
		return enlishName;
	}
	public void setEnlishName(String enlishName) {
		this.enlishName = enlishName;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getMethodOfApplication() {
		return methodOfApplication;
	}
	public void setMethodOfApplication(String methodOfApplication) {
		this.methodOfApplication = methodOfApplication;
	}
	public String getPackaging() {
		return packaging;
	}
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	public String getProBatchNumber() {
		return proBatchNumber;
	}
	public void setProBatchNumber(String proBatchNumber) {
		this.proBatchNumber = proBatchNumber;
	}
	public String getExpirationdate() {
		return expirationdate;
	}
	public void setExpirationdate(String expirationdate) {
		this.expirationdate = expirationdate;
	}
	public String getStorageCondition() {
		return storageCondition;
	}
	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getThebarcode() {
		return thebarcode;
	}
	public void setThebarcode(String thebarcode) {
		this.thebarcode = thebarcode;
	}
	public String getProImageUrl() {
		return proImageUrl;
	}
	public void setProImageUrl(String proImageUrl) {
		this.proImageUrl = proImageUrl;
	}
	public String getProCure() {
		return proCure;
	}
	public void setProCure(String proCure) {
		this.proCure = proCure;
	}
	public String getProForbidden() {
		return proForbidden;
	}
	public void setProForbidden(String proForbidden) {
		this.proForbidden = proForbidden;
	}
	public String getProBadness() {
		return proBadness;
	}
	public void setProBadness(String proBadness) {
		this.proBadness = proBadness;
	}
	public double getCostPice() {
		return costPice;
	}
	public void setCostPice(double costPice) {
		this.costPice = costPice;
	}
	public double getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	
}
