package com.lime.user.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lime.user.vo.UserVO;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("userDAO")

public class UserDao extends EgovAbstractMapper{
	
	
	// 회원 추가
	public void insertUser(UserVO userVO) throws Exception{
		 insert("insertUser", userVO);
	}
	
	//아이디 중복체크
	public EgovMap idChk(Map<String, Object> inPutMap) throws Exception{
		System.out.println("DAO userId >>>>>>>>>>" + inPutMap);
		EgovMap result = selectOne("idCheck", inPutMap);
		return result;
	}
}
