package saptacims.vo.log;

import java.util.Date;

/**
 *
 * 
 *  T_EDI_LOG_DATA_TRANS
 *
 * @mbg.generated 合并模式下此注解不可删除
 * @author Sawyer 
 * @company 上海亚太神通计算机有限公司Copyright (c)
 **/
public class LogDataTrans {
    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_LOG_ID
     *
     * @mbg.generated
     **/
    private Long logId;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_FUNC_NAME
     *
     * @mbg.generated
     **/
    private String funcName;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_SEND_TIME
     *
     * @mbg.generated
     **/
    private Date sendTime;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    private String oprtUserId;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_CREATE_DATE
     *
     * @mbg.generated
     **/
    private Date createDate;

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_LOG_ID
     *
     * @return  T_EDI_LOG_DATA_TRANS.F_LOG_ID
     *
     * @mbg.generated
     **/
    public Long getLogId() {
        return logId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_LOG_ID
     *
     * @param logId   T_EDI_LOG_DATA_TRANS.F_LOG_ID
     *
     * @mbg.generated
     **/
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_FUNC_NAME
     *
     * @return  T_EDI_LOG_DATA_TRANS.F_FUNC_NAME
     *
     * @mbg.generated
     **/
    public String getFuncName() {
        return funcName;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_FUNC_NAME
     *
     * @param funcName   T_EDI_LOG_DATA_TRANS.F_FUNC_NAME
     *
     * @mbg.generated
     **/
    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_SEND_TIME
     *
     * @return  T_EDI_LOG_DATA_TRANS.F_SEND_TIME
     *
     * @mbg.generated
     **/
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_SEND_TIME
     *
     * @param sendTime   T_EDI_LOG_DATA_TRANS.F_SEND_TIME
     *
     * @mbg.generated
     **/
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_OPRT_USER_ID
     *
     * @return  T_EDI_LOG_DATA_TRANS.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    public String getOprtUserId() {
        return oprtUserId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_OPRT_USER_ID
     *
     * @param oprtUserId   T_EDI_LOG_DATA_TRANS.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    public void setOprtUserId(String oprtUserId) {
        this.oprtUserId = oprtUserId == null ? null : oprtUserId.trim();
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_CREATE_DATE
     *
     * @return  T_EDI_LOG_DATA_TRANS.F_CREATE_DATE
     *
     * @mbg.generated
     **/
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_DATA_TRANS.F_CREATE_DATE
     *
     * @param createDate   T_EDI_LOG_DATA_TRANS.F_CREATE_DATE
     *
     * @mbg.generated
     **/
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}