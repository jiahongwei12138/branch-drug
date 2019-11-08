package com.drug.infoManagement.controller;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.drug.entity.BranchEmployee;
import com.drug.infoManagement.service.EmployeeService;
import com.drug.util.ToolClass;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService emp;


    @RequestMapping("/queryAllEmp")
    @ResponseBody
    public Map<String, Object> queryAllemp(Integer page,Integer limit,String empName){
	HashMap<String, Object> hashMap = new HashMap<>();
	Integer pageString = (page-1)*limit;
	hashMap.put("page", pageString);
	hashMap.put("limit", limit);
	hashMap.put("empName", empName);
	List<BranchEmployee> queryAllEmp = emp.queryAllEmp(hashMap);
	int counEmp = emp.getCounEmp();
	Map<String, Object> responseByData = ToolClass.responseByData(queryAllEmp, counEmp);
	return responseByData;

    }

    @RequestMapping("/deletEmpById")
    @ResponseBody
    public boolean deleteEmpById(int empid) {
	int i = emp.deleteEmpById(empid);
	if (i==1) {
	    return true;
	}else {
	    return false;
	}


    }

    @RequestMapping("/updateEmployeeById")
    @ResponseBody
    public boolean updateEmployeeById(BranchEmployee branchEmployee,@RequestParam(required=false) MultipartFile file,
	    HttpSession session) {
	try {
	    
	  //创建配置文件集合
		Properties properties=new Properties();
		//通过类加载器加载资源文件，获得输入流
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("system.properties");
		//将流加载到配置文件集合
		properties.load(in);
		//获取配种文件中的“键”所对应的目录
		String path = properties.getProperty("directory");
		//获取上传的文件名
		String oriName = file.getOriginalFilename();
		//获取上传的图片后缀，使用FilenameUtils工具类，但不会获取.，得自己拼接一个
		String extName = FilenameUtils.getExtension(oriName);
		//为防止存储到数据库的图片重名，设置图片名称
		String picName=UUID.randomUUID().toString();
		//开始上传（transferTo：保存到..），前提得有该文件夹image
		file.transferTo(new File(path+"/"+picName+"."+extName));
		//拼接图片名称
		String headUrl=picName+"."+extName;
		//赋值给员工对象中的头像路径属性
		branchEmployee.setHeadUrl(headUrl);
		//天涯修改员工方法
	       emp.updateEmployeeById(branchEmployee);
	     return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}

    }

    @RequestMapping("/addEmployee")
    @ResponseBody
    public Object addEmployee(BranchEmployee branchEmployee,
	    @RequestParam(required=false) MultipartFile file,
	    HttpSession session) {
	try {
	    
	  //创建配置文件集合
		Properties properties=new Properties();
		//通过类加载器加载资源文件，获得输入流
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("system.properties");
		//将流加载到配置文件集合
		properties.load(in);
		//获取配种文件中的“键”所对应的目录
		String path = properties.getProperty("directory");
		//获取上传的文件名
		String oriName = file.getOriginalFilename();
		//获取上传的图片后缀，使用FilenameUtils工具类，但不会获取.，得自己拼接一个
		String extName = FilenameUtils.getExtension(oriName);
		//为防止存储到数据库的图片重名，设置图片名称
		String picName=UUID.randomUUID().toString();
		//开始上传（transferTo：保存到..），前提得有该文件夹image
		file.transferTo(new File(path+"/"+picName+"."+extName));
		//拼接图片名称
		String headUrl=picName+"."+extName;
		//赋值给员工对象中的头像路径属性
		branchEmployee.setHeadUrl(headUrl);
		//调用添加员工方法
	    
	    emp.addEmployee(branchEmployee);
	     return true;
	}catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}

    }


    @RequestMapping("/addMoreEmp")
    @ResponseBody
    public boolean addMoreEmp(@RequestParam(required=false) MultipartFile file) {
	boolean result = emp.addMoreEmp(file);
	return result;
    }

}
