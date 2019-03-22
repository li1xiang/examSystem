package saptacims.vo.page;

import saptacims.model.TbUser;

import java.io.Serializable;

public class TbUserVo   implements Serializable{

    private  String roleName;

    private TbUser tbUser;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public TbUser getTbUser() {
        return tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }
}
