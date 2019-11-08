package com.drug.purchaseManagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drug.entity.BranchPurchaseOrderDetails;
import com.drug.entity.BranchPurchaseReturnDetails;
import com.drug.entity.MainProduct;
import com.drug.purchaseManagement.service.PurchaseOrderService;
import com.drug.purchaseManagement.service.PurchaseReturnService;
import com.drug.purchaseManagement.util.HttpClientUtil;
import com.drug.util.ToolClass;
/**
 * 
 * 类的描述：接口控制层
 * @author 刘鑫旺
 * @dateTime 2019年11月5日下午6:37:41
 * @version 1.0
 */
@Controller
@RequestMapping("/purchase")
public class MainHeadController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private PurchaseReturnService purchaseReturnService;
	
	/**
	 * 
	 * 功能：通过接口获取总店传过来的所有商品详情
	 * @return responseByData 总店的商品详情
	 * @dateTime 2019年10月30日下午6:02:47
	 */
	@RequestMapping("/getMainProduct.do")
	@ResponseBody
	public Object getMainProduct(){
		//
		String aMainProduct = HttpClientUtil.postNoParam("http://PC-20190309EOYO:8080/main-drug/getProduct.do");
		//字符串json转json数组
		JSONArray a = JSONArray.parseArray(aMainProduct);
		//json数组转总店商品集合
		List<MainProduct> list = a.toJavaList(MainProduct.class);
		//得到总店商品数据向前端的响应数据
		Map<String, Object> responseByData = ToolClass.responseByData(list, list.size());
		return responseByData;
	}
	
	/**
	 * 
	 * 功能：修改采购订单的审核状态,并在已批准后向总店发送申请
	 * @param checkTime 从前端页面传来的审核时间
	 * @param checkName 从前端页面传来的审核人名称
	 * @param checkStatus 从前端页面传来的审核状态
	 * @param bpoId 从前端页面传来的订单编号
	 * @return flag int值（0/1）0代表失败    1 代表成功
	 * @dateTime 2019年10月30日下午2:35:42
	 */
	@RequestMapping("updateOrder.do")
	@ResponseBody
	public int updateOrder(String checkTime,String checkName,String checkStatus,String bpoId) {
		System.err.println(checkName);
		//根据审核人名称得到审核人编号
		//int checkId=purchaseOrderService.selectEmpId(checkName);
		int checkId=1005;
		//根据订单编号修改订单审核时间、审核人id、审核状态得到受影响的行数
		int flag=purchaseOrderService.updateOrder(checkTime,checkId,checkStatus,bpoId);
		//在审核已批准后向总店发送申请，未批准就不发送
		if ("已批准".equals(checkStatus)) {
			//通过订单编号得到所有这条订单的所有详情
			List<BranchPurchaseOrderDetails> bpodlist=purchaseOrderService.queryOrderDetailsById(bpoId);
			//当订单详情不为空时才发送，为空不发送
			if (bpodlist!=null) {
				//设置请求接口路径
				String url=	"http://PC-20190309EOYO:8080/main-drug/getBranchPurchaseOrderDetails.do";	
				//post请求方式发送商品详情给总店
				HttpClientUtil.post(url, 1, bpodlist);
			}
		}
		return flag;
	}
	/**
	 * 
	 * 功能：通过接口获取总店传过来的订单编号去修改审核状态
	 * @param bpoId 订单编号
	 * @dateTime 2019年10月31日上午11:14:17
	 */
	@RequestMapping("/updateCheckStatusById.do")
	public void updateCheckStatusById(String bpoId) {
		//在数据库修改订单审核状态得到受影响的行数
		int count=purchaseOrderService.updateCheckStatusById(bpoId);
		if(count>0){
			System.err.println("修改成功");
		}else{
			System.err.println("修改失败");

		}
		
	}
	
	/**
	 * 
	 * 功能：修改采购退货单的审核状态,并在已批准后向总店发送申请
	 * @param checkTime 从前端页面传来的审核时间
	 * @param checkName 从前端页面传来的审核人名称
	 * @param checkStatus 从前端页面传来的审核状态
	 * @param bprId 从前端页面传来的退货单编号
	 * @return flag int值（0/1）0代表失败    1 代表成功
	 * @dateTime 2019年11月3日下午9:57:42
	 */
	@RequestMapping("updateReturn.do")
	@ResponseBody
	public int updateReturn(String checkTime,String checkName,String checkStatus,Integer bprId) {
		System.err.println(checkName);
		//根据审核人名称得到审核人编号
		//int checkId=purchaseOrderService.selectEmpId(checkName);
		int checkId=1005;
		//根据退货单编号修改退货单审核时间、审核人id、审核状态得到受影响的行数
		int flag=purchaseReturnService.updateReturn(checkTime,checkId,checkStatus,bprId);
		//在审核已批准后向总店发送申请，未批准就不发送
		if ("已批准".equals(checkStatus)) {
			//通过退货单编号得到所有这条订单的所有详情
			List<BranchPurchaseReturnDetails> bprdlist=purchaseReturnService.queryReturnDetailsById(bprId);
			//当退货单详情不为空时才发送，为空不发送
			if (bprdlist!=null) {
				//设置请求接口路径http://PC-20190309EOYO:8080/main-drug/getBranchPurchaseOrderDetails.do
				String url="http://PC-20190309EOYO:8080/main-drug/getBranchPurchaseReturnDetails.do";	
				//post请求方式发送商品详情给总店
				HttpClientUtil.post(url, 1, bprdlist);
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * 功能：通过接口获取总店传过来的退货单编号去修改审核状态
	 * @param bprId 退货单编号
	 * @dateTime 2019年11月3日下午9:58:02
	 */
	@RequestMapping("/updateCheckStatusBybprId.do")
	public void updateCheckStatusById(Integer bprId) {
		//在数据库修改退货单审核状态得到受影响的行数
		int count=purchaseReturnService.updateCheckStatusById(bprId);
		if(count>0){
			System.err.println("修改成功");
		}else{
			System.err.println("修改失败");

		}
		
	}
}
