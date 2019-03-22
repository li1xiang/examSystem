package saptacims.model;

import java.io.Serializable;
import java.util.Date;

public class TbAnswer implements Serializable{
    private Integer answerId;

    private Integer questionId;

    private String answer;

    private String answerImg;

    private String answerImgName;

    private Integer isright;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAnswerImg() {
        return answerImg;
    }

    public void setAnswerImg(String answerImg) {
        this.answerImg = answerImg == null ? null : answerImg.trim();
    }

    public String getAnswerImgName() {
        return answerImgName;
    }

    public void setAnswerImgName(String answerImgName) {
        this.answerImgName = answerImgName == null ? null : answerImgName.trim();
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