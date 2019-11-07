package com.drug.purchaseManagement.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.drug.entity.BranchPurchaseOrder;
import com.drug.entity.BranchPurchaseOrderChild;
import com.drug.entity.BranchPurchaseOrderDetails;
import com.drug.entity.BranchPurchaseOrderDetailsChild;
import com.drug.entity.MainProduct;
import com.drug.purchaseManagement.service.PurchaseOrderService;
import com.drug.util.ToolClass;


/**
 * 
 * 类的描述：采购订单控制层
 * @author 刘鑫旺
 * @dateTime 2019年10月23日上午9:00:57
 * @version 1.0
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	/**
	 * 
	 * 功能：分页查询采购订单
	 * @param page 页面传过来的页数
	 * @param limit 每页的显示数
	 * @param searchName 界面传来的模糊查询的值
	 * @return map 采购订单集合数据
	 * @dateTime 2019年10月24日上午10:49:16
	 */
	@RequestMapping("/selectAll.do")
	@ResponseBody
	public Map<String, Object> selectAll(Integer page,Integer limit,String searchName){
		//得到采购订单子类集合
		List<BranchPurchaseOrderChild>  bpoclist= purchaseOrderService.selectAll(page,limit,searchName);
		//得到查询后的总行数
		int count=purchaseOrderService.getCount(searchName);
		//得到采购订单向前端的响应数据
		Map<String, Object> map = ToolClass.responseByData(bpoclist,count);
		return 	map;	
	}
	/**
	 * 
	 * 功能：分页查询未审核与未批准的采购订单
	 * @param page 页面传过来的页数
	 * @param limit  每页的显示数
	 * @param selectName 界面传来的模糊查询的值
	 * @return map 采购订单集合数据
	 * @dateTime 2019年10月29日下午4:01:26
	 */
	@RequestMapping("/selectNotCheckOrder.do")
	@ResponseBody
	public Map<String, Object> selectNotCheckOrder(Integer page,Integer limit,String searchName){
		//分页查询得到采购订单子类集合
		List<BranchPurchaseOrderChild>  bpoclist= purchaseOrderService.selectNotCheckOrder(page,limit,searchName);
		//分页查询得到总行数
		int count=purchaseOrderService.getNotCheckCount(searchName);
		//得到采购订单向前端的响应数据
		Map<String, Object> map = ToolClass.responseByData(bpoclist,count);
		return 	map;	
	}
	/**
	 * 
	 * 功能：新增选购商品详情到session中
	 * @param mpclist 前端所传的json对象数组
	 * @param session 会话请求对象
	 * @return  boolean值（true/false）
	 * @dateTime 2019年10月25日上午9:08:07
	 */
	@RequestMapping("/insertDetails.do")
	@ResponseBody
	public boolean insertDetails(@RequestBody List<MainProduct>  mpclist,HttpSession session){
		return purchaseOrderService.insertDetails(mpclist,session);
	}
	/**
	 * 
	 * 功能：查询session中的选购商品详情
	 * @param session 会话请求对象
	 * @return Map<String, Object> json数据
	 * @dateTime 2019年10月25日上午9:01:36
	 */
	@RequestMapping("/selectDetails.do")
	@ResponseBody
	public Map<String, Object> selectDetails(HttpSession session){
		//从session中取出采购订单详情单子类集合
		List<BranchPurchaseOrderDetailsChild> bpodclist= (List<BranchPurchaseOrderDetailsChild>)session.getAttribute("bpodclist");
		//得到总行数
		int count=bpodclist.size();
		//得到采购订单详情单向前端的响应数据
		Map<String, Object> map = ToolClass.responseByData(bpodclist,count);
		return 	map;	
	}
	/**
	 * 
	 * 功能：删除session中的选购商品
	 * @param id 前台所传的商品id
	 * @param session 会话请求对象
	 * @return  int值（0/1）0 代表失败    1 代表成功
	 * @dateTime 2019年10月27日下午11:20:33
	 */
	@RequestMapping("deleteDetails.do")
	@ResponseBody
	public int deleteDetails(Integer id,HttpSession session) {
		return purchaseOrderService.deleteDetails(id,session);
	}
	
	/**
	 * 
	 * 功能：插入一条采购订单的同时插入多条采购订单详情
	 * @param bpo 分店采购订单对象
	 * @param session 会话请求对象
	 * @return flag int值（0/1）0代表失败    1 代表成功
	 * @dateTime 2019年10月29日上午8:37:16
	 */
	@RequestMapping("/insertOrder.do")
	@ResponseBody
	public int  insertOrder(BranchPurchaseOrder bpo,HttpSession session){
		//设置一个标志来进行判断
		int flag=0;
		//根据采购订单新增数据之后得到受影响的行数
		int bporow=purchaseOrderService.insertOrder(bpo,session);
		//根据采购订单详情新增数据之后得到受影响的行数
		int bpodrow=purchaseOrderService.insertBatchOrderDetails(bpo,session);
		//判断是否成功插入数据
		if(bporow>0&&bpodrow>0){
			//成功
			flag=1;
		}
		return flag;	
	}
	/**
	 * 
	 * 功能：删除采购订单的同时删除采购详情单
	 * @param id 前台所传的订单编号id
	 * @return flag int值（0/1）0 代表失败    1 代表成功
	 * @dateTime 2019年10月31日下午6:37:26
	 */
	@RequestMapping("deleteOrder.do")
	@ResponseBody
	public int deleteOrder(String id) {
		//设置一个标志来进行判断
		int flag=0;
		//根据采购订单删除数据之后得到受影响的行数
		int ocount=purchaseOrderService.deleteOrder(id);
		//根据采购订单详情删除数据之后得到受影响的行数
		int odcount=purchaseOrderService.deleteDetailById(id);
		//判断是否成功插入数据
		if(ocount>0&&odcount>0){
			//成功
			flag=1;
		}
		return flag;
	}
	/**
	 * 
	 * 功能：查看某条订单详情
	 * @param id  前台所传的订单编号id
	 * @return map 采购订单响应数据
	 * @dateTime 2019年10月31日下午8:12:51
	 */
	@RequestMapping("/selectOrderDetail.do")
	@ResponseBody
	public Map<String, Object> selectOrderDetail(String id){
		//得到采购订单详情单集合
		List<BranchPurchaseOrderDetails> bpodlist=purchaseOrderService.selectOrderDetailById(id);
		//得到查询后的总行数
		int count=bpodlist.size();
		//得到采购订单详情单向前端的响应数据
		Map<String, Object> map = ToolClass.responseByData(bpodlist,count);
		return 	map;	
	}
}
