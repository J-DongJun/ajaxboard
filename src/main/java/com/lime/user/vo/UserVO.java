package com.lime.user.vo;

import org.springframework.stereotype.Component;

@Component("userVO")
public class UserVO {

	private int user_Seq   ;
	private String userId  ;
	private String pwd  ;
	private String userName  ;
	private String reg_Dt  ;
	public int getUser_Seq() {
		return user_Seq;
	}
	public void setUser_Seq(int user_Seq) {
		this.user_Seq = user_Seq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReg_Dt() {
		return reg_Dt;
	}
	public void setReg_Dt(String reg_Dt) {
		this.reg_Dt = reg_Dt;
	}
	
	@Override
	public String toString() {
		return "UserVO [user_Seq=" + user_Seq + ", userId=" + userId + ", pwd=" + pwd + ", userName=" + userName
				+ ", reg_Dt=" + reg_Dt + "]";
	}
	
	
}
