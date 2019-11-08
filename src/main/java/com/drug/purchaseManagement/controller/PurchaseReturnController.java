package com.drug.purchaseManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.BranchPurchaseOrderChild;
import com.drug.entity.BranchPurchaseOrderDetails;
import com.drug.entity.BranchPurchaseReturn;
import com.drug.entity.BranchPurchaseReturnChild;
import com.drug.entity.BranchPurchaseReturnDetails;
import com.drug.purchaseManagement.service.PurchaseReturnService;
import com.drug.util.ToolClass;

/**
 * 
 * 类的描述：采购退货控制层
 * @author 刘鑫旺
 * @dateTime 2019年10月31日下午11:58:55
 * @version 1.0
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseReturnController {
	@Autowired
	private PurchaseReturnService purchaseReturnService;
	/**
	 * 
	 * 功能：新增一条退货订单
	 * @param bpr 采购退货单子类
	 * @return row int值（0/1）0代表失败    1 代表成功
	 * @dateTime 2019年11月1日下午4:53:24
	 */
	@RequestMapping("addReturn.do")
	@ResponseBody
	public int addReturn(BranchPurchaseReturnChild bprc){
		//得到受影响的行数
		int row=purchaseReturnService.addReturn(bprc);
		return row;
	}
	/**
	 * 
	 * 功能：分页查询退货单
	 * @param page 页面传过来的页数
	 * @param limit 每页的显示数
	 * @param searchName 界面传来的模糊查询的值
	 * @return map 采购退货单响应数据
	 * @dateTime 2019年11月3日下午2:13:44
	 */
	@RequestMapping("/selectReturnAll.do")
	@ResponseBody
	public Map<String, Object> selectReturnAll(Integer page,Integer limit,String searchName){
		//得到采购退货单子类集合
		List<BranchPurchaseReturnChild>  bprclist= purchaseReturnService.selectReturnAll(page,limit,searchName);
		//得到查询后的总行数
		int count=purchaseReturnService.getCount(searchName);
		//得到采购退货单向前端的响应数据
		Map<String, Object> map = ToolClass.responseByData(bprclist,count);
		return 	map;	
	}

	/**
	 * 
	 * 功能：查看某条退货单详情
	 * @param id  前台所传的退货单编号id
	 * @return map 采购退货单响应数据
	 * @dateTime 2019年11月3日下午4:24:25
	 */
	@RequestMapping("/selectReturnDetail.do")
	@ResponseBody
	public Map<String, Object> selectReturnDetail(Integer id){
		//得到采购退货详情单集合
		List<BranchPurchaseReturnDetails> bprdlist=purchaseReturnService.selectReturnDetailById(id);
		//得到查询后的总行数
		int count=bprdlist.size();
		//得到采购退货详情单向前端的响应数据
		Map<String, Object> map = ToolClass.responseByData(bprdlist,count);
		return 	map;	
	}
	
	/**
	 * 
	 * 功能：分页查询未审核与未批准的采购退货单
	 * @param page 页面传过来的页数
	 * @param limit  每页的显示数
	 * @param selectName 界面传来的模糊查询的值
	 * @return map 采购退货单响应数据
	 * @dateTime 2019年11月3日下午8:48:30
	 */
	@RequestMapping("/selectNotCheckReturn.do")
	@ResponseBody
	public Map<String, Object> selectNotCheckReturn(Integer page,Integer limit,String searchName){
		//得到采购退货单子类集合
		List<BranchPurchaseReturnChild>  bprclist= purchaseReturnService.selectNotCheckReturn(page,limit,searchName);
		//得到查询后的总行数
		int count=purchaseReturnService.getNotCheckCount(searchName);
		//得到采购退货单向前端的响应数据
		Map<String, Object> map = ToolClass.responseByData(bprclist,count);
		return 	map;	
	}
}
