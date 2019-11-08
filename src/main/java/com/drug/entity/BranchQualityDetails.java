package com.drug.entity;

/**
 * 
 * 描述:质检详情表实体类
 * 
 * @author 陈俊驰
 * @date 2019年10月24日 上午8:38:24
 */
public class BranchQualityDetails{
	private Integer qdId;		//质检详情单ID
	private Integer quaId;		//质检单ID;
	private Integer proId;		//商品表Id;
	private Integer qualifiedNum;//合格数量
	private Integer unqualifiedNum;//不合格数量
	private double  percent;	//合格率
	public Integer getQdId() {
		return qdId;
	}
	public void setQdId(Integer qdId) {
		this.qdId = qdId;
	}
	public Integer getQuaId() {
		return quaId;
	}
	public void setQuaId(Integer quaId) {
		this.quaId = quaId;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public Integer getQualifiedNum() {
		return qualifiedNum;
	}
	public void setQualifiedNum(Integer qualifiedNum) {
		this.qualifiedNum = qualifiedNum;
	}
	public Integer getUnqualifiedNum() {
		return unqualifiedNum;
	}
	public void setUnqualifiedNum(Integer unqualifiedNum) {
		this.unqualifiedNum = unqualifiedNum;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	
}
