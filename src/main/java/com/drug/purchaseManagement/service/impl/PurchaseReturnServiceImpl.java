package com.drug.purchaseManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BranchPurchaseOrderDetails;
import com.drug.entity.BranchPurchaseReturn;
import com.drug.entity.BranchPurchaseReturnChild;
import com.drug.entity.BranchPurchaseReturnDetails;
import com.drug.purchaseManagement.mapper.PurchaseReturnMapper;
import com.drug.purchaseManagement.service.PurchaseReturnService;

/**
 * 
 * 类的描述：采购退货业务实现层
 * @author 刘鑫旺
 * @dateTime 2019年10月23日上午8:59:42
 * @version 1.0
 */
@Service
public class PurchaseReturnServiceImpl implements PurchaseReturnService{
	@Autowired
	private PurchaseReturnMapper purchaseReturnMapper;

	@Override
	public int addReturn(BranchPurchaseReturnChild bprc) {
		//创建一个采购退货类对象
		BranchPurchaseReturn bpr=new BranchPurchaseReturn();
		//向采购退货类对象中设置属性值
		bpr.setBprTime(bprc.getBprTime());
		bpr.setBraId(bprc.getBraId());
		bpr.setBprReason(bprc.getBprReason());
		bpr.setReturnId(1001);
		return purchaseReturnMapper.addReturn(bpr);
	}
	/**
	 * 
	 * 功能：分页查询退货单
	 * @param page 页面传过来的页数
	 * @param limit 每页的显示数
	 * @param searchName 界面传来的模糊查询的值
	 * @return List<BranchPurchaseReturnChild> 采购退货单子类集合
	 * @dateTime 2019年11月3日下午2:17:05
	 */
	@Override
	public List<BranchPurchaseReturnChild> selectReturnAll(Integer page, Integer limit, String selectName) {
		//计算当前页的前几页的总行数
		int pageIndex=(page-1)*limit;
		//创建一个map集合
		Map<String, Object> map=new HashMap<String, Object>();
		//给集合存值
		map.put("page", pageIndex);
		map.put("limit", limit);
		map.put("selectName", selectName);
		return purchaseReturnMapper.selectReturnAll(map);
	}
	/**
	 * 
	 * 功能：得到总行数
	 * @param selectName 界面传来的模糊查询的值
	 * @return int 受影响的行数
	 * @dateTime 2019年11月3日下午2:23:56
	 */
	@Override
	public int getCount(String selectName) {
		return purchaseReturnMapper.getCount(selectName);
	}
	 /**
	  * 
	  * 功能：查看某条退货单详情
	  * @param bprId 退货单编号
	  * @return List<BranchPurchaseReturnDetails> 退货单详情集合 
	  * @dateTime 2019年11月3日下午4:29:13
	  */
	@Override
	public List<BranchPurchaseReturnDetails> selectReturnDetailById(Integer bprId) {
		return purchaseReturnMapper.selectReturnDetailById(bprId);
	}
	 /**
	  * 
	  * 功能：分页查询未审核与未批准的采购退货单
	  * @param page 页面传过来的页数
	  * @param limit  每页的显示数
	  * @param selectName 界面传来的模糊查询的值
	  * @return List<BranchPurchaseReturnChild> 采购退货单子类集合 
	  * @dateTime 2019年11月3日下午8:49:54
	  */
	@Override
	public List<BranchPurchaseReturnChild> selectNotCheckReturn(Integer page, Integer limit, String selectName) {
		//计算当前页的前几页的总行数
		int pageIndex=(page-1)*limit;
		//创建一个map集合
		Map<String, Object> map=new HashMap<String, Object>();
		//给集合存值
		map.put("page", pageIndex);
		map.put("limit", limit);
		map.put("selectName", selectName);
		return purchaseReturnMapper.selectNotCheckReturn(map);
	}
	  /**
	   * 
	   * 功能：得到未审核与未批准的采购退货单总行数
	   * @param selecName 界面传来的模糊查询的值
	   * @return int 受影响的行数
	   * @dateTime 2019年11月3日下午8:56:10
	   */	
	@Override
	public int getNotCheckCount(String selecName) {
		return purchaseReturnMapper.getNotCheckCount(selecName);
	}
   /**
    * 
    * 功能：修改采购退货单的审核状态
	* @param checkTime 从前端页面传来的审核时间
	* @param checkName 从前端页面传来的审核人名称
	* @param checkStatus 从前端页面传来的审核状态
	* @param bprId 从前端页面传来的退货单编号
	* @dateTime 2019年11月3日下午9:33:20
    */
	@Override
	public int updateReturn(String checkTime, int checkId, String checkStatus, Integer bprId) {
		//创建一个map集合
		Map<String, Object> map=new HashMap<String, Object>();
		//给集合存值
		map.put("checkTime", checkTime);
		map.put("checkId", checkId);
		map.put("checkStatus", checkStatus);
		map.put("bprId", bprId);
		return purchaseReturnMapper.updateReturn(map);
	}
   /**
    * 
    * 功能：通过退货单编号查询订单详情
    * @param bprId 退货单编号
    * @return List<BranchPurchaseReturnDetails> 退货单详情集合
    * @dateTime 2019年11月3日下午9:38:22
    */
	@Override
	public List<BranchPurchaseReturnDetails> queryReturnDetailsById(Integer bprId) {
		return purchaseReturnMapper.queryReturnDetailsById(bprId);
	}
   /**
    * 
    * 功能：通过接口获取总店传过来的退货单编号去修改审核状态
    * @param bprId 退货单编号
    * @return int 受影响的行数
    * @dateTime 2019年11月3日下午10:02:45
    */
	@Override
	public int updateCheckStatusById(Integer bprId) {
		return purchaseReturnMapper.updateCheckStatusById(bprId);
	}
	
}
