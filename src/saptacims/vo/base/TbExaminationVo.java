package saptacims.vo.base;

import java.io.Serializable;

import saptacims.model.TbExamination;

public class TbExaminationVo extends TbExamination implements Serializable{
	private String createUserCname;

	public String getCreateUserCname() {
		return createUserCname;
	}

	public void setCreateUserCname(String createUserCname) {
		this.createUserCname = createUserCname;
	}
}