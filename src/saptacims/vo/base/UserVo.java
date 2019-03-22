package saptacims.vo.base;

import java.io.Serializable;

import saptacims.model.TbUser;

public class UserVo extends TbUser implements Serializable{
	private String oldPassword;
	private String newPwd;
	private String confirmNewPwd;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getConfirmNewPwd() {
		return confirmNewPwd;
	}
	public void setConfirmNewPwd(String confirmNewPwd) {
		this.confirmNewPwd = confirmNewPwd;
	}
    
}