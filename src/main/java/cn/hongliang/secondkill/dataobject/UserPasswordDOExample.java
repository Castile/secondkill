package cn.hongliang.secondkill.dataobject;

import java.util.ArrayList;
import java.util.List;

public class UserPasswordDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserPasswordDOExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordIsNull() {
            addCriterion("encript_password is null");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordIsNotNull() {
            addCriterion("encript_password is not null");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordEqualTo(String value) {
            addCriterion("encript_password =", value, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordNotEqualTo(String value) {
            addCriterion("encript_password <>", value, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordGreaterThan(String value) {
            addCriterion("encript_password >", value, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("encript_password >=", value, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordLessThan(String value) {
            addCriterion("encript_password <", value, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordLessThanOrEqualTo(String value) {
            addCriterion("encript_password <=", value, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordLike(String value) {
            addCriterion("encript_password like", value, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordNotLike(String value) {
            addCriterion("encript_password not like", value, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordIn(List<String> values) {
            addCriterion("encript_password in", values, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordNotIn(List<String> values) {
            addCriterion("encript_password not in", values, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordBetween(String value1, String value2) {
            addCriterion("encript_password between", value1, value2, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andEncriptPasswordNotBetween(String value1, String value2) {
            addCriterion("encript_password not between", value1, value2, "encriptPassword");
            return (Criteria) this;
        }

        public Criteria andUserUdIsNull() {
            addCriterion("user_ud is null");
            return (Criteria) this;
        }

        public Criteria andUserUdIsNotNull() {
            addCriterion("user_ud is not null");
            return (Criteria) this;
        }

        public Criteria andUserUdEqualTo(Integer value) {
            addCriterion("user_ud =", value, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdNotEqualTo(Integer value) {
            addCriterion("user_ud <>", value, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdGreaterThan(Integer value) {
            addCriterion("user_ud >", value, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_ud >=", value, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdLessThan(Integer value) {
            addCriterion("user_ud <", value, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdLessThanOrEqualTo(Integer value) {
            addCriterion("user_ud <=", value, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdIn(List<Integer> values) {
            addCriterion("user_ud in", values, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdNotIn(List<Integer> values) {
            addCriterion("user_ud not in", values, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdBetween(Integer value1, Integer value2) {
            addCriterion("user_ud between", value1, value2, "userUd");
            return (Criteria) this;
        }

        public Criteria andUserUdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_ud not between", value1, value2, "userUd");
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