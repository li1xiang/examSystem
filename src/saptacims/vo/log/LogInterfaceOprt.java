package saptacims.vo.log;

import java.util.Date;

/**
 *
 * 
 *  T_EDI_LOG_INTERFACE_OPRT
 *
 * @mbg.generated 合并模式下此注解不可删除
 * @author Sawyer 
 * @company 上海亚太神通计算机有限公司Copyright (c)
 **/
public class LogInterfaceOprt {
    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_LOG_ID
     *
     * @mbg.generated
     **/
    private Long logId;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_INERFACE_NAME
     *
     * @mbg.generated
     **/
    private String inerfaceName;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_INVOKE_TIME
     *
     * @mbg.generated
     **/
    private Date invokeTime;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    private String oprtUserId;

    /**
     *
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_CREATE_DATE
     *
     * @mbg.generated
     **/
    private Date createDate;

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_LOG_ID
     *
     * @return  T_EDI_LOG_INTERFACE_OPRT.F_LOG_ID
     *
     * @mbg.generated
     **/
    public Long getLogId() {
        return logId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_LOG_ID
     *
     * @param logId   T_EDI_LOG_INTERFACE_OPRT.F_LOG_ID
     *
     * @mbg.generated
     **/
    public void setLogId(Long logId) {
        this.logId = logId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_INERFACE_NAME
     *
     * @return  T_EDI_LOG_INTERFACE_OPRT.F_INERFACE_NAME
     *
     * @mbg.generated
     **/
    public String getInerfaceName() {
        return inerfaceName;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_INERFACE_NAME
     *
     * @param inerfaceName   T_EDI_LOG_INTERFACE_OPRT.F_INERFACE_NAME
     *
     * @mbg.generated
     **/
    public void setInerfaceName(String inerfaceName) {
        this.inerfaceName = inerfaceName == null ? null : inerfaceName.trim();
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_INVOKE_TIME
     *
     * @return  T_EDI_LOG_INTERFACE_OPRT.F_INVOKE_TIME
     *
     * @mbg.generated
     **/
    public Date getInvokeTime() {
        return invokeTime;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_INVOKE_TIME
     *
     * @param invokeTime   T_EDI_LOG_INTERFACE_OPRT.F_INVOKE_TIME
     *
     * @mbg.generated
     **/
    public void setInvokeTime(Date invokeTime) {
        this.invokeTime = invokeTime;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_OPRT_USER_ID
     *
     * @return  T_EDI_LOG_INTERFACE_OPRT.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    public String getOprtUserId() {
        return oprtUserId;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_OPRT_USER_ID
     *
     * @param oprtUserId   T_EDI_LOG_INTERFACE_OPRT.F_OPRT_USER_ID
     *
     * @mbg.generated
     **/
    public void setOprtUserId(String oprtUserId) {
        this.oprtUserId = oprtUserId == null ? null : oprtUserId.trim();
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_CREATE_DATE
     *
     * @return  T_EDI_LOG_INTERFACE_OPRT.F_CREATE_DATE
     *
     * @mbg.generated
     **/
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 
     * 数据库字段: T_EDI_LOG_INTERFACE_OPRT.F_CREATE_DATE
     *
     * @param createDate   T_EDI_LOG_INTERFACE_OPRT.F_CREATE_DATE
     *
     * @mbg.generated
     **/
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}