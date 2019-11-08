package com.drug.purchaseManagement.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.drug.entity.BranchPurchaseReturn;
import com.drug.entity.BranchPurchaseReturnChild;
import com.drug.entity.BranchPurchaseReturnDetails;
/**
 * 
 * 类的描述：采购退货持久层
 * @author 刘鑫旺
 * @dateTime 2019年11月1日上午12:04:15
 * @version 1.0
 */
@Repository
public interface PurchaseReturnMapper {
	/**
	 * 
	 * 功能：新增一条退货订单
	 * @param bpr 采购退货单对象
	 * @return int 受影响的行数
	 * @dateTime 2019年11月1日下午4:56:22
	 */
	public int addReturn(BranchPurchaseReturn bpr);
	/**
	 * 
	 * 功能：分页查询退货单
	 * @param map 界面所传参数集合
	 * @return List<BranchPurchaseReturnChild> 采购退货单子类集合
	 * @dateTime 2019年11月3日下午2:17:05
	 */
	public List<BranchPurchaseReturnChild> selectReturnAll(Map<String, Object> map);
	/**
	 * 
	 * 功能：得到总行数
	 * @param selectName 界面传来的模糊查询的值
	 * @return int 受影响的行数
	 * @dateTime 2019年11月3日下午2:23:56
	 */
	public int getCount(String selectName);
	 /**
	  * 
	  * 功能：查看某条退货单详情
	  * @param bprId 退货单编号
	  * @return List<BranchPurchaseReturnDetails> 退货单详情集合 
	  * @dateTime 2019年11月3日下午4:29:13
	  */
	 public List<BranchPurchaseReturnDetails> selectReturnDetailById(Integer bprId);
	 /**
	  * 
	  * 功能：分页查询未审核与未批准的采购退货单
	  * @param map 界面所传参数集合
	  * @return List<BranchPurchaseReturnChild> 采购退货单子类集合
	  * @dateTime 2019年11月3日下午8:49:54
	  */
	  public List<BranchPurchaseReturnChild> selectNotCheckReturn(Map<String, Object> map);
	  /**
	   * 
	   * 功能：得到未审核与未批准的采购退货单总行数
	   * @param selecName 界面传来的模糊查询的值
	   * @return int 受影响的行数
	   * @dateTime 2019年11月3日下午8:56:10
	   */
	  public int getNotCheckCount(String selecName);
	   /**
	    * 
	    * 功能：修改采购退货单的审核状态
		* @param map 界面所传参数集合
        * @dateTime 2019年11月3日下午9:33:20
	    */
	   public int updateReturn(Map<String, Object> map);
	   /**
	    * 
	    * 功能：通过退货单编号查询订单详情
	    * @param bprId 退货单编号
	    * @return List<BranchPurchaseReturnDetails> 退货单详情集合
        * @dateTime 2019年11月3日下午9:38:22
	    */
	   public List<BranchPurchaseReturnDetails> queryReturnDetailsById(Integer bprId);
	   /**
	    * 
	    * 功能：通过接口获取总店传过来的退货单编号去修改审核状态
	    * @param bprId 退货单编号
	    * @return int 受影响的行数
	    * @dateTime 2019年11月3日下午10:02:45
	    */
	   public int updateCheckStatusById(Integer bprId);
}
