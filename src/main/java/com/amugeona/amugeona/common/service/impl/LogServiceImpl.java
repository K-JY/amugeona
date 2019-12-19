package com.amugeona.amugeona.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.amugeona.amugeona.common.dao.LogDAO;
import com.amugeona.amugeona.common.service.LogService;
import com.amugeona.amugeona.menupick.common.Util;

@Service("logService")
public class LogServiceImpl implements LogService{
	@Resource(name = "logDAO")
	private LogDAO logDAO;
	
	public int InsertLog(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return logDAO.insertLog(map);
	}

	@Override
	public int InsertTypeLog(Map<String, Object> map, String cookieId) throws Exception {
		String logIdx = Util.getLogIdx();
		String logType1 = "LG002";
		for(int i = 2;i<10;i++) {
			String typeContent = "";
			String logType2 = "CM00"+i;
			if(map.get("CM00"+i) == null) {
				typeContent = "";
			}else {
				typeContent = (String)map.get("CM00"+i);
			}
			
			Map<String, Object> logMap = new HashMap<String, Object>();
			logMap.put("logIdx",logIdx);
			logMap.put("logType1",logType1);
			logMap.put("logType2",logType2);
			logMap.put("logContent",typeContent);
			logMap.put("cookieId",cookieId);
			
			logDAO.insertLog(logMap);
		}
		
		return 0;
	}

}
