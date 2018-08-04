package com.neuedu.crm.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerLossExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public CustomerLossExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
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

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeIsNull() {
            addCriterion("last_order_time is null");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeIsNotNull() {
            addCriterion("last_order_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeEqualTo(LocalDateTime value) {
            addCriterion("last_order_time =", value, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeNotEqualTo(LocalDateTime value) {
            addCriterion("last_order_time <>", value, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeGreaterThan(LocalDateTime value) {
            addCriterion("last_order_time >", value, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("last_order_time >=", value, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeLessThan(LocalDateTime value) {
            addCriterion("last_order_time <", value, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("last_order_time <=", value, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeIn(List<LocalDateTime> values) {
            addCriterion("last_order_time in", values, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeNotIn(List<LocalDateTime> values) {
            addCriterion("last_order_time not in", values, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("last_order_time between", value1, value2, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andLastOrderTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("last_order_time not between", value1, value2, "lastOrderTime");
            return (Criteria) this;
        }

        public Criteria andMeasureIsNull() {
            addCriterion("measure is null");
            return (Criteria) this;
        }

        public Criteria andMeasureIsNotNull() {
            addCriterion("measure is not null");
            return (Criteria) this;
        }

        public Criteria andMeasureEqualTo(String value) {
            addCriterion("measure =", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureNotEqualTo(String value) {
            addCriterion("measure <>", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureGreaterThan(String value) {
            addCriterion("measure >", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureGreaterThanOrEqualTo(String value) {
            addCriterion("measure >=", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureLessThan(String value) {
            addCriterion("measure <", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureLessThanOrEqualTo(String value) {
            addCriterion("measure <=", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureLike(String value) {
            addCriterion("measure like", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureNotLike(String value) {
            addCriterion("measure not like", value, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureIn(List<String> values) {
            addCriterion("measure in", values, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureNotIn(List<String> values) {
            addCriterion("measure not in", values, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureBetween(String value1, String value2) {
            addCriterion("measure between", value1, value2, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureNotBetween(String value1, String value2) {
            addCriterion("measure not between", value1, value2, "measure");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendIsNull() {
            addCriterion("measure_append is null");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendIsNotNull() {
            addCriterion("measure_append is not null");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendEqualTo(String value) {
            addCriterion("measure_append =", value, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendNotEqualTo(String value) {
            addCriterion("measure_append <>", value, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendGreaterThan(String value) {
            addCriterion("measure_append >", value, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendGreaterThanOrEqualTo(String value) {
            addCriterion("measure_append >=", value, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendLessThan(String value) {
            addCriterion("measure_append <", value, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendLessThanOrEqualTo(String value) {
            addCriterion("measure_append <=", value, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendLike(String value) {
            addCriterion("measure_append like", value, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendNotLike(String value) {
            addCriterion("measure_append not like", value, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendIn(List<String> values) {
            addCriterion("measure_append in", values, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendNotIn(List<String> values) {
            addCriterion("measure_append not in", values, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendBetween(String value1, String value2) {
            addCriterion("measure_append between", value1, value2, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andMeasureAppendNotBetween(String value1, String value2) {
            addCriterion("measure_append not between", value1, value2, "measureAppend");
            return (Criteria) this;
        }

        public Criteria andLossDateIsNull() {
            addCriterion("loss_date is null");
            return (Criteria) this;
        }

        public Criteria andLossDateIsNotNull() {
            addCriterion("loss_date is not null");
            return (Criteria) this;
        }

        public Criteria andLossDateEqualTo(LocalDateTime value) {
            addCriterion("loss_date =", value, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateNotEqualTo(LocalDateTime value) {
            addCriterion("loss_date <>", value, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateGreaterThan(LocalDateTime value) {
            addCriterion("loss_date >", value, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("loss_date >=", value, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateLessThan(LocalDateTime value) {
            addCriterion("loss_date <", value, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("loss_date <=", value, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateIn(List<LocalDateTime> values) {
            addCriterion("loss_date in", values, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateNotIn(List<LocalDateTime> values) {
            addCriterion("loss_date not in", values, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("loss_date between", value1, value2, "lossDate");
            return (Criteria) this;
        }

        public Criteria andLossDateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("loss_date not between", value1, value2, "lossDate");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    /**
     */
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