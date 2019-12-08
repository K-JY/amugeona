package com.amugeona.amugeona.admin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {
	public Map<String, Object> login(Map<String, Object> map) throws Exception;

}
