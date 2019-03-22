package saptacims.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbExaminationQuestionExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbExaminationQuestionExample() {
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

        public Criteria andExaminationQuestionIdIsNull() {
            addCriterion("EXAMINATION_QUESTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdIsNotNull() {
            addCriterion("EXAMINATION_QUESTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdEqualTo(Integer value) {
            addCriterion("EXAMINATION_QUESTION_ID =", value, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdNotEqualTo(Integer value) {
            addCriterion("EXAMINATION_QUESTION_ID <>", value, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdGreaterThan(Integer value) {
            addCriterion("EXAMINATION_QUESTION_ID >", value, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXAMINATION_QUESTION_ID >=", value, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdLessThan(Integer value) {
            addCriterion("EXAMINATION_QUESTION_ID <", value, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdLessThanOrEqualTo(Integer value) {
            addCriterion("EXAMINATION_QUESTION_ID <=", value, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdIn(List<Integer> values) {
            addCriterion("EXAMINATION_QUESTION_ID in", values, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdNotIn(List<Integer> values) {
            addCriterion("EXAMINATION_QUESTION_ID not in", values, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdBetween(Integer value1, Integer value2) {
            addCriterion("EXAMINATION_QUESTION_ID between", value1, value2, "examinationQuestionId");
            return (Criteria) this;
        }

        public Criteria andExaminationQuestionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EXAMINATION_QUESTION_ID not between", value1, value2, "examinationQuestionId");
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("CATEGORY_ID =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("CATEGORY_ID <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("CATEGORY_ID >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CATEGORY_ID >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("CATEGORY_ID <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("CATEGORY_ID <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("CATEGORY_ID in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("CATEGORY_ID not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("CATEGORY_ID between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CATEGORY_ID not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("GROUP_ID =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("GROUP_ID <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("GROUP_ID >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("GROUP_ID >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("GROUP_ID <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("GROUP_ID <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("GROUP_ID in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("GROUP_ID not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("GROUP_ID between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("GROUP_ID not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andLevelsIsNull() {
            addCriterion("LEVELS is null");
            return (Criteria) this;
        }

        public Criteria andLevelsIsNotNull() {
            addCriterion("LEVELS is not null");
            return (Criteria) this;
        }

        public Criteria andLevelsEqualTo(Integer value) {
            addCriterion("LEVELS =", value, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsNotEqualTo(Integer value) {
            addCriterion("LEVELS <>", value, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsGreaterThan(Integer value) {
            addCriterion("LEVELS >", value, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsGreaterThanOrEqualTo(Integer value) {
            addCriterion("LEVELS >=", value, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsLessThan(Integer value) {
            addCriterion("LEVELS <", value, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsLessThanOrEqualTo(Integer value) {
            addCriterion("LEVELS <=", value, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsIn(List<Integer> values) {
            addCriterion("LEVELS in", values, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsNotIn(List<Integer> values) {
            addCriterion("LEVELS not in", values, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsBetween(Integer value1, Integer value2) {
            addCriterion("LEVELS between", value1, value2, "levels");
            return (Criteria) this;
        }

        public Criteria andLevelsNotBetween(Integer value1, Integer value2) {
            addCriterion("LEVELS not between", value1, value2, "levels");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleIsNull() {
            addCriterion("QUESTION_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleIsNotNull() {
            addCriterion("QUESTION_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleEqualTo(String value) {
            addCriterion("QUESTION_TITLE =", value, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleNotEqualTo(String value) {
            addCriterion("QUESTION_TITLE <>", value, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleGreaterThan(String value) {
            addCriterion("QUESTION_TITLE >", value, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_TITLE >=", value, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleLessThan(String value) {
            addCriterion("QUESTION_TITLE <", value, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_TITLE <=", value, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleLike(String value) {
            addCriterion("QUESTION_TITLE like", value, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleNotLike(String value) {
            addCriterion("QUESTION_TITLE not like", value, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleIn(List<String> values) {
            addCriterion("QUESTION_TITLE in", values, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleNotIn(List<String> values) {
            addCriterion("QUESTION_TITLE not in", values, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleBetween(String value1, String value2) {
            addCriterion("QUESTION_TITLE between", value1, value2, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTitleNotBetween(String value1, String value2) {
            addCriterion("QUESTION_TITLE not between", value1, value2, "questionTitle");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNull() {
            addCriterion("QUESTION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIsNotNull() {
            addCriterion("QUESTION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeEqualTo(Integer value) {
            addCriterion("QUESTION_TYPE =", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotEqualTo(Integer value) {
            addCriterion("QUESTION_TYPE <>", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThan(Integer value) {
            addCriterion("QUESTION_TYPE >", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("QUESTION_TYPE >=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThan(Integer value) {
            addCriterion("QUESTION_TYPE <", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("QUESTION_TYPE <=", value, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeIn(List<Integer> values) {
            addCriterion("QUESTION_TYPE in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotIn(List<Integer> values) {
            addCriterion("QUESTION_TYPE not in", values, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeBetween(Integer value1, Integer value2) {
            addCriterion("QUESTION_TYPE between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("QUESTION_TYPE not between", value1, value2, "questionType");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathIsNull() {
            addCriterion("QUESTION_IMG_PATH is null");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathIsNotNull() {
            addCriterion("QUESTION_IMG_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathEqualTo(String value) {
            addCriterion("QUESTION_IMG_PATH =", value, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathNotEqualTo(String value) {
            addCriterion("QUESTION_IMG_PATH <>", value, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathGreaterThan(String value) {
            addCriterion("QUESTION_IMG_PATH >", value, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_IMG_PATH >=", value, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathLessThan(String value) {
            addCriterion("QUESTION_IMG_PATH <", value, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_IMG_PATH <=", value, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathLike(String value) {
            addCriterion("QUESTION_IMG_PATH like", value, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathNotLike(String value) {
            addCriterion("QUESTION_IMG_PATH not like", value, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathIn(List<String> values) {
            addCriterion("QUESTION_IMG_PATH in", values, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathNotIn(List<String> values) {
            addCriterion("QUESTION_IMG_PATH not in", values, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathBetween(String value1, String value2) {
            addCriterion("QUESTION_IMG_PATH between", value1, value2, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgPathNotBetween(String value1, String value2) {
            addCriterion("QUESTION_IMG_PATH not between", value1, value2, "questionImgPath");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameIsNull() {
            addCriterion("QUESTION_IMG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameIsNotNull() {
            addCriterion("QUESTION_IMG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameEqualTo(String value) {
            addCriterion("QUESTION_IMG_NAME =", value, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameNotEqualTo(String value) {
            addCriterion("QUESTION_IMG_NAME <>", value, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameGreaterThan(String value) {
            addCriterion("QUESTION_IMG_NAME >", value, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_IMG_NAME >=", value, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameLessThan(String value) {
            addCriterion("QUESTION_IMG_NAME <", value, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_IMG_NAME <=", value, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameLike(String value) {
            addCriterion("QUESTION_IMG_NAME like", value, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameNotLike(String value) {
            addCriterion("QUESTION_IMG_NAME not like", value, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameIn(List<String> values) {
            addCriterion("QUESTION_IMG_NAME in", values, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameNotIn(List<String> values) {
            addCriterion("QUESTION_IMG_NAME not in", values, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameBetween(String value1, String value2) {
            addCriterion("QUESTION_IMG_NAME between", value1, value2, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andQuestionImgNameNotBetween(String value1, String value2) {
            addCriterion("QUESTION_IMG_NAME not between", value1, value2, "questionImgName");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNull() {
            addCriterion("TOTAL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIsNotNull() {
            addCriterion("TOTAL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreEqualTo(Integer value) {
            addCriterion("TOTAL_SCORE =", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotEqualTo(Integer value) {
            addCriterion("TOTAL_SCORE <>", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThan(Integer value) {
            addCriterion("TOTAL_SCORE >", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("TOTAL_SCORE >=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThan(Integer value) {
            addCriterion("TOTAL_SCORE <", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreLessThanOrEqualTo(Integer value) {
            addCriterion("TOTAL_SCORE <=", value, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreIn(List<Integer> values) {
            addCriterion("TOTAL_SCORE in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotIn(List<Integer> values) {
            addCriterion("TOTAL_SCORE not in", values, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreBetween(Integer value1, Integer value2) {
            addCriterion("TOTAL_SCORE between", value1, value2, "totalScore");
            return (Criteria) this;
        }

        public Criteria andTotalScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("TOTAL_SCORE not between", value1, value2, "totalScore");
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