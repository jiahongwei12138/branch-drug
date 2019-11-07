package com.drug.purchaseManagement.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drug.entity.PurchaseEchart;
import com.drug.entity.PurchaseNum;
import com.drug.purchaseManagement.service.PurchaseOrderService;

/**
 * 
 * 类的描述：报表控制层
 * @author 刘鑫旺
 * @dateTime 2019年10月31日下午11:58:55
 * @version 1.0
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseEchartController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@RequestMapping("/reportForms.do")
	@ResponseBody
	public List<PurchaseNum> reportForms(){
		List<PurchaseNum> pnlist=purchaseOrderService.reportForms();
		for (PurchaseNum pn : pnlist) {
			System.err.println(pn.getMonth());
			List<Integer> ilist=pn.getiList();
			for (Integer it : ilist) {
				System.err.println(it);
			}
		}
		return pnlist;
	}
	
}
