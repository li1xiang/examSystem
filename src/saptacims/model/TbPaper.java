package saptacims.model;

import java.io.Serializable;
import java.util.Date;

public class TbPaper implements Serializable{
    private Integer paperId;

    private Integer examinationId;

    private Integer paperUser;

    private Date paperStartTime;

    private Date paperEndTime;

    private Integer score;

    private String scoreRemarks;
    
    private Integer markingMan;
    
    private Integer submitStatus;

	private Integer createUser;

    private Date createTime;

    private Integer updateUser;

    private Date updateTime;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getMarkingMan() {
		return markingMan;
	}

	public void setMarkingMan(Integer markingMan) {
		this.markingMan = markingMan;
	}

	public Integer getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(Integer examinationId) {
        this.examinationId = examinationId;
    }

    public Integer getPaperUser() {
        return paperUser;
    }

    public void setPaperUser(Integer paperUser) {
        this.paperUser = paperUser;
    }

    public Date getPaperStartTime() {
        return paperStartTime;
    }

    public void setPaperStartTime(Date paperStartTime) {
        this.paperStartTime = paperStartTime;
    }

    public Date getPaperEndTime() {
        return paperEndTime;
    }

    public void setPaperEndTime(Date paperEndTime) {
        this.paperEndTime = paperEndTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getScoreRemarks() {
        return scoreRemarks;
    }

    public void setScoreRemarks(String scoreRemarks) {
        this.scoreRemarks = scoreRemarks == null ? null : scoreRemarks.trim();
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

    public Integer getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(Integer submitStatus) {
		this.submitStatus = submitStatus;
	}
}