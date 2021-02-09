package com.lime.user.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.lime.user.dao.UserDao;
import com.lime.user.vo.UserVO;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDAO;
	
	
	// 회원추가
	public void addUser(UserVO userVO) throws Exception{
		userDAO.insertUser(userVO);
	}
	
	// 아이디 중복체크
	public EgovMap idChk(Map<String, Object> inPutMap) throws Exception{
		System.out.println("서비스userId >>>>>>>>>>" + inPutMap);
		EgovMap result = userDAO.idChk(inPutMap);
		return result;
	}
}
