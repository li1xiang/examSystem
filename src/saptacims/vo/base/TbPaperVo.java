package saptacims.vo.base;

import java.io.Serializable;

import saptacims.model.TbPaper;

public class TbPaperVo extends TbPaper implements Serializable{
	private String name;
	private String phone;
	private String examinationName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}
}
