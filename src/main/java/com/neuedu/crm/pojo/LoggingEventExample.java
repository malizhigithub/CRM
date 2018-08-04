package com.neuedu.crm.pojo;

import java.util.ArrayList;
import java.util.List;

public class LoggingEventExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public LoggingEventExample() {
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

        public Criteria andEventIdIsNull() {
            addCriterion("event_id is null");
            return (Criteria) this;
        }

        public Criteria andEventIdIsNotNull() {
            addCriterion("event_id is not null");
            return (Criteria) this;
        }

        public Criteria andEventIdEqualTo(Long value) {
            addCriterion("event_id =", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotEqualTo(Long value) {
            addCriterion("event_id <>", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdGreaterThan(Long value) {
            addCriterion("event_id >", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdGreaterThanOrEqualTo(Long value) {
            addCriterion("event_id >=", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdLessThan(Long value) {
            addCriterion("event_id <", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdLessThanOrEqualTo(Long value) {
            addCriterion("event_id <=", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdIn(List<Long> values) {
            addCriterion("event_id in", values, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotIn(List<Long> values) {
            addCriterion("event_id not in", values, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdBetween(Long value1, Long value2) {
            addCriterion("event_id between", value1, value2, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotBetween(Long value1, Long value2) {
            addCriterion("event_id not between", value1, value2, "eventId");
            return (Criteria) this;
        }

        public Criteria andTimestmpIsNull() {
            addCriterion("timestmp is null");
            return (Criteria) this;
        }

        public Criteria andTimestmpIsNotNull() {
            addCriterion("timestmp is not null");
            return (Criteria) this;
        }

        public Criteria andTimestmpEqualTo(Long value) {
            addCriterion("timestmp =", value, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpNotEqualTo(Long value) {
            addCriterion("timestmp <>", value, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpGreaterThan(Long value) {
            addCriterion("timestmp >", value, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpGreaterThanOrEqualTo(Long value) {
            addCriterion("timestmp >=", value, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpLessThan(Long value) {
            addCriterion("timestmp <", value, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpLessThanOrEqualTo(Long value) {
            addCriterion("timestmp <=", value, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpIn(List<Long> values) {
            addCriterion("timestmp in", values, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpNotIn(List<Long> values) {
            addCriterion("timestmp not in", values, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpBetween(Long value1, Long value2) {
            addCriterion("timestmp between", value1, value2, "timestmp");
            return (Criteria) this;
        }

        public Criteria andTimestmpNotBetween(Long value1, Long value2) {
            addCriterion("timestmp not between", value1, value2, "timestmp");
            return (Criteria) this;
        }

        public Criteria andLoggerNameIsNull() {
            addCriterion("logger_name is null");
            return (Criteria) this;
        }

        public Criteria andLoggerNameIsNotNull() {
            addCriterion("logger_name is not null");
            return (Criteria) this;
        }

        public Criteria andLoggerNameEqualTo(String value) {
            addCriterion("logger_name =", value, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameNotEqualTo(String value) {
            addCriterion("logger_name <>", value, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameGreaterThan(String value) {
            addCriterion("logger_name >", value, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameGreaterThanOrEqualTo(String value) {
            addCriterion("logger_name >=", value, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameLessThan(String value) {
            addCriterion("logger_name <", value, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameLessThanOrEqualTo(String value) {
            addCriterion("logger_name <=", value, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameLike(String value) {
            addCriterion("logger_name like", value, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameNotLike(String value) {
            addCriterion("logger_name not like", value, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameIn(List<String> values) {
            addCriterion("logger_name in", values, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameNotIn(List<String> values) {
            addCriterion("logger_name not in", values, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameBetween(String value1, String value2) {
            addCriterion("logger_name between", value1, value2, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLoggerNameNotBetween(String value1, String value2) {
            addCriterion("logger_name not between", value1, value2, "loggerName");
            return (Criteria) this;
        }

        public Criteria andLevelStringIsNull() {
            addCriterion("level_string is null");
            return (Criteria) this;
        }

        public Criteria andLevelStringIsNotNull() {
            addCriterion("level_string is not null");
            return (Criteria) this;
        }

        public Criteria andLevelStringEqualTo(String value) {
            addCriterion("level_string =", value, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringNotEqualTo(String value) {
            addCriterion("level_string <>", value, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringGreaterThan(String value) {
            addCriterion("level_string >", value, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringGreaterThanOrEqualTo(String value) {
            addCriterion("level_string >=", value, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringLessThan(String value) {
            addCriterion("level_string <", value, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringLessThanOrEqualTo(String value) {
            addCriterion("level_string <=", value, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringLike(String value) {
            addCriterion("level_string like", value, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringNotLike(String value) {
            addCriterion("level_string not like", value, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringIn(List<String> values) {
            addCriterion("level_string in", values, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringNotIn(List<String> values) {
            addCriterion("level_string not in", values, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringBetween(String value1, String value2) {
            addCriterion("level_string between", value1, value2, "levelString");
            return (Criteria) this;
        }

        public Criteria andLevelStringNotBetween(String value1, String value2) {
            addCriterion("level_string not between", value1, value2, "levelString");
            return (Criteria) this;
        }

        public Criteria andThreadNameIsNull() {
            addCriterion("thread_name is null");
            return (Criteria) this;
        }

        public Criteria andThreadNameIsNotNull() {
            addCriterion("thread_name is not null");
            return (Criteria) this;
        }

        public Criteria andThreadNameEqualTo(String value) {
            addCriterion("thread_name =", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameNotEqualTo(String value) {
            addCriterion("thread_name <>", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameGreaterThan(String value) {
            addCriterion("thread_name >", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameGreaterThanOrEqualTo(String value) {
            addCriterion("thread_name >=", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameLessThan(String value) {
            addCriterion("thread_name <", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameLessThanOrEqualTo(String value) {
            addCriterion("thread_name <=", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameLike(String value) {
            addCriterion("thread_name like", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameNotLike(String value) {
            addCriterion("thread_name not like", value, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameIn(List<String> values) {
            addCriterion("thread_name in", values, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameNotIn(List<String> values) {
            addCriterion("thread_name not in", values, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameBetween(String value1, String value2) {
            addCriterion("thread_name between", value1, value2, "threadName");
            return (Criteria) this;
        }

        public Criteria andThreadNameNotBetween(String value1, String value2) {
            addCriterion("thread_name not between", value1, value2, "threadName");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagIsNull() {
            addCriterion("reference_flag is null");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagIsNotNull() {
            addCriterion("reference_flag is not null");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagEqualTo(Short value) {
            addCriterion("reference_flag =", value, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagNotEqualTo(Short value) {
            addCriterion("reference_flag <>", value, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagGreaterThan(Short value) {
            addCriterion("reference_flag >", value, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagGreaterThanOrEqualTo(Short value) {
            addCriterion("reference_flag >=", value, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagLessThan(Short value) {
            addCriterion("reference_flag <", value, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagLessThanOrEqualTo(Short value) {
            addCriterion("reference_flag <=", value, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagIn(List<Short> values) {
            addCriterion("reference_flag in", values, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagNotIn(List<Short> values) {
            addCriterion("reference_flag not in", values, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagBetween(Short value1, Short value2) {
            addCriterion("reference_flag between", value1, value2, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andReferenceFlagNotBetween(Short value1, Short value2) {
            addCriterion("reference_flag not between", value1, value2, "referenceFlag");
            return (Criteria) this;
        }

        public Criteria andArg0IsNull() {
            addCriterion("arg0 is null");
            return (Criteria) this;
        }

        public Criteria andArg0IsNotNull() {
            addCriterion("arg0 is not null");
            return (Criteria) this;
        }

        public Criteria andArg0EqualTo(String value) {
            addCriterion("arg0 =", value, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0NotEqualTo(String value) {
            addCriterion("arg0 <>", value, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0GreaterThan(String value) {
            addCriterion("arg0 >", value, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0GreaterThanOrEqualTo(String value) {
            addCriterion("arg0 >=", value, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0LessThan(String value) {
            addCriterion("arg0 <", value, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0LessThanOrEqualTo(String value) {
            addCriterion("arg0 <=", value, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0Like(String value) {
            addCriterion("arg0 like", value, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0NotLike(String value) {
            addCriterion("arg0 not like", value, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0In(List<String> values) {
            addCriterion("arg0 in", values, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0NotIn(List<String> values) {
            addCriterion("arg0 not in", values, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0Between(String value1, String value2) {
            addCriterion("arg0 between", value1, value2, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg0NotBetween(String value1, String value2) {
            addCriterion("arg0 not between", value1, value2, "arg0");
            return (Criteria) this;
        }

        public Criteria andArg1IsNull() {
            addCriterion("arg1 is null");
            return (Criteria) this;
        }

        public Criteria andArg1IsNotNull() {
            addCriterion("arg1 is not null");
            return (Criteria) this;
        }

        public Criteria andArg1EqualTo(String value) {
            addCriterion("arg1 =", value, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1NotEqualTo(String value) {
            addCriterion("arg1 <>", value, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1GreaterThan(String value) {
            addCriterion("arg1 >", value, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1GreaterThanOrEqualTo(String value) {
            addCriterion("arg1 >=", value, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1LessThan(String value) {
            addCriterion("arg1 <", value, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1LessThanOrEqualTo(String value) {
            addCriterion("arg1 <=", value, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1Like(String value) {
            addCriterion("arg1 like", value, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1NotLike(String value) {
            addCriterion("arg1 not like", value, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1In(List<String> values) {
            addCriterion("arg1 in", values, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1NotIn(List<String> values) {
            addCriterion("arg1 not in", values, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1Between(String value1, String value2) {
            addCriterion("arg1 between", value1, value2, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg1NotBetween(String value1, String value2) {
            addCriterion("arg1 not between", value1, value2, "arg1");
            return (Criteria) this;
        }

        public Criteria andArg2IsNull() {
            addCriterion("arg2 is null");
            return (Criteria) this;
        }

        public Criteria andArg2IsNotNull() {
            addCriterion("arg2 is not null");
            return (Criteria) this;
        }

        public Criteria andArg2EqualTo(String value) {
            addCriterion("arg2 =", value, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2NotEqualTo(String value) {
            addCriterion("arg2 <>", value, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2GreaterThan(String value) {
            addCriterion("arg2 >", value, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2GreaterThanOrEqualTo(String value) {
            addCriterion("arg2 >=", value, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2LessThan(String value) {
            addCriterion("arg2 <", value, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2LessThanOrEqualTo(String value) {
            addCriterion("arg2 <=", value, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2Like(String value) {
            addCriterion("arg2 like", value, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2NotLike(String value) {
            addCriterion("arg2 not like", value, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2In(List<String> values) {
            addCriterion("arg2 in", values, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2NotIn(List<String> values) {
            addCriterion("arg2 not in", values, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2Between(String value1, String value2) {
            addCriterion("arg2 between", value1, value2, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg2NotBetween(String value1, String value2) {
            addCriterion("arg2 not between", value1, value2, "arg2");
            return (Criteria) this;
        }

        public Criteria andArg3IsNull() {
            addCriterion("arg3 is null");
            return (Criteria) this;
        }

        public Criteria andArg3IsNotNull() {
            addCriterion("arg3 is not null");
            return (Criteria) this;
        }

        public Criteria andArg3EqualTo(String value) {
            addCriterion("arg3 =", value, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3NotEqualTo(String value) {
            addCriterion("arg3 <>", value, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3GreaterThan(String value) {
            addCriterion("arg3 >", value, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3GreaterThanOrEqualTo(String value) {
            addCriterion("arg3 >=", value, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3LessThan(String value) {
            addCriterion("arg3 <", value, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3LessThanOrEqualTo(String value) {
            addCriterion("arg3 <=", value, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3Like(String value) {
            addCriterion("arg3 like", value, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3NotLike(String value) {
            addCriterion("arg3 not like", value, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3In(List<String> values) {
            addCriterion("arg3 in", values, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3NotIn(List<String> values) {
            addCriterion("arg3 not in", values, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3Between(String value1, String value2) {
            addCriterion("arg3 between", value1, value2, "arg3");
            return (Criteria) this;
        }

        public Criteria andArg3NotBetween(String value1, String value2) {
            addCriterion("arg3 not between", value1, value2, "arg3");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameIsNull() {
            addCriterion("caller_filename is null");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameIsNotNull() {
            addCriterion("caller_filename is not null");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameEqualTo(String value) {
            addCriterion("caller_filename =", value, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameNotEqualTo(String value) {
            addCriterion("caller_filename <>", value, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameGreaterThan(String value) {
            addCriterion("caller_filename >", value, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("caller_filename >=", value, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameLessThan(String value) {
            addCriterion("caller_filename <", value, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameLessThanOrEqualTo(String value) {
            addCriterion("caller_filename <=", value, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameLike(String value) {
            addCriterion("caller_filename like", value, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameNotLike(String value) {
            addCriterion("caller_filename not like", value, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameIn(List<String> values) {
            addCriterion("caller_filename in", values, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameNotIn(List<String> values) {
            addCriterion("caller_filename not in", values, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameBetween(String value1, String value2) {
            addCriterion("caller_filename between", value1, value2, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerFilenameNotBetween(String value1, String value2) {
            addCriterion("caller_filename not between", value1, value2, "callerFilename");
            return (Criteria) this;
        }

        public Criteria andCallerClassIsNull() {
            addCriterion("caller_class is null");
            return (Criteria) this;
        }

        public Criteria andCallerClassIsNotNull() {
            addCriterion("caller_class is not null");
            return (Criteria) this;
        }

        public Criteria andCallerClassEqualTo(String value) {
            addCriterion("caller_class =", value, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassNotEqualTo(String value) {
            addCriterion("caller_class <>", value, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassGreaterThan(String value) {
            addCriterion("caller_class >", value, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassGreaterThanOrEqualTo(String value) {
            addCriterion("caller_class >=", value, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassLessThan(String value) {
            addCriterion("caller_class <", value, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassLessThanOrEqualTo(String value) {
            addCriterion("caller_class <=", value, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassLike(String value) {
            addCriterion("caller_class like", value, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassNotLike(String value) {
            addCriterion("caller_class not like", value, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassIn(List<String> values) {
            addCriterion("caller_class in", values, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassNotIn(List<String> values) {
            addCriterion("caller_class not in", values, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassBetween(String value1, String value2) {
            addCriterion("caller_class between", value1, value2, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerClassNotBetween(String value1, String value2) {
            addCriterion("caller_class not between", value1, value2, "callerClass");
            return (Criteria) this;
        }

        public Criteria andCallerMethodIsNull() {
            addCriterion("caller_method is null");
            return (Criteria) this;
        }

        public Criteria andCallerMethodIsNotNull() {
            addCriterion("caller_method is not null");
            return (Criteria) this;
        }

        public Criteria andCallerMethodEqualTo(String value) {
            addCriterion("caller_method =", value, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodNotEqualTo(String value) {
            addCriterion("caller_method <>", value, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodGreaterThan(String value) {
            addCriterion("caller_method >", value, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodGreaterThanOrEqualTo(String value) {
            addCriterion("caller_method >=", value, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodLessThan(String value) {
            addCriterion("caller_method <", value, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodLessThanOrEqualTo(String value) {
            addCriterion("caller_method <=", value, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodLike(String value) {
            addCriterion("caller_method like", value, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodNotLike(String value) {
            addCriterion("caller_method not like", value, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodIn(List<String> values) {
            addCriterion("caller_method in", values, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodNotIn(List<String> values) {
            addCriterion("caller_method not in", values, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodBetween(String value1, String value2) {
            addCriterion("caller_method between", value1, value2, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerMethodNotBetween(String value1, String value2) {
            addCriterion("caller_method not between", value1, value2, "callerMethod");
            return (Criteria) this;
        }

        public Criteria andCallerLineIsNull() {
            addCriterion("caller_line is null");
            return (Criteria) this;
        }

        public Criteria andCallerLineIsNotNull() {
            addCriterion("caller_line is not null");
            return (Criteria) this;
        }

        public Criteria andCallerLineEqualTo(String value) {
            addCriterion("caller_line =", value, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineNotEqualTo(String value) {
            addCriterion("caller_line <>", value, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineGreaterThan(String value) {
            addCriterion("caller_line >", value, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineGreaterThanOrEqualTo(String value) {
            addCriterion("caller_line >=", value, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineLessThan(String value) {
            addCriterion("caller_line <", value, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineLessThanOrEqualTo(String value) {
            addCriterion("caller_line <=", value, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineLike(String value) {
            addCriterion("caller_line like", value, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineNotLike(String value) {
            addCriterion("caller_line not like", value, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineIn(List<String> values) {
            addCriterion("caller_line in", values, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineNotIn(List<String> values) {
            addCriterion("caller_line not in", values, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineBetween(String value1, String value2) {
            addCriterion("caller_line between", value1, value2, "callerLine");
            return (Criteria) this;
        }

        public Criteria andCallerLineNotBetween(String value1, String value2) {
            addCriterion("caller_line not between", value1, value2, "callerLine");
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