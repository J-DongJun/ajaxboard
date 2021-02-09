package com.lime.login.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lime.login.dao.LoginDao;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("loginService")
public class LoginService {

	@Autowired
	private LoginDao loginDAO;

	// 로그인 검토
	public EgovMap loginChk(Map<String, Object> inPutMap) throws Exception {
		System.out.println("서비스userId >>>>>>>>>>" + inPutMap);
		EgovMap result = loginDAO.loginChk(inPutMap);
		
		return result;
	}

	public void logout(HttpSession session) {
		loginDAO.logout(session);
	}
}
