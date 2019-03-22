package saptacims.vo.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import saptacims.model.TbPaper;

public class PaperVo extends TbPaper implements Serializable{
	private String paperUserName;//答卷人名字
	private String examinationName;//试卷名称
	private Date examinationUpdateDate;//试卷创建日期



	public Date getExaminationUpdateDate() {
		return examinationUpdateDate;
	}

	public void setExaminationUpdateDate(Date examinationUpdateDate) {
		this.examinationUpdateDate = examinationUpdateDate;
	}

	private List<QuestionVo> questionList;
	
	public String getExaminationName() {
		return examinationName;
	}
	
	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public List<QuestionVo> getQuestionList() {
		return questionList;
	}

	public String getPaperUserName() {
		return paperUserName;
	}

	public void setPaperUserName(String paperUserName) {
		this.paperUserName = paperUserName;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}
}
