package saptacims.model;

import java.io.Serializable;
import java.util.Date;

public class TbPaperDetail implements Serializable{
    private Integer paperDetailId;

    private Integer paperId;

    private Integer questionId;

    private Integer answerId;

    private String subjectiveAnswer;

    private Integer score;

    private Integer createUser;

    private Date createTime;

    public Integer getPaperDetailId() {
        return paperDetailId;
    }

    public void setPaperDetailId(Integer paperDetailId) {
        this.paperDetailId = paperDetailId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getSubjectiveAnswer() {
        return subjectiveAnswer;
    }

    public void setSubjectiveAnswer(String subjectiveAnswer) {
        this.subjectiveAnswer = subjectiveAnswer == null ? null : subjectiveAnswer.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
}