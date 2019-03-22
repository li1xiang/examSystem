package saptacims.model;

import java.io.Serializable;
import java.util.Date;

public class TbExamination implements Serializable {
    private Integer examinationId;

    private String examinationName;

    private String examinationRemark;

    private Integer examinationStatus;

    private Integer totalScore;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public String getExaminationName() {
        return examinationName;
    }

    public void setExaminationName(String examinationName) {
        this.examinationName = examinationName == null ? null : examinationName.trim();
    }

    public String getExaminationRemark() {
        return examinationRemark;
    }

    public void setExaminationRemark(String examinationRemark) {
        this.examinationRemark = examinationRemark == null ? null : examinationRemark.trim();
    }

    public Integer getExaminationStatus() {
        return examinationStatus;
    }

    public void setExaminationStatus(Integer examinationStatus) {
        this.examinationStatus = examinationStatus;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}