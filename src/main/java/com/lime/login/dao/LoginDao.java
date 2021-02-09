package com.lime.login.dao;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("loginDAO")

public class LoginDao extends EgovAbstractMapper {
	
	private SqlSession sqlSession;
	// 로그인 검토
	public EgovMap loginChk(Map<String, Object> inPutMap) throws Exception {
		System.out.println("DAOuserId >>>>>>>>>>" + inPutMap);
		EgovMap result = selectOne("loginCheck", inPutMap);
		return result;
	}
	public void logout(HttpSession session) {
		System.out.println("로그아웃 기능 처리");
		session.invalidate();
	}
}
