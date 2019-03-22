package saptacims.vo.log;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 
 *  T_EDI_LOG_BIZ_OPRT
 *
 * @mbg.generated 合并模式下此注解不可删除
 * @author Sawyer 
 * @company 上海亚太神通计算机有限公司Copyright (c)
 **/
public class LogBizOprt implements Serializable{
    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_LOG_ID
     *
     * @mbg.generated
     **/
    private Long logId;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_FUNC_NAME
     *
     * @mbg.generated
     **/
    private String funcName;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_TIME
     *
     * @mbg.generated
     **/
    private Date oprtTime;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_CONTENT
     *
     * @mbg.generated
     **/
    private String oprtContent;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    private String oprtUserId;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_CHECK_USER_ID
     *
     * @mbg.generated
     **/
    private String checkUserId;

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_LOG_ID
     *
     * @return  T_EDI_LOG_BIZ_OPRT.F_LOG_ID
     *
     * @mbg.generated
     **/
    public Long getLogId() {
        return logId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_LOG_ID
     *
     * @param logId   T_EDI_LOG_BIZ_OPRT.F_LOG_ID
     *
     * @mbg.generated
     **/
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_FUNC_NAME
     *
     * @return  T_EDI_LOG_BIZ_OPRT.F_FUNC_NAME
     *
     * @mbg.generated
     **/
    public String getFuncName() {
        return funcName;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_FUNC_NAME
     *
     * @param funcName   T_EDI_LOG_BIZ_OPRT.F_FUNC_NAME
     *
     * @mbg.generated
     **/
    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_TIME
     *
     * @return  T_EDI_LOG_BIZ_OPRT.F_OPRT_TIME
     *
     * @mbg.generated
     **/
    public Date getOprtTime() {
        return oprtTime;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_TIME
     *
     * @param oprtTime   T_EDI_LOG_BIZ_OPRT.F_OPRT_TIME
     *
     * @mbg.generated
     **/
    public void setOprtTime(Date oprtTime) {
        this.oprtTime = oprtTime;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_CONTENT
     *
     * @return  T_EDI_LOG_BIZ_OPRT.F_OPRT_CONTENT
     *
     * @mbg.generated
     **/
    public String getOprtContent() {
        return oprtContent;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_CONTENT
     *
     * @param oprtContent   T_EDI_LOG_BIZ_OPRT.F_OPRT_CONTENT
     *
     * @mbg.generated
     **/
    public void setOprtContent(String oprtContent) {
        this.oprtContent = oprtContent == null ? null : oprtContent.trim();
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_USER_ID
     *
     * @return  T_EDI_LOG_BIZ_OPRT.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    public String getOprtUserId() {
        return oprtUserId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_OPRT_USER_ID
     *
     * @param oprtUserId   T_EDI_LOG_BIZ_OPRT.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    public void setOprtUserId(String oprtUserId) {
        this.oprtUserId = oprtUserId == null ? null : oprtUserId.trim();
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_CHECK_USER_ID
     *
     * @return  T_EDI_LOG_BIZ_OPRT.F_CHECK_USER_ID
     *
     * @mbg.generated
     **/
    public String getCheckUserId() {
        return checkUserId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_BIZ_OPRT.F_CHECK_USER_ID
     *
     * @param checkUserId   T_EDI_LOG_BIZ_OPRT.F_CHECK_USER_ID
     *
     * @mbg.generated
     **/
    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId == null ? null : checkUserId.trim();
    }
}