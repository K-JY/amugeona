package com.amugeona.amugeona.admin.service.impl;

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

}

	
