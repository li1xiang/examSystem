package saptacims.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TbRole implements Serializable {
    private Integer roleId;

    private String roleName;

    private Date createTime;

    private Integer createUser;

    private Date updateTime;

    private Integer updateUser;
    
    private List<TbUserRole> roleUsers;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

	public List<TbUserRole> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(List<TbUserRole> roleUsers) {
		this.roleUsers = roleUsers;
	}
    
}