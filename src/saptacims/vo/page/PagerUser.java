package saptacims.vo.page;

public class PagerUser extends Pager {
	
	private String queryUserId;//查询用户时，传入的用户编号
	private String queryDeptId;//查询用户时，传入的部门编号
	private String queryUserTypeId;//查询用户时，传入的类型编号
	private String queryUserStatus;//查询用户时，传入的用户状态
	private String queryUserTypeIdLogin;//登录后的用户类型
	private String loginUserId;//登录用户的代码
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getQueryUserTypeIdLogin() {
		return queryUserTypeIdLogin;
	}
	public void setQueryUserTypeIdLogin(String queryUserTypeIdLogin) {
		if(queryUserTypeIdLogin.length()>0){
			this.queryUserTypeIdLogin = queryUserTypeIdLogin;
		}else{
			this.queryUserStatus=null;
		}
	}
	public String getQueryUserStatus() {
		return queryUserStatus;
	}
	public void setQueryUserStatus(String queryUserStatus) {
		if(queryUserStatus.length()>0){
			this.queryUserStatus = queryUserStatus;
		}else{
			this.queryUserStatus=null;
		}
	}
	public String getQueryUserTypeId() {
		return queryUserTypeId;
	}
	public void setQueryUserTypeId(String queryUserTypeId) {
		if(queryUserTypeId.length()>0){
			this.queryUserTypeId = queryUserTypeId;
		}else{
			this.queryUserTypeId=null;
		}
	}
	public String getQueryUserId() {
		return queryUserId;
	}
	public void setQueryUserId(String queryUserId) {
		if(queryUserId.length()>0){
			this.queryUserId = queryUserId;
		}else{
			this.queryUserId=null;
		}
	}
	public String getQueryDeptId() {
		return queryDeptId;
	}
	public void setQueryDeptId(String queryDeptId) {
		if(queryDeptId.length()>0){
			this.queryDeptId = queryDeptId;
		}else{
			this.queryDeptId=null;
		}
	}

}
