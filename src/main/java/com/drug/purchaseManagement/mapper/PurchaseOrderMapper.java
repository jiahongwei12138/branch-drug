package com.drug.purchaseManagement.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.drug.entity.BranchPurchaseOrder;
import com.drug.entity.BranchPurchaseOrderChild;
import com.drug.entity.BranchPurchaseOrderDetails;
import com.drug.entity.PurchaseEchart;

/**
 * 
 * 类的描述：采购订单持久层
 * @author 刘鑫旺
 * @dateTime 2019年10月22日下午3:04:23
 * @version 1.0
 */
@Repository
public interface PurchaseOrderMapper {
	/**
	 * 
	 * 功能：查询所有采购订单
	 * @param map 界面所传参数集合
	 * @return List<BranchPurchaseOrderChild> 采购订单子类集合
	 * @dateTime 2019年10月23日上午8:48:06
	 */
   public List<BranchPurchaseOrderChild> selectAll(Map<String, Object> map);
   /**
    * 
    * 功能：查询未审核与未批准的采购订单
    * @param map 界面所传参数集合
    * @return List<BranchPurchaseOrderChild> 采购订单子类集合
    * @dateTime 2019年10月29日下午4:07:26
    */
   public List<BranchPurchaseOrderChild> selectNotCheckOrder(Map<String, Object> map);
   /**
    * 
    * 功能：得到采购订单总行数
	* @param selecName 界面传来的模糊查询的值
    * @return int 受影响的行数
    * @dateTime 2019年10月23日下午5:32:02
    */
   public int getCount(String selectName);
   /**
    * 
    * 功能：得到未审核与未批准的采购订单总行数
	* @param selecName 界面传来的模糊查询的值    * 
    * @return int 受影响的行数
    * @dateTime 2019年10月29日下午4:10:24
    */
   public int getNotCheckCount(String selectName);
   /**
    * 
    * 功能：新增采购订单
    * @param bpo 分店采购订单类
    * @return int 受影响的行数
    * @dateTime 2019年10月28日下午4:30:51
    */
   public int insertOrder(BranchPurchaseOrder bpo);
   /**
    * 
    * 功能：新增多条采购订单详情
    * @param bpodlist  采购订单详情集合
    * @return int 受影响的行数
    * @dateTime 2019年10月28日下午8:15:47
    */
   public int insertBatchOrderDetails(List<BranchPurchaseOrderDetails> bpodlist);
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
    * @dateTime 2019年10月30日下午2:39:08
    */
   public int updateOrder(Map<String, Object> map);
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
    * 功能：查找报表数据
    * @return List<PurchaseEchart> 报表数据集合
    * @dateTime 2019年11月5日下午7:10:49
    */
   public List<PurchaseEchart> reportForms(String strMouth);
	
}
