package saptacims.model;

import java.io.Serializable;

public class TbQuestionView extends TbQuestion implements Serializable{
	
	private String createUserName;
	private String categoryName;
	private String groupName;
	private String updateUserName;
	private String createTimestr;
	private String answer;
	
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCreateTimestr() {
		return createTimestr;
	}
	public void setCreateTimestr(String createTimestr) {
		this.createTimestr = createTimestr;
	}
	public String getAnswer() {
	    return answer;
    }
	public void setAnswer(String answer) {
	    this.answer = answer;
    }
	

}
