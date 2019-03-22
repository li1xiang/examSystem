package saptacims.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import saptacims.model.TbExamination;
import saptacims.model.TbExaminationExample;
import saptacims.model.TbInterviewer;
import saptacims.vo.base.TbExaminationVo;

public interface TbExaminationMapper {
	
    int countByExample(TbExaminationExample example);

    int deleteByExample(TbExaminationExample example);

    int deleteByPrimaryKey(Integer examinationId);

    int insert(TbExamination record);

    int insertSelective(TbExamination record);

    List<TbExamination> selectByExample(TbExaminationExample example);

    TbExamination selectByPrimaryKey(Integer examinationId);

    int updateByExampleSelective(@Param("record") TbExamination record, @Param("example") TbExaminationExample example);

    int updateByExample(@Param("record") TbExamination record, @Param("example") TbExaminationExample example);

    int updateByPrimaryKeySelective(TbExamination record);

    int updateByPrimaryKey(TbExamination record);
    
    /**
	 * 查询所有试卷
	 * @return
	 */
	public List<TbExamination> selectAll();
	

    /**
     * 根据面试者查询应该面试者关联的试卷
     * @param interviewer
     * @return
     */
    public List<TbExaminationVo> selectExaminationByInterviewer(Map<String, Object> params);
    
    /**
     * 根据面试者查询应该面试者关联的试卷数量
     * @param interviewer
     * @return
     */
    public int getExaminationByInterviewerCount(TbInterviewer interviewer);
    
    /**
     * 分页查询 
     * @param params
     * @return
     */
    public List<TbExamination> getPageList(Map<String, Object> params);
    
    /**
     * 查询符合条件的记录数量
     * @param qryCondition
     * @return
     */
    int getCount(TbInterviewer qryCondition);

    /**
     * 分页查询:数量
     * @param map
     * @return
     */
	int getCount(Map<String, Object> map);

	/**
	 * 修改状态
	 * @param result
	 * @return
	 */
	int batchUpdateStatus(Map<String, Object> result);
}