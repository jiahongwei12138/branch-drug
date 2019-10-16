package com.drug.infoManagement.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drug.entity.Student;
import com.drug.infoManagement.mapper.StudentMapper;
import com.drug.infoManagement.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public void tranferTo(int i, int j, int k) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("lisi", i);
		map.put("wangwu", j);
		map.put("age", k);
		studentMapper.jia(map);
		int a=1/0;
		studentMapper.jian(map);
	}

	@Override
	public Student queryAll() {
		return studentMapper.queryAll();
	}

}
