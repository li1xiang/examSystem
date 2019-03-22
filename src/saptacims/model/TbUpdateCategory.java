package saptacims.model;

import java.io.Serializable;

public class TbUpdateCategory extends TbCategory implements Serializable{

	private String createUsername;
	private String updateUsername;
	private String createTimeStr;
	private String updateTimeStr;
	
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
	public TbUpdateCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public String getUpdateUsername() {
		return updateUsername;
	}
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}
	
}
