package saptacims.service;

import java.util.List;
import java.util.Map;

import saptacims.vo.base.TbPaperVo;
import saptacims.vo.page.Pager;

public interface IMarkingService {

	/**
	 * 展示答卷列表
	 * @param page
	 * @param paper
	 * @param name
	 * @return
	 */
	public Map<String, Object> paperList(Pager page,
			TbPaperVo paper);
	
	/**
	 * 根据答卷Id查询答卷详细信息(题目,答案,参考答案,得分)
	 * @param paperId
	 * @return
	 */
	public List<Map<String, Object>> qryPaperDetails(int paperId);
}
