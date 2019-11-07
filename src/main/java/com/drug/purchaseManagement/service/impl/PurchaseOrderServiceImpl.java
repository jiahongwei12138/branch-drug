package com.drug.purchaseManagement.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.BranchPurchaseOrder;
import com.drug.entity.BranchPurchaseOrderChild;
import com.drug.entity.BranchPurchaseOrderDetails;
import com.drug.entity.BranchPurchaseOrderDetailsChild;
import com.drug.entity.MainProduct;
import com.drug.entity.PurchaseEchart;
import com.drug.entity.PurchaseNum;
import com.drug.purchaseManagement.mapper.PurchaseOrderMapper;
import com.drug.purchaseManagement.service.PurchaseOrderService;
/**
 * 
 * 类的描述：采购订单业务实现层
 * @author 刘鑫旺
 * @dateTime 2019年10月23日上午8:59:42
 * @version 1.0
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	/**
	 * 
	 * 功能：查询所有采购订单
	 * @param page 页面传过来的页数
	 * @param limit 每页的显示数
	 * @param searchName 界面传来的模糊查询的值	 
	 * @return List<BranchPurchaseOrderChild> 采购订单子类集合
	 * @dateTime 2019年10月23日上午8:48:06
	 */
	@Override
	public List<BranchPurchaseOrderChild> selectAll(Integer page, Integer limit,String selectName) {
	//计算当前页的前几页的总行数
	int pageIndex=(page-1)*limit;
	//创建一个map集合
	Map<String, Object> map=new HashMap<String, Object>();
	//给集合存值
	map.put("page", pageIndex);
	map.put("limit", limit);
	map.put("selectName", selectName);
	return purchaseOrderMapper.selectAll(map);
	}
   /**
    * 
    * 功能：得到总行数
    * @param selecName 界面传来的模糊查询的值
    * @return int 受影响的行数
    * @dateTime 2019年10月23日下午5:32:02
    */
	@Override
	public int getCount(String selectName) {
		return purchaseOrderMapper.getCount(selectName);
	}
   /**
    * 
    * 功能：新增采购订单
    * @param bpo 分店采购订单类
  	* @param session 会话请求对象
    * @return int 受影响的行数
    * @dateTime 2019年10月28日下午4:30:51
    */
	@Override
	public int insertOrder(BranchPurchaseOrder bpo,HttpSession session) {
		//从session中取出总价
		double total=(Double)session.getAttribute("totalMoney");
		//为采购订单类对象添加订单总价值
		bpo.setBpoTotalPrices(total);
		//从session中取出采购员id
		int id=(Integer)session.getAttribute("bpurchaseId");
		//为采购订单类对象添加采购员id
		bpo.setBpurchaseId(id);
		//从session中取出分店id
		int braId=(Integer)session.getAttribute("braId");
		//为采购订单类对象添加分店编号id
		bpo.setBpurchaseId(braId);
		//时间随机数生成订单编号
		Date date=new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		String bpoId = simple.format(date);
		//为采购订单类对象添加订单编号id
		bpo.setBpoId(bpoId);
		return purchaseOrderMapper.insertOrder(bpo);
	}
   /**
    * 
    * 功能：新增多条采购订单详情
    * @param bpo 分店采购订单对象
	* @param session 会话请求对象
    * @return int 受影响的行数
    * @dateTime 2019年10月28日下午8:15:47
    */
	@Override
	public int insertBatchOrderDetails(BranchPurchaseOrder bpo,HttpSession session) {
		//创建一个采购订单详情类集合
		List<BranchPurchaseOrderDetails> bpodlist=new ArrayList<BranchPurchaseOrderDetails>();
		//时间随机数生成订单编号
		Date date=new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		String bpoId = simple.format(date);
		//从session中取出采购订单详情子类集合
		List<BranchPurchaseOrderDetailsChild> bpodclist= (List<BranchPurchaseOrderDetailsChild>)session.getAttribute("bpodclist");
		//循环遍历采购订单详情子类集合
		for (BranchPurchaseOrderDetailsChild bpodc : bpodclist) {
			//创建一个采购订单详情类对象
			BranchPurchaseOrderDetails	bpod=new BranchPurchaseOrderDetails();
			//为采购订单详情类对象添加商品id
			bpod.setProId(bpodc.getProId());
			//为采购订单详情类对象添加商品名称
			bpod.setProName(bpodc.getProName());
			//为采购订单详情类对象添加商品价格
			bpod.setProPrice(bpodc.getProPrice());
			//为采购订单详情类对象添加订单编号id
			bpod.setBpoId(bpoId);
			//为采购订单详情类对象添加商品数量
			bpod.setBpodQuantity(bpodc.getBpodQuantity());
			//为采购订单详情类对象添加商品小计
			bpod.setBpodSubtotal(bpodc.getBpodSubtotal());	
			//为采购订单详情类对象添加新建的采购订单详情类集合中
			bpodlist.add(bpod);		
		}
		return purchaseOrderMapper.insertBatchOrderDetails(bpodlist);
	}
   /**
    * 
	* 功能：分页查询未审核与未批准的采购订单
	* @param page 页面传过来的页数
	* @param limit  每页的显示数
	* @param selectName 界面传来的模糊查询的值
    * @return List<BranchPurchaseOrderChild> 采购订单子类集合
    * @dateTime 2019年10月29日下午4:07:26
    */
	@Override
	public List<BranchPurchaseOrderChild> selectNotCheckOrder(Integer page, Integer limit, String selectName) {
		//计算当前页的前几页的总行数
		int pageIndex=(page-1)*limit;
		//创建一个map集合
		Map<String, Object> map=new HashMap<String, Object>();
		//给集合存值
		map.put("page", pageIndex);
		map.put("limit", limit);
		map.put("selectName", selectName);
		return purchaseOrderMapper.selectNotCheckOrder(map);
	}
   /**
    * 
    * 功能：得到未审核与未批准的采购订单总行数
	* @param selecName 界面传来的模糊查询的值
    * @return int 受影响的行数
    * @dateTime 2019年10月29日下午4:10:24
    */
	@Override
	public int getNotCheckCount(String selectName) {
		return purchaseOrderMapper.getNotCheckCount(selectName);
	}
   /**
    * 
    * 功能：修改采购订单的审核状态
	* @param checkTime 从前端页面传来的审核时间
	* @param checkId 审核人id
	* @param checkStatus 从前端页面传来的审核状态
	* @param bpoId 从前端页面传来的订单编号
    * @dateTime 2019年10月30日下午2:39:08
    */
	@Override
	public int updateOrder(String checkTime, int checkId, String checkStatus, String bpoId) {
		//创建一个map集合
		Map<String, Object> map=new HashMap<String, Object>();
		//给集合存值
		map.put("checkTime", checkTime);
		map.put("checkId", checkId);
		map.put("checkStatus", checkStatus);
		map.put("bpoId", bpoId);
		return purchaseOrderMapper.updateOrder(map);
	}
   /**
    * 
    * 功能：查询审核人id
    * @param checkName 审核人名称
    * @dateTime 2019年10月30日下午3:23:54
    */
	@Override
	public int selectEmpId(String checkName) {
		return purchaseOrderMapper.selectEmpId(checkName);
	}
   /**
    * 
    * 功能：通过订单编号查询订单详情
    * @param bpoId 订单编号
    * @return List<BranchPurchaseOrderDetails> 订单详情集合
    * @dateTime 2019年10月30日下午6:14:47
    */
	@Override
	public List<BranchPurchaseOrderDetails> queryOrderDetailsById(String bpoId) {
		return purchaseOrderMapper.queryOrderDetailsById(bpoId);
	}
   /**
    * 
    * 功能：通过接口获取总店传过来的订单编号去修改审核状态
    * @param bpoId 订单编号
    * @return int 受影响的行数
    * @dateTime 2019年10月31日上午11:26:41
    */
	@Override
	public int updateCheckStatusById(String bpoId) {
		return purchaseOrderMapper.updateCheckStatusById(bpoId);
	}
   /**
    * 
    * 功能：通过订单编号删除采购订单
    * @param bpoId 订单编号
    * @return int 受影响的行数
    * @dateTime 2019年10月31日下午6:46:07
    */
	@Override
	public int deleteOrder(String bpoId) {
		return purchaseOrderMapper.deleteOrder(bpoId);
	}
   /**
    * 
    * 功能：通过订单编号删除采购订单详情
    * @param bpoId 订单编号
    * @return int 受影响的行数
    * @dateTime 2019年10月31日下午6:51:51
    */
	@Override
	public int deleteDetailById(String bpoId) {
		return purchaseOrderMapper.deleteDetailById(bpoId);
	}
   /**
    * 
    * 功能：查看某条订单详情
    * @param bpoId 订单编号
    * @return List<BranchPurchaseOrderDetails> 订单详情集合
    * @dateTime 2019年10月31日下午8:17:08
    */
	@Override
	public List<BranchPurchaseOrderDetails> selectOrderDetailById(String bpoId) {
		return purchaseOrderMapper.selectOrderDetailById(bpoId);
	}
	/**
	 * 
	 * 功能：新增选购商品详情到session中
	 * @param  mpclist前端所传的json对象数组
	 * @param session 会话请求对象
	 * @return flag boolean值（true/false）
	 * @dateTime 2019年10月25日上午9:08:07
	 */
	@Override
	public boolean insertDetails(List<MainProduct> mpclist, HttpSession session) {
		//设置一个标志来进行判断
		boolean flag=true;
		//初始化总价为0
		double totalMoney=0;
		//CopyOnWriteArrayList是ArrayList的实现类,创建采购订单详情单子类集合	
		List<BranchPurchaseOrderDetailsChild>  sessionlist=new CopyOnWriteArrayList<BranchPurchaseOrderDetailsChild>();
		//判断商品集合是否为空，不为空才进行存值
		if(mpclist.size()==0){
			//为空时将标志改为false
			flag=false;
		}else{
			//循环遍历商品集合
			for (int i = 0; i < mpclist.size(); i++) {
				//得到商品对象
				MainProduct mp = mpclist.get(i);
				//创建一个采购订单详情单子类
				BranchPurchaseOrderDetailsChild bpodc=new BranchPurchaseOrderDetailsChild();
				//为采购订单详情单子类添加商品id值
				bpodc.setProId(mp.getProId());
				//为采购订单详情单子类添加商品名称值
				bpodc.setProName(mp.getProName());
				//为采购订单详情单子类添加商品价格值
				bpodc.setProPrice(mp.getRetailPrice());
				//为采购订单详情单子类添加商品小计值
				bpodc.setBpodSubtotal(mp.getRetailPrice()*mp.getBpodQuantity());
				//为采购订单详情单子类添加商品数量值
				bpodc.setBpodQuantity(mp.getBpodQuantity());
				//为采购订单详情单子类添加商品英文名称值
				bpodc.setEnlishName(mp.getEnlishName());
				//为采购订单详情单子类添加商品作用值
				bpodc.setProCure(mp.getProCure());
				//为采购订单详情单子类添加商品出厂商值
				bpodc.setHqtName(mp.getHqtName());
				//计算出商品总价值
				totalMoney+=bpodc.getProPrice()*bpodc.getBpodQuantity();
				//将采购订单详情单子类对象存入采购订单详情单子类集合中
				sessionlist.add(bpodc);
			}
			//将采购订单详情单子类集合存入session中
			session.setAttribute("bpodclist", sessionlist);
			//将商品总价存入session中
			session.setAttribute("totalMoney", totalMoney);
			String name="荒";
			session.setAttribute("name",name);
			int braId=100001;
			session.setAttribute("braId",braId);
			int bpurchaseId=1001;
			session.setAttribute("bpurchaseId",bpurchaseId);
		}
		return 	flag;	
	}
	/**
	 * 
	 * 功能：删除session中的选购商品
	 * @param id 前台所传的商品id
	 * @param session 会话请求对象
	 * @return flag int值（0/1）0 代表失败    1 代表成功
	 * @dateTime 2019年10月27日下午11:20:33
	 */
	@Override
	public int deleteDetails(Integer id, HttpSession session) {
		//设置一个标志来进行判断
		int flag=0;
		//从session中取出商品总价
		double total=(Double)session.getAttribute("totalMoney");
		//从session中取出采购订单详情单子类集合
		List<BranchPurchaseOrderDetailsChild> bpodclist= (List<BranchPurchaseOrderDetailsChild>)session.getAttribute("bpodclist");
		//循环遍历商品集合
		for (BranchPurchaseOrderDetailsChild bpod : bpodclist) {
			//对传入的id与集合中的商品id进行比较
			if(bpod.getProId()==id){
				//找到要删除的商品对象后，将其在集合中移除
				bpodclist.remove(bpod);
				//总价进行改变
				total-=bpod.getBpodSubtotal();
				//删除成功后改变标志
				flag=1;
			}
		}
		//将新的商品总价存入session中
		session.setAttribute("totalMoney", total);
		//将新的采购订单详情单子类集合存入session中
		session.setAttribute("bpodclist", bpodclist);
		/*List<BranchPurchaseOrderDetailsChild> bpodclist1= (List<BranchPurchaseOrderDetailsChild>)session.getAttribute("bpodclist");
		for (BranchPurchaseOrderDetailsChild bpod : bpodclist1) {
			System.err.println(bpod.getBpodSubtotal()+"sss");
		}*/
		return flag;
	}
   /**
    * 
    * 功能：查找报表数据
    * @return List<PurchaseEchart> 报表数据集合
    * @dateTime 2019年11月5日下午7:10:49
    */
	@Override
	public List<PurchaseNum> reportForms() {
		List<PurchaseNum> pnlist=new ArrayList<PurchaseNum>();
		for (int i = 1; i <= 12; i++) {
			String strMouth="";
			String mouths="";
			if(i<10){
				 strMouth="2019-0"+i;
				    mouths="2019-0"+i;	
			}else{
				strMouth="2019-"+i;
				mouths="2019-"+i;
			}
			//System.err.println("asd:"+strMouth+"/t"+mouths);
			List<PurchaseEchart> rflist=purchaseOrderMapper.reportForms(strMouth);
			if(rflist==null){
				continue;
			}
			//List<String> names=new ArrayList<String>();
			List<Integer> num=new ArrayList<Integer>();
			for (int j = 0; j < rflist.size(); j++) {
				if(mouths.equals(rflist.get(j).getMonths())){
					//int it=rflist.get(j).getTotal();
					//System.err.println(it);
					num.add(rflist.get(j).getTotal());
					//names.add(rflist.get(j).getMonths());					
				}
				
			}
			PurchaseNum pn=new PurchaseNum(mouths,num);
			for (Integer it : num) {
				System.err.println(it);
			}
			for (Integer it : num) {
				System.err.println(it);
			}
			pnlist.add(pn);
			//map.put("name", names);
			//num.clear();
			for (Integer it : num) {
				System.err.println(it);
			}
		}
		return pnlist;
	}

	
	
}
