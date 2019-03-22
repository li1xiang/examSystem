package saptacims.dao.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import saptacims.model.TbPaper;
import saptacims.model.TbPaperExample;
import saptacims.vo.base.TbPaperVo;

public interface TbPaperMapper {
    long countByExample(TbPaperExample example);

    int deleteByExample(TbPaperExample example);

    int deleteByPrimaryKey(Integer paperId);

    int insert(TbPaper record);

    int insertSelective(TbPaper record);

    List<TbPaper> selectByExample(TbPaperExample example);

    TbPaper selectByPrimaryKey(Integer paperId);

    int updateByExampleSelective(@Param("record") TbPaper record, @Param("example") TbPaperExample example);

    int updateByExample(@Param("record") TbPaper record, @Param("example") TbPaperExample example);

    int updateByPrimaryKeySelective(TbPaper record);

    int updateByPrimaryKey(TbPaper record);
    
    /**
     * 插入记录 并填入Key
     * @param record
     * @return
     */
    int insertReturnKey(TbPaper record);
    
    /**
     * 根据答卷人和试卷编号查询
     * @param qryCondition
     * @return
     */
    TbPaper selectByExaminationIdAndPaperUser(TbPaper qryCondition);
    
    /**
     * 查询答卷列表
     * @param params
     * @return
     */
    List<TbPaperVo> getPaperList(Map<String, Object> params);
    
    /**
     * 查询答卷列表数量
     * @param params
     * @return
     */
    int getPaperListCount(Map<String,Object> params);
    
    /**
     * 根据面试者查询答卷
     * @param tbPaper
     * @return
     */
    List<TbPaper> selectByPaperUser(TbPaper tbPaper);
    
}