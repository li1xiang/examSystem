package saptacims.model;

import java.io.Serializable;

public class TbUpdateGroup extends TbGroup implements Serializable{

	private String createrName;
	private String updaterName;
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
	public TbUpdateGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getUpdaterName() {
		return updaterName;
	}
	public void setUpdaterName(String updaterName) {
		this.updaterName = updaterName;
	}
	
}
