package com.drug.infoManagement.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.BranchModel;
import com.drug.entity.BranchRole;
import com.drug.infoManagement.service.BranchIndexService;
import com.drug.infoManagement.service.RoleService;
import com.drug.util.ToolClass;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private BranchIndexService branchIndexService;

    @RequestMapping("/queryRoleIdName")
    @ResponseBody
    public Map<String, Object> queryRoleIdName(){
	List<BranchRole> queryRoleIdName = roleService.queryRoleIdName();
	Map<String, Object> responseByData = ToolClass.responseByData(queryRoleIdName, 0);
	return responseByData;

    }

    @RequestMapping("/queryAllRole")
    @ResponseBody
    public Map<String, Object> queryAllRole(int page,int limit ){
	int pages = (page-1)*limit;
	int queryCountRole = roleService.queryCountRole();
	Map<String, Integer> map = new HashMap<>();
	map.put("page", pages);
	map.put("limit", limit);
	List<BranchRole> queryAllRole = roleService.queryAllRole(map);
	Map<String, Object> responseByData = ToolClass.responseByData(queryAllRole, queryCountRole);
	return responseByData;

    }

    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(BranchRole branchRole) {
	String addRole = roleService.addRole(branchRole);
	return addRole;

    }

    @RequestMapping("/deletRoleById")
    @ResponseBody
    public boolean deletRoleById(String roleId) {
	try {
	    roleService.deletRoleById(roleId);
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}

    }

    @RequestMapping("/updateRoleById")
    @ResponseBody
    public boolean updateRoleById(BranchRole branchRole) {
	try {
	    roleService.updateRoleById(branchRole);
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    @RequestMapping("/queryAllModel")
    @ResponseBody
    public List<BranchModel> queryAllModel(Integer roleId){
	//创建空的模块集合
	List<BranchModel> modelList=new ArrayList<BranchModel>();
	//查询所有模块，返回模块集合
	List<BranchModel> allModels=branchIndexService.queryByMenu();
	//根据角色id查询模块id，返回模块id集合
	List<Integer> modelIdList=roleService.queryModelIdByRoleId(roleId);
	//创建map集合
	Map<Integer, BranchModel> map=new HashMap<Integer, BranchModel>();
	//遍历返回的模块集合
	for (BranchModel branchModel : allModels) {
	    /*
	     * layui节点的特性：子节点被选中，那么父节点也会被选项。
	     *    如果父节点被选中那么该父节点下的所有子节点都将被选中。
	     *     所以这里判断modelIdList集合中是否包含遍历出的所有模块id，包含则设置模块的checked值都设置为true,否则为false  
	     *    注意在下面需要判断将modelIdList集合中包含的父模块（父节点）的checked值都设置为false  
	     */
	    if (modelIdList.contains(branchModel.getId())) {
		//如果模块id集合中包含遍历出的模块对象中的id，则设置模块复选框状态为被选中的
		branchModel.setChecked(true);
	    }else {
		//如果模块id集合中不包含遍历出的模块对象中的id，则设置模块复选框状态为不被选中的
		branchModel.setChecked(false);
	    }
	    //判断遍历出的模块对象的父id是否为0，为0则为根节点，设置为展开状态
	    if (branchModel.getParentModelId()==0) {
		branchModel.setSpread(true);
	    }
	    //将遍历出的模块对象放入map集合，以模块的主键id为键，对象为值
	    map.put(branchModel.getId(), branchModel);
	}
	//再次遍历返回的模块集合
	for (BranchModel m : allModels) {
	    //以当前遍历出的模块对象作为子节点
	    BranchModel childModel=m;
	    //如果当前模块的父id为0则为根节点，将其添加到空的模块集合中
	    if (childModel.getParentModelId()==0) {
		modelList.add(childModel);
	    }else {
		//否则获取当前模块的父模块id从模块中去寻找当前模块的父模块，返回父模块对象
		BranchModel parentModel=map.get(childModel.getParentModelId());
		/*
		 * layui节点的特性：子节点被选中，那么父节点也会被选项。
		 *    如果父节点被选中那么该父节点下的所有子节点都将被选中。
		 *     所以这里判断将modelIdList集合中包含的父模块（父节点）的checked值都设置为false  
		 */
		//判断模块id集合中是否包含返回的父模块id
		if (modelIdList.contains(parentModel.getId())) {
		    //如果包含，则设置复选框状态为不被选中的
		    parentModel.setChecked(false);
		}
		//将当前模块添加到返回的父模块对象属性的子集合属性
		parentModel.getChildren().add(childModel);
	    }
	}
	return modelList;
    }

    @RequestMapping("/assignAuthority")
    @ResponseBody
    public boolean assignAuthority(String roleId,Integer[] modelIds){
	try {
	    roleService.assignAuthority(roleId,modelIds);
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

}
