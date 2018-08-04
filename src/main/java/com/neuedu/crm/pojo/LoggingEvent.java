package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * logging_event
 * @author 
 */
public class LoggingEvent implements Serializable {
    private Long eventId;

    private Long timestmp;

    private String loggerName;

    private String levelString;

    private String threadName;

    private Short referenceFlag;

    private String arg0;

    private String arg1;

    private String arg2;

    private String arg3;

    private String callerFilename;

    private String callerClass;

    private String callerMethod;

    private String callerLine;

    private String formattedMessage;

    private static final long serialVersionUID = 1L;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTimestmp() {
    	return timestmp;
    	//return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestmp + 0));
    }
    
    //时间戳转换
    public String getTimestamp() {
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestmp + 0));
    }

    public void setTimestmp(Long timestmp) {
        this.timestmp = timestmp;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLevelString() {
        return levelString;
    }

    public void setLevelString(String levelString) {
        this.levelString = levelString;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Short getReferenceFlag() {
        return referenceFlag;
    }

    public void setReferenceFlag(Short referenceFlag) {
        this.referenceFlag = referenceFlag;
    }

    public String getArg0() {
        return arg0;
    }

    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getArg3() {
        return arg3;
    }

    public void setArg3(String arg3) {
        this.arg3 = arg3;
    }

    public String getCallerFilename() {
        return callerFilename;
    }

    public void setCallerFilename(String callerFilename) {
        this.callerFilename = callerFilename;
    }

    public String getCallerClass() {
        return callerClass;
    }

    public void setCallerClass(String callerClass) {
        this.callerClass = callerClass;
    }

    public String getCallerMethod() {
        return callerMethod;
    }

    public void setCallerMethod(String callerMethod) {
        this.callerMethod = callerMethod;
    }

    public String getCallerLine() {
        return callerLine;
    }

    public void setCallerLine(String callerLine) {
        this.callerLine = callerLine;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LoggingEvent other = (LoggingEvent) that;
        return (this.getEventId() == null ? other.getEventId() == null : this.getEventId().equals(other.getEventId()))
            && (this.getTimestmp() == null ? other.getTimestmp() == null : this.getTimestmp().equals(other.getTimestmp()))
            && (this.getLoggerName() == null ? other.getLoggerName() == null : this.getLoggerName().equals(other.getLoggerName()))
            && (this.getLevelString() == null ? other.getLevelString() == null : this.getLevelString().equals(other.getLevelString()))
            && (this.getThreadName() == null ? other.getThreadName() == null : this.getThreadName().equals(other.getThreadName()))
            && (this.getReferenceFlag() == null ? other.getReferenceFlag() == null : this.getReferenceFlag().equals(other.getReferenceFlag()))
            && (this.getArg0() == null ? other.getArg0() == null : this.getArg0().equals(other.getArg0()))
            && (this.getArg1() == null ? other.getArg1() == null : this.getArg1().equals(other.getArg1()))
            && (this.getArg2() == null ? other.getArg2() == null : this.getArg2().equals(other.getArg2()))
            && (this.getArg3() == null ? other.getArg3() == null : this.getArg3().equals(other.getArg3()))
            && (this.getCallerFilename() == null ? other.getCallerFilename() == null : this.getCallerFilename().equals(other.getCallerFilename()))
            && (this.getCallerClass() == null ? other.getCallerClass() == null : this.getCallerClass().equals(other.getCallerClass()))
            && (this.getCallerMethod() == null ? other.getCallerMethod() == null : this.getCallerMethod().equals(other.getCallerMethod()))
            && (this.getCallerLine() == null ? other.getCallerLine() == null : this.getCallerLine().equals(other.getCallerLine()))
            && (this.getFormattedMessage() == null ? other.getFormattedMessage() == null : this.getFormattedMessage().equals(other.getFormattedMessage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEventId() == null) ? 0 : getEventId().hashCode());
        result = prime * result + ((getTimestmp() == null) ? 0 : getTimestmp().hashCode());
        result = prime * result + ((getLoggerName() == null) ? 0 : getLoggerName().hashCode());
        result = prime * result + ((getLevelString() == null) ? 0 : getLevelString().hashCode());
        result = prime * result + ((getThreadName() == null) ? 0 : getThreadName().hashCode());
        result = prime * result + ((getReferenceFlag() == null) ? 0 : getReferenceFlag().hashCode());
        result = prime * result + ((getArg0() == null) ? 0 : getArg0().hashCode());
        result = prime * result + ((getArg1() == null) ? 0 : getArg1().hashCode());
        result = prime * result + ((getArg2() == null) ? 0 : getArg2().hashCode());
        result = prime * result + ((getArg3() == null) ? 0 : getArg3().hashCode());
        result = prime * result + ((getCallerFilename() == null) ? 0 : getCallerFilename().hashCode());
        result = prime * result + ((getCallerClass() == null) ? 0 : getCallerClass().hashCode());
        result = prime * result + ((getCallerMethod() == null) ? 0 : getCallerMethod().hashCode());
        result = prime * result + ((getCallerLine() == null) ? 0 : getCallerLine().hashCode());
        result = prime * result + ((getFormattedMessage() == null) ? 0 : getFormattedMessage().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", eventId=").append(eventId);
        sb.append(", timestmp=").append(timestmp);
        sb.append(", loggerName=").append(loggerName);
        sb.append(", levelString=").append(levelString);
        sb.append(", threadName=").append(threadName);
        sb.append(", referenceFlag=").append(referenceFlag);
        sb.append(", arg0=").append(arg0);
        sb.append(", arg1=").append(arg1);
        sb.append(", arg2=").append(arg2);
        sb.append(", arg3=").append(arg3);
        sb.append(", callerFilename=").append(callerFilename);
        sb.append(", callerClass=").append(callerClass);
        sb.append(", callerMethod=").append(callerMethod);
        sb.append(", callerLine=").append(callerLine);
        sb.append(", formattedMessage=").append(formattedMessage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}