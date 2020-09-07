package com.pl.airboss.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecLoginOnlineBeanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SecLoginOnlineBeanExample() {
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

        public Criteria andSessionIdIsNull() {
            addCriterion("SESSION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSessionIdIsNotNull() {
            addCriterion("SESSION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSessionIdEqualTo(String value) {
            addCriterion("SESSION_ID =", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotEqualTo(String value) {
            addCriterion("SESSION_ID <>", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThan(String value) {
            addCriterion("SESSION_ID >", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_ID >=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThan(String value) {
            addCriterion("SESSION_ID <", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThanOrEqualTo(String value) {
            addCriterion("SESSION_ID <=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLike(String value) {
            addCriterion("SESSION_ID like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotLike(String value) {
            addCriterion("SESSION_ID not like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdIn(List<String> values) {
            addCriterion("SESSION_ID in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotIn(List<String> values) {
            addCriterion("SESSION_ID not in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdBetween(String value1, String value2) {
            addCriterion("SESSION_ID between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotBetween(String value1, String value2) {
            addCriterion("SESSION_ID not between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andLoginCodeIsNull() {
            addCriterion("LOGIN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andLoginCodeIsNotNull() {
            addCriterion("LOGIN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andLoginCodeEqualTo(String value) {
            addCriterion("LOGIN_CODE =", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeNotEqualTo(String value) {
            addCriterion("LOGIN_CODE <>", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeGreaterThan(String value) {
            addCriterion("LOGIN_CODE >", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_CODE >=", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeLessThan(String value) {
            addCriterion("LOGIN_CODE <", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_CODE <=", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeLike(String value) {
            addCriterion("LOGIN_CODE like", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeNotLike(String value) {
            addCriterion("LOGIN_CODE not like", value, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeIn(List<String> values) {
            addCriterion("LOGIN_CODE in", values, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeNotIn(List<String> values) {
            addCriterion("LOGIN_CODE not in", values, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeBetween(String value1, String value2) {
            addCriterion("LOGIN_CODE between", value1, value2, "loginCode");
            return (Criteria) this;
        }

        public Criteria andLoginCodeNotBetween(String value1, String value2) {
            addCriterion("LOGIN_CODE not between", value1, value2, "loginCode");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNull() {
            addCriterion("IP_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andIpAddressIsNotNull() {
            addCriterion("IP_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andIpAddressEqualTo(String value) {
            addCriterion("IP_ADDRESS =", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotEqualTo(String value) {
            addCriterion("IP_ADDRESS <>", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThan(String value) {
            addCriterion("IP_ADDRESS >", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressGreaterThanOrEqualTo(String value) {
            addCriterion("IP_ADDRESS >=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThan(String value) {
            addCriterion("IP_ADDRESS <", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLessThanOrEqualTo(String value) {
            addCriterion("IP_ADDRESS <=", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressLike(String value) {
            addCriterion("IP_ADDRESS like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotLike(String value) {
            addCriterion("IP_ADDRESS not like", value, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressIn(List<String> values) {
            addCriterion("IP_ADDRESS in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotIn(List<String> values) {
            addCriterion("IP_ADDRESS not in", values, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressBetween(String value1, String value2) {
            addCriterion("IP_ADDRESS between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andIpAddressNotBetween(String value1, String value2) {
            addCriterion("IP_ADDRESS not between", value1, value2, "ipAddress");
            return (Criteria) this;
        }

        public Criteria andLoginLocationIsNull() {
            addCriterion("LOGIN_LOCATION is null");
            return (Criteria) this;
        }

        public Criteria andLoginLocationIsNotNull() {
            addCriterion("LOGIN_LOCATION is not null");
            return (Criteria) this;
        }

        public Criteria andLoginLocationEqualTo(String value) {
            addCriterion("LOGIN_LOCATION =", value, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationNotEqualTo(String value) {
            addCriterion("LOGIN_LOCATION <>", value, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationGreaterThan(String value) {
            addCriterion("LOGIN_LOCATION >", value, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_LOCATION >=", value, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationLessThan(String value) {
            addCriterion("LOGIN_LOCATION <", value, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_LOCATION <=", value, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationLike(String value) {
            addCriterion("LOGIN_LOCATION like", value, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationNotLike(String value) {
            addCriterion("LOGIN_LOCATION not like", value, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationIn(List<String> values) {
            addCriterion("LOGIN_LOCATION in", values, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationNotIn(List<String> values) {
            addCriterion("LOGIN_LOCATION not in", values, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationBetween(String value1, String value2) {
            addCriterion("LOGIN_LOCATION between", value1, value2, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andLoginLocationNotBetween(String value1, String value2) {
            addCriterion("LOGIN_LOCATION not between", value1, value2, "loginLocation");
            return (Criteria) this;
        }

        public Criteria andBrowserIsNull() {
            addCriterion("BROWSER is null");
            return (Criteria) this;
        }

        public Criteria andBrowserIsNotNull() {
            addCriterion("BROWSER is not null");
            return (Criteria) this;
        }

        public Criteria andBrowserEqualTo(String value) {
            addCriterion("BROWSER =", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserNotEqualTo(String value) {
            addCriterion("BROWSER <>", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserGreaterThan(String value) {
            addCriterion("BROWSER >", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserGreaterThanOrEqualTo(String value) {
            addCriterion("BROWSER >=", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserLessThan(String value) {
            addCriterion("BROWSER <", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserLessThanOrEqualTo(String value) {
            addCriterion("BROWSER <=", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserLike(String value) {
            addCriterion("BROWSER like", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserNotLike(String value) {
            addCriterion("BROWSER not like", value, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserIn(List<String> values) {
            addCriterion("BROWSER in", values, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserNotIn(List<String> values) {
            addCriterion("BROWSER not in", values, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserBetween(String value1, String value2) {
            addCriterion("BROWSER between", value1, value2, "browser");
            return (Criteria) this;
        }

        public Criteria andBrowserNotBetween(String value1, String value2) {
            addCriterion("BROWSER not between", value1, value2, "browser");
            return (Criteria) this;
        }

        public Criteria andOsIsNull() {
            addCriterion("OS is null");
            return (Criteria) this;
        }

        public Criteria andOsIsNotNull() {
            addCriterion("OS is not null");
            return (Criteria) this;
        }

        public Criteria andOsEqualTo(String value) {
            addCriterion("OS =", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotEqualTo(String value) {
            addCriterion("OS <>", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsGreaterThan(String value) {
            addCriterion("OS >", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsGreaterThanOrEqualTo(String value) {
            addCriterion("OS >=", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLessThan(String value) {
            addCriterion("OS <", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLessThanOrEqualTo(String value) {
            addCriterion("OS <=", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsLike(String value) {
            addCriterion("OS like", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotLike(String value) {
            addCriterion("OS not like", value, "os");
            return (Criteria) this;
        }

        public Criteria andOsIn(List<String> values) {
            addCriterion("OS in", values, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotIn(List<String> values) {
            addCriterion("OS not in", values, "os");
            return (Criteria) this;
        }

        public Criteria andOsBetween(String value1, String value2) {
            addCriterion("OS between", value1, value2, "os");
            return (Criteria) this;
        }

        public Criteria andOsNotBetween(String value1, String value2) {
            addCriterion("OS not between", value1, value2, "os");
            return (Criteria) this;
        }

        public Criteria andLoginDateIsNull() {
            addCriterion("LOGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLoginDateIsNotNull() {
            addCriterion("LOGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLoginDateEqualTo(Date value) {
            addCriterion("LOGIN_DATE =", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotEqualTo(Date value) {
            addCriterion("LOGIN_DATE <>", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateGreaterThan(Date value) {
            addCriterion("LOGIN_DATE >", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LOGIN_DATE >=", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateLessThan(Date value) {
            addCriterion("LOGIN_DATE <", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateLessThanOrEqualTo(Date value) {
            addCriterion("LOGIN_DATE <=", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateIn(List<Date> values) {
            addCriterion("LOGIN_DATE in", values, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotIn(List<Date> values) {
            addCriterion("LOGIN_DATE not in", values, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateBetween(Date value1, Date value2) {
            addCriterion("LOGIN_DATE between", value1, value2, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotBetween(Date value1, Date value2) {
            addCriterion("LOGIN_DATE not between", value1, value2, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateIsNull() {
            addCriterion("LAST_ACCESS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateIsNotNull() {
            addCriterion("LAST_ACCESS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateEqualTo(Date value) {
            addCriterion("LAST_ACCESS_DATE =", value, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateNotEqualTo(Date value) {
            addCriterion("LAST_ACCESS_DATE <>", value, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateGreaterThan(Date value) {
            addCriterion("LAST_ACCESS_DATE >", value, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_ACCESS_DATE >=", value, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateLessThan(Date value) {
            addCriterion("LAST_ACCESS_DATE <", value, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateLessThanOrEqualTo(Date value) {
            addCriterion("LAST_ACCESS_DATE <=", value, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateIn(List<Date> values) {
            addCriterion("LAST_ACCESS_DATE in", values, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateNotIn(List<Date> values) {
            addCriterion("LAST_ACCESS_DATE not in", values, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateBetween(Date value1, Date value2) {
            addCriterion("LAST_ACCESS_DATE between", value1, value2, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andLastAccessDateNotBetween(Date value1, Date value2) {
            addCriterion("LAST_ACCESS_DATE not between", value1, value2, "lastAccessDate");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeIsNull() {
            addCriterion("EXPIREE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeIsNotNull() {
            addCriterion("EXPIREE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeEqualTo(Integer value) {
            addCriterion("EXPIREE_TIME =", value, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeNotEqualTo(Integer value) {
            addCriterion("EXPIREE_TIME <>", value, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeGreaterThan(Integer value) {
            addCriterion("EXPIREE_TIME >", value, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXPIREE_TIME >=", value, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeLessThan(Integer value) {
            addCriterion("EXPIREE_TIME <", value, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeLessThanOrEqualTo(Integer value) {
            addCriterion("EXPIREE_TIME <=", value, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeIn(List<Integer> values) {
            addCriterion("EXPIREE_TIME in", values, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeNotIn(List<Integer> values) {
            addCriterion("EXPIREE_TIME not in", values, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeBetween(Integer value1, Integer value2) {
            addCriterion("EXPIREE_TIME between", value1, value2, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andExpireeTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("EXPIREE_TIME not between", value1, value2, "expireeTime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("STATE not between", value1, value2, "state");
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