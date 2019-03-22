package saptacims.model;

import java.io.Serializable;
import java.util.Date;

public class TbExaminationAnswer implements Serializable {
    private Integer examinationAnswerId;

    private Integer answerId;

    private Integer questionId;

    private String answerText;

    private Integer isright;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    public Integer getExaminationAnswerId() {
        return examinationAnswerId;
    }

    public void setExaminationAnswerId(Integer examinationAnswerId) {
        this.examinationAnswerId = examinationAnswerId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText == null ? null : answerText.trim();
    }

    public Integer getIsright() {
        return isright;
    }

    public void setIsright(Integer isright) {
        this.isright = isright;
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