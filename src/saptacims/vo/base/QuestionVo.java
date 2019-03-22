package saptacims.vo.base;

import java.io.Serializable;
import java.util.List;

import saptacims.model.TbExaminationAnswer;
import saptacims.model.TbExaminationQuestion;
import saptacims.model.TbPaperDetail;

public class QuestionVo extends TbExaminationQuestion implements Serializable{
	private List<TbExaminationAnswer> options;//选项
	
	private TbPaperDetail paperDetail;
	
	public TbPaperDetail getPaperDetail() {
		return paperDetail;
	}
	public void setPaperDetail(TbPaperDetail paperDetail) {
		this.paperDetail = paperDetail;
	}
	public List<TbExaminationAnswer> getOptions() {
		return options;
	}
	public void setOptions(List<TbExaminationAnswer> options) {
		this.options = options;
	}
}
