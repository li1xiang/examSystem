package saptacims.model;

import java.io.Serializable;
import java.util.Date;

public class TbExaminationQuestion implements Serializable {
    private Integer examinationQuestionId;

    private Integer questionId;

    private Integer examinationId;

    private Integer categoryId;

    private Integer groupId;

    private Integer levels;

    private String questionTitle;

    private Integer questionType;

    private String questionImgPath;

    private String questionImgName;

    private Integer totalScore;

    private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    public Integer getExaminationQuestionId() {
        return examinationQuestionId;
    }

    public void setExaminationQuestionId(Integer examinationQuestionId) {
        this.examinationQuestionId = examinationQuestionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle == null ? null : questionTitle.trim();
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionImgPath() {
        return questionImgPath;
    }

    public void setQuestionImgPath(String questionImgPath) {
        this.questionImgPath = questionImgPath == null ? null : questionImgPath.trim();
    }

    public String getQuestionImgName() {
        return questionImgName;
    }

    public void setQuestionImgName(String questionImgName) {
        this.questionImgName = questionImgName == null ? null : questionImgName.trim();
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