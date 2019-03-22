package saptacims.dao.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import saptacims.model.TbPaperDetail;
import saptacims.model.TbPaperDetailExample;

public interface TbPaperDetailMapper {
    long countByExample(TbPaperDetailExample example);

    int deleteByExample(TbPaperDetailExample example);

    int deleteByPrimaryKey(Integer paperDetailId);

    int insert(TbPaperDetail record);

    int insertSelective(TbPaperDetail record);

    List<TbPaperDetail> selectByExample(TbPaperDetailExample example);

    TbPaperDetail selectByPrimaryKey(Integer paperDetailId);

    int updateByExampleSelective(@Param("record") TbPaperDetail record, @Param("example") TbPaperDetailExample example);

    int updateByExample(@Param("record") TbPaperDetail record, @Param("example") TbPaperDetailExample example);

    int updateByPrimaryKeySelective(TbPaperDetail record);

    int updateByPrimaryKey(TbPaperDetail record);
    
    /**
     * 根据答卷id查询答卷信息
     * @param paperId
     * @return
     */
    List<TbPaperDetail> selectByPaperId(int paperId);
    
    /**
     * 根据paperId和question查询答题详情
     * @param paperId
     * @param questionId
     * @return
     */
    TbPaperDetail selectByPaperIdAndQuestionId(@Param("paperId")int paperId,@Param("questionId") int questionId);
}