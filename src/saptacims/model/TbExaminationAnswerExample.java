package saptacims.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbExaminationAnswerExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbExaminationAnswerExample() {
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

        public Criteria andExaminationAnswerIdIsNull() {
            addCriterion("EXAMINATION_ANSWER_ID is null");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdIsNotNull() {
            addCriterion("EXAMINATION_ANSWER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdEqualTo(Integer value) {
            addCriterion("EXAMINATION_ANSWER_ID =", value, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdNotEqualTo(Integer value) {
            addCriterion("EXAMINATION_ANSWER_ID <>", value, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdGreaterThan(Integer value) {
            addCriterion("EXAMINATION_ANSWER_ID >", value, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXAMINATION_ANSWER_ID >=", value, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdLessThan(Integer value) {
            addCriterion("EXAMINATION_ANSWER_ID <", value, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdLessThanOrEqualTo(Integer value) {
            addCriterion("EXAMINATION_ANSWER_ID <=", value, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdIn(List<Integer> values) {
            addCriterion("EXAMINATION_ANSWER_ID in", values, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdNotIn(List<Integer> values) {
            addCriterion("EXAMINATION_ANSWER_ID not in", values, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdBetween(Integer value1, Integer value2) {
            addCriterion("EXAMINATION_ANSWER_ID between", value1, value2, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andExaminationAnswerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EXAMINATION_ANSWER_ID not between", value1, value2, "examinationAnswerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIsNull() {
            addCriterion("ANSWER_ID is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIsNotNull() {
            addCriterion("ANSWER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerIdEqualTo(Integer value) {
            addCriterion("ANSWER_ID =", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotEqualTo(Integer value) {
            addCriterion("ANSWER_ID <>", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdGreaterThan(Integer value) {
            addCriterion("ANSWER_ID >", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ANSWER_ID >=", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdLessThan(Integer value) {
            addCriterion("ANSWER_ID <", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdLessThanOrEqualTo(Integer value) {
            addCriterion("ANSWER_ID <=", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIn(List<Integer> values) {
            addCriterion("ANSWER_ID in", values, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotIn(List<Integer> values) {
            addCriterion("ANSWER_ID not in", values, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdBetween(Integer value1, Integer value2) {
            addCriterion("ANSWER_ID between", value1, value2, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ANSWER_ID not between", value1, value2, "answerId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNull() {
            addCriterion("QUESTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("QUESTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(Integer value) {
            addCriterion("QUESTION_ID =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(Integer value) {
            addCriterion("QUESTION_ID <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(Integer value) {
            addCriterion("QUESTION_ID >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("QUESTION_ID >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(Integer value) {
            addCriterion("QUESTION_ID <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(Integer value) {
            addCriterion("QUESTION_ID <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<Integer> values) {
            addCriterion("QUESTION_ID in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<Integer> values) {
            addCriterion("QUESTION_ID not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(Integer value1, Integer value2) {
            addCriterion("QUESTION_ID between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("QUESTION_ID not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andAnswerTextIsNull() {
            addCriterion("ANSWER_TEXT is null");
            return (Criteria) this;
        }

        public Criteria andAnswerTextIsNotNull() {
            addCriterion("ANSWER_TEXT is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerTextEqualTo(String value) {
            addCriterion("ANSWER_TEXT =", value, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextNotEqualTo(String value) {
            addCriterion("ANSWER_TEXT <>", value, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextGreaterThan(String value) {
            addCriterion("ANSWER_TEXT >", value, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextGreaterThanOrEqualTo(String value) {
            addCriterion("ANSWER_TEXT >=", value, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextLessThan(String value) {
            addCriterion("ANSWER_TEXT <", value, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextLessThanOrEqualTo(String value) {
            addCriterion("ANSWER_TEXT <=", value, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextLike(String value) {
            addCriterion("ANSWER_TEXT like", value, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextNotLike(String value) {
            addCriterion("ANSWER_TEXT not like", value, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextIn(List<String> values) {
            addCriterion("ANSWER_TEXT in", values, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextNotIn(List<String> values) {
            addCriterion("ANSWER_TEXT not in", values, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextBetween(String value1, String value2) {
            addCriterion("ANSWER_TEXT between", value1, value2, "answerText");
            return (Criteria) this;
        }

        public Criteria andAnswerTextNotBetween(String value1, String value2) {
            addCriterion("ANSWER_TEXT not between", value1, value2, "answerText");
            return (Criteria) this;
        }

        public Criteria andIsrightIsNull() {
            addCriterion("ISRIGHT is null");
            return (Criteria) this;
        }

        public Criteria andIsrightIsNotNull() {
            addCriterion("ISRIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andIsrightEqualTo(Integer value) {
            addCriterion("ISRIGHT =", value, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightNotEqualTo(Integer value) {
            addCriterion("ISRIGHT <>", value, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightGreaterThan(Integer value) {
            addCriterion("ISRIGHT >", value, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightGreaterThanOrEqualTo(Integer value) {
            addCriterion("ISRIGHT >=", value, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightLessThan(Integer value) {
            addCriterion("ISRIGHT <", value, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightLessThanOrEqualTo(Integer value) {
            addCriterion("ISRIGHT <=", value, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightIn(List<Integer> values) {
            addCriterion("ISRIGHT in", values, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightNotIn(List<Integer> values) {
            addCriterion("ISRIGHT not in", values, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightBetween(Integer value1, Integer value2) {
            addCriterion("ISRIGHT between", value1, value2, "isright");
            return (Criteria) this;
        }

        public Criteria andIsrightNotBetween(Integer value1, Integer value2) {
            addCriterion("ISRIGHT not between", value1, value2, "isright");
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