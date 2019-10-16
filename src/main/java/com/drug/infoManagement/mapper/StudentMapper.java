package com.drug.infoManagement.mapper;

import java.util.Map;

import com.drug.entity.Student;

public interface StudentMapper {

	void jian(Map<String, Object> map);
	void jia(Map<String, Object> map);
	Student queryAll();
}
