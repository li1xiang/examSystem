package saptacims.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbPaperExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbPaperExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andPaperIdIsNull() {
            addCriterion("PAPER_ID is null");
            return (Criteria) this;
        }

        public Criteria andPaperIdIsNotNull() {
            addCriterion("PAPER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPaperIdEqualTo(Integer value) {
            addCriterion("PAPER_ID =", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotEqualTo(Integer value) {
            addCriterion("PAPER_ID <>", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThan(Integer value) {
            addCriterion("PAPER_ID >", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAPER_ID >=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThan(Integer value) {
            addCriterion("PAPER_ID <", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThanOrEqualTo(Integer value) {
            addCriterion("PAPER_ID <=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdIn(List<Integer> values) {
            addCriterion("PAPER_ID in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotIn(List<Integer> values) {
            addCriterion("PAPER_ID not in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdBetween(Integer value1, Integer value2) {
            addCriterion("PAPER_ID between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PAPER_ID not between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdIsNull() {
            addCriterion("EXAMINATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andExaminationIdIsNotNull() {
            addCriterion("EXAMINATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExaminationIdEqualTo(Integer value) {
            addCriterion("EXAMINATION_ID =", value, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdNotEqualTo(Integer value) {
            addCriterion("EXAMINATION_ID <>", value, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdGreaterThan(Integer value) {
            addCriterion("EXAMINATION_ID >", value, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXAMINATION_ID >=", value, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdLessThan(Integer value) {
            addCriterion("EXAMINATION_ID <", value, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdLessThanOrEqualTo(Integer value) {
            addCriterion("EXAMINATION_ID <=", value, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdIn(List<Integer> values) {
            addCriterion("EXAMINATION_ID in", values, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdNotIn(List<Integer> values) {
            addCriterion("EXAMINATION_ID not in", values, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdBetween(Integer value1, Integer value2) {
            addCriterion("EXAMINATION_ID between", value1, value2, "examinationId");
            return (Criteria) this;
        }

        public Criteria andExaminationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EXAMINATION_ID not between", value1, value2, "examinationId");
            return (Criteria) this;
        }

        public Criteria andPaperUserIsNull() {
            addCriterion("PAPER_USER is null");
            return (Criteria) this;
        }

        public Criteria andPaperUserIsNotNull() {
            addCriterion("PAPER_USER is not null");
            return (Criteria) this;
        }

        public Criteria andPaperUserEqualTo(Integer value) {
            addCriterion("PAPER_USER =", value, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserNotEqualTo(Integer value) {
            addCriterion("PAPER_USER <>", value, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserGreaterThan(Integer value) {
            addCriterion("PAPER_USER >", value, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAPER_USER >=", value, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserLessThan(Integer value) {
            addCriterion("PAPER_USER <", value, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserLessThanOrEqualTo(Integer value) {
            addCriterion("PAPER_USER <=", value, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserIn(List<Integer> values) {
            addCriterion("PAPER_USER in", values, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserNotIn(List<Integer> values) {
            addCriterion("PAPER_USER not in", values, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserBetween(Integer value1, Integer value2) {
            addCriterion("PAPER_USER between", value1, value2, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperUserNotBetween(Integer value1, Integer value2) {
            addCriterion("PAPER_USER not between", value1, value2, "paperUser");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeIsNull() {
            addCriterion("PAPER_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeIsNotNull() {
            addCriterion("PAPER_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeEqualTo(Date value) {
            addCriterion("PAPER_START_TIME =", value, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeNotEqualTo(Date value) {
            addCriterion("PAPER_START_TIME <>", value, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeGreaterThan(Date value) {
            addCriterion("PAPER_START_TIME >", value, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PAPER_START_TIME >=", value, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeLessThan(Date value) {
            addCriterion("PAPER_START_TIME <", value, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("PAPER_START_TIME <=", value, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeIn(List<Date> values) {
            addCriterion("PAPER_START_TIME in", values, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeNotIn(List<Date> values) {
            addCriterion("PAPER_START_TIME not in", values, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeBetween(Date value1, Date value2) {
            addCriterion("PAPER_START_TIME between", value1, value2, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("PAPER_START_TIME not between", value1, value2, "paperStartTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeIsNull() {
            addCriterion("PAPER_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeIsNotNull() {
            addCriterion("PAPER_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeEqualTo(Date value) {
            addCriterion("PAPER_END_TIME =", value, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeNotEqualTo(Date value) {
            addCriterion("PAPER_END_TIME <>", value, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeGreaterThan(Date value) {
            addCriterion("PAPER_END_TIME >", value, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PAPER_END_TIME >=", value, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeLessThan(Date value) {
            addCriterion("PAPER_END_TIME <", value, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("PAPER_END_TIME <=", value, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeIn(List<Date> values) {
            addCriterion("PAPER_END_TIME in", values, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeNotIn(List<Date> values) {
            addCriterion("PAPER_END_TIME not in", values, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeBetween(Date value1, Date value2) {
            addCriterion("PAPER_END_TIME between", value1, value2, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andPaperEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("PAPER_END_TIME not between", value1, value2, "paperEndTime");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("SCORE not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksIsNull() {
            addCriterion("SCORE_REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksIsNotNull() {
            addCriterion("SCORE_REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksEqualTo(String value) {
            addCriterion("SCORE_REMARKS =", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksNotEqualTo(String value) {
            addCriterion("SCORE_REMARKS <>", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksGreaterThan(String value) {
            addCriterion("SCORE_REMARKS >", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_REMARKS >=", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksLessThan(String value) {
            addCriterion("SCORE_REMARKS <", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksLessThanOrEqualTo(String value) {
            addCriterion("SCORE_REMARKS <=", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksLike(String value) {
            addCriterion("SCORE_REMARKS like", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksNotLike(String value) {
            addCriterion("SCORE_REMARKS not like", value, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksIn(List<String> values) {
            addCriterion("SCORE_REMARKS in", values, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksNotIn(List<String> values) {
            addCriterion("SCORE_REMARKS not in", values, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksBetween(String value1, String value2) {
            addCriterion("SCORE_REMARKS between", value1, value2, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andScoreRemarksNotBetween(String value1, String value2) {
            addCriterion("SCORE_REMARKS not between", value1, value2, "scoreRemarks");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(Integer value) {
            addCriterion("CREATE_USER =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(Integer value) {
            addCriterion("CREATE_USER <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(Integer value) {
            addCriterion("CREATE_USER >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("CREATE_USER >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(Integer value) {
            addCriterion("CREATE_USER <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(Integer value) {
            addCriterion("CREATE_USER <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<Integer> values) {
            addCriterion("CREATE_USER in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<Integer> values) {
            addCriterion("CREATE_USER not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(Integer value1, Integer value2) {
            addCriterion("CREATE_USER between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(Integer value1, Integer value2) {
            addCriterion("CREATE_USER not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("UPDATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("UPDATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(Integer value) {
            addCriterion("UPDATE_USER =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(Integer value) {
            addCriterion("UPDATE_USER <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(Integer value) {
            addCriterion("UPDATE_USER >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("UPDATE_USER >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(Integer value) {
            addCriterion("UPDATE_USER <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(Integer value) {
            addCriterion("UPDATE_USER <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<Integer> values) {
            addCriterion("UPDATE_USER in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<Integer> values) {
            addCriterion("UPDATE_USER not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(Integer value1, Integer value2) {
            addCriterion("UPDATE_USER between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(Integer value1, Integer value2) {
            addCriterion("UPDATE_USER not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}