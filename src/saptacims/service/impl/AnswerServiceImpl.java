package saptacims.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import saptacims.dao.base.TbAnswerMapper;
import saptacims.dao.base.TbQuestionMapper;
import saptacims.model.TbAnswer;
import saptacims.model.TbQuestion;
import saptacims.service.IAnswerService;
@Service
public class AnswerServiceImpl implements IAnswerService {

	private static Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);
	@Resource
	private TbAnswerMapper answerMapper;
	@Resource
	private TbQuestionMapper questionMapper;
	
	
	@Override
	public Boolean addAnswer(TbAnswer answer) {
		int num =  answerMapper.insertSelective(answer);
		if(num==0){
			return false;
		}else{
			return true;
		}
	}


	@Override
	public Boolean addAnswer(List<TbAnswer> answers) {
		//删除所有答案
		answerMapper.deleteAnswer(answers.get(0).getQuestionId());
		int all = 0;
		int success = 0;
		for (TbAnswer tbAnswer : answers) {
			all++;
			success+=answerMapper.insertSelective(tbAnswer);
		}
		if(all!=success){
			return false;
		}else{
			return true;
		}
	}


	@Override
	public TbAnswer getSubjectAnswer(Integer questionId) {		
		return answerMapper.getSubjectAnswer(questionId);
	}


	@Override
	public List<TbAnswer> answerList(Integer questionId) {
		// TODO Auto-generated method stub
		return answerMapper.answerList(questionId);
	}


	@Override
	public int deleteAnswer(Integer questionId) {
/*		//原题型为主观题,删除答案要注意删除附件
		if(oldQuestionType==0){
			TbAnswer answer = answerMapper.getSubjectAnswer(questionId);
			if(answer.getAnswerImg()!=null&&"".equals(answer.getAnswerImg().trim())){
				
			}
		}*/
		return answerMapper.deleteAnswer(questionId);
	}


	@Override
	public Boolean updateAnswer(TbAnswer tbAnswer) {
		// TODO Auto-generated method stub
		int num = answerMapper.updateByPrimaryKey(tbAnswer);
		if(num==1){
			return true;
		}else{
			return false;
		}
	}

}
