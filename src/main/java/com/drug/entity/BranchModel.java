package com.drug.entity;

public class BranchModel {

	private Integer id;//modelId  
	private String title;//modelName              
	private Integer parentModelId;  
	private String modelImg;               
	private String modelUrl;                              
	private Integer menuType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getParentModelId() {
		return parentModelId;
	}
	public void setParentModelId(Integer parentModelId) {
		this.parentModelId = parentModelId;
	}
	public String getModelImg() {
		return modelImg;
	}
	public void setModelImg(String modelImg) {
		this.modelImg = modelImg;
	}
	public String getModelUrl() {
		return modelUrl;
	}
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	
	
}
