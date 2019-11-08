package com.drug.purchaseManagement.service;

import java.util.List;

import javax.servlet.http.HttpSession;


import com.drug.entity.BranchPurchaseOrder;
import com.drug.entity.BranchPurchaseOrderChild;
import com.drug.entity.BranchPurchaseOrderDetails;
import com.drug.entity.MainProduct;
import com.drug.entity.PurchaseEchart;
import com.drug.entity.PurchaseNum;
/**
 * 
 * 类的描述：采购订单业务层
 * @author 刘鑫旺
 * @dateTime 2019年10月23日上午8:59:09
 * @version 1.0
 */
public interface PurchaseOrderService {
	/**
	 * 
	 * 功能：查询所有采购订单
	 * @param page 页面传过来的页数
	 * @param limit 每页的显示数
	 * @param searchName 界面传来的模糊查询的值	 
	 * @return List<BranchPurchaseOrderChild> 采购订单子类集合
	 * @dateTime 2019年10月23日上午8:48:06
	 */
   public List<BranchPurchaseOrderChild> selectAll(Integer page,Integer limit,String selectName);
   /**
    * 
	* 功能：分页查询未审核与未批准的采购订单
	* @param page 页面传过来的页数
	* @param limit  每页的显示数
	* @param selectName 界面传来的模糊查询的值
    * @return List<BranchPurchaseOrderChild> 采购订单子类集合
    * @dateTime 2019年10月29日下午4:07:26
    */
   public List<BranchPurchaseOrderChild> selectNotCheckOrder(Integer page,Integer limit,String selectName);
   /**
    * 
    * 功能：得到总行数
    * @param selecName 界面传来的模糊查询的值
    * @return int 受影响的行数
    * @dateTime 2019年10月23日下午5:32:02
    */
   public int getCount(String selecName);
   /**
    * 
    * 功能：得到未审核与未批准的采购订单总行数
	* @param selecName 界面传来的模糊查询的值
    * @return int 受影响的行数
    * @dateTime 2019年10月29日下午4:10:24
    */
   public int getNotCheckCount(String selecName);
   /**
    * 
    * 功能：新增采购订单
    * @param bpo 分店采购订单类
  	* @param session 会话请求对象
    * @return int 受影响的行数
    * @dateTime 2019年10月28日下午4:30:51
    */
   public int insertOrder(BranchPurchaseOrder bpo,HttpSession session);
   /**
    * 
    * 功能：新增多条采购订单详情
    * @param bpo 分店采购订单对象
	* @param session 会话请求对象
    * @return int 受影响的行数
    * @dateTime 2019年10月28日下午8:15:47
    */
   public int insertBatchOrderDetails(BranchPurchaseOrder bpo,HttpSession session);
   /**
    * 
    * 功能：查询审核人id
    * @param checkName 审核人名称
    * @dateTime 2019年10月30日下午3:23:54
    */
   public int selectEmpId(String checkName);
   /**
    * 
    * 功能：修改采购订单的审核状态
	* @param checkTime 从前端页面传来的审核时间
	* @param checkName 从前端页面传来的审核人名称
	* @param checkStatus 从前端页面传来的审核状态
	* @param bpoId 从前端页面传来的订单编号
    * @dateTime 2019年10月30日下午2:39:08
    */
   public int updateOrder(String checkTime,int checkId,String checkStatus,String bpoId);
   /**
    * 
    * 功能：通过订单编号查询订单详情
    * @param bpoId 订单编号
    * @return List<BranchPurchaseOrderDetails> 订单详情集合
    * @dateTime 2019年10月30日下午6:14:47
    */
   public List<BranchPurchaseOrderDetails> queryOrderDetailsById(String bpoId);
   /**
    * 
    * 功能：通过接口获取总店传过来的订单编号去修改审核状态
    * @param bpoId 订单编号
    * @return int 受影响的行数
    * @dateTime 2019年10月31日上午11:26:41
    */
   public int updateCheckStatusById(String bpoId);
   /**
    * 
    * 功能：通过订单编号删除采购订单
    * @param bpoId 订单编号
    * @return int 受影响的行数
    * @dateTime 2019年10月31日下午6:46:07
    */
   public int deleteOrder(String bpoId);
   /**
    * 
    * 功能：通过订单编号删除采购订单详情
    * @param bpoId 订单编号
    * @return int 受影响的行数
    * @dateTime 2019年10月31日下午6:51:51
    */
   public int deleteDetailById(String bpoId);
   /**
    * 
    * 功能：查看某条订单详情
    * @param bpoId 订单编号
    * @return List<BranchPurchaseOrderDetails> 订单详情集合
    * @dateTime 2019年10月31日下午8:17:08
    */
   public List<BranchPurchaseOrderDetails> selectOrderDetailById(String bpoId);
	/**
	 * 
	 * 功能：新增选购商品详情到session中
	 * @param  mpclist前端所传的json对象数组
	 * @param session 会话请求对象
	 * @return flag boolean值（true/false）
	 * @dateTime 2019年10月25日上午9:08:07
	 */
   public boolean insertDetails(List<MainProduct>  mpclist,HttpSession session);
	/**
	 * 
	 * 功能：删除session中的选购商品
	 * @param id 前台所传的商品id
	 * @param session 会话请求对象
	 * @return flag int值（0/1）0 代表失败    1 代表成功
	 * @dateTime 2019年10月27日下午11:20:33
	 */
   public int deleteDetails(Integer id,HttpSession session);
	
   /**
    * 
    * 功能：查找报表数据
    * @return List<PurchaseNum> 采购报表界面数据类集合
    * @dateTime 2019年11月5日下午7:10:49
    */
   public List<PurchaseNum> reportForms();

}
