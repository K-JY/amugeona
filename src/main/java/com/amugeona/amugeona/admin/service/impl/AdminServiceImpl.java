package com.amugeona.amugeona.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.amugeona.amugeona.admin.dao.AdminDAO;
import com.amugeona.amugeona.admin.service.AdminService;
import com.amugeona.amugeona.dao.AmugeonaDAO;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Resource(name = "adminDAO")
	private AdminDAO adminDAO;
	@Override
	public Map<String, Object> login(Map<String, Object> map) throws Exception {
		return adminDAO.login(map);
	}
	@Override
	public List<Map<String, Object>> selectFoodType(Map<String, Object> map) throws Exception {
		return adminDAO.selectFoodType(map);
	}
	@Override
	public int insertFoodInfo(Map<String, Object> map) throws Exception {
		return adminDAO.insertFoodInfo(map);
	}
	@Override
	public int insertFoodMap(Map<String, Object> map) throws Exception {
		return adminDAO.insertFoodMap(map);
	}
	@Override
	public List<Map<String, Object>> selectStepType(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.selectStepType(map);
	}
	@Override
	public List<Map<String, Object>> selectFoodList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.selectFoodList(map);
	}
	@Override
	public int updateFoodInfo(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.updateFoodInfo(map);
	}
	@Override
	public int updateFoodMap(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return adminDAO.updateFoodMap(map);
	}

}

	
