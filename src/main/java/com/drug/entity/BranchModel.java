package com.drug.entity;

import java.util.ArrayList;
import java.util.List;

public class BranchModel {

	private Integer id;//modelId  
	private String title;//modelName              
	private Integer parentModelId;  
	private String modelImg;               
	private String modelUrl;                              
	private boolean spread=false;
	private boolean checked=false;
	private Integer menuType;
	/**
	 * 子模块集合
	 */
	private List<BranchModel> children=new ArrayList<BranchModel>();
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
	public boolean isSpread() {
	    return spread;
	}
	public void setSpread(boolean spread) {
	    this.spread = spread;
	}
	public boolean isChecked() {
	    return checked;
	}
	public void setChecked(boolean checked) {
	    this.checked = checked;
	}
	public Integer getMenuType() {
	    return menuType;
	}
	public void setMenuType(Integer menuType) {
	    this.menuType = menuType;
	}
	public List<BranchModel> getChildren() {
	    return children;
	}
	public void setChildren(List<BranchModel> children) {
	    this.children = children;
	}
	
	
	
}
