package saptacims.exception;

public class error {
	/**
	 * 中文：业务处理成功
	 */
	public final static int SUCCESS_OK =  0;// 中文：业务处理成功
	
	// 技术上报错信息，使用编号为RSP000100以上
	public final static int UNKNOWN_ERROR =  101;   //未知错误
	 
	/**
	 * 登录异常
	 */
	public final static int LOGINUSERID_ISEMPTY = 10008;//用户名不能为空，请输入用户名
	public final static int LOGINPWD_ISEMPTY = 10009;//密码不能为空，请输入密码；
	public final static int LOGINUSERID_PWD = 10010;//用户名或密码不正确，请重新输入；
	public final static int LOGINCODE_ISEMPTY = 10011;//验证码不能为空，请输入验证码；
	public final static int LOGINCODE = 10012;//验证码不正确，请重新输入。
	public final static int NOPRVL = 10013;//验证码不正确，请重新输入。
	public final static int USER_NOT_EXIT = 10416;//用户不存在
	public final static int USER_STATUS = 10417;//用户无效
	
}
