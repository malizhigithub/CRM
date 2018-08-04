package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.neuedu.crm.utils.LocalDateTimeUtil;

/**
 * service_transfer
 * @author 
 */
public class ServiceTransfer implements Serializable {
    private Integer id;

    /**
     * 服务id
     */
    private Integer serviceId;

    /**
     * 进行转交服务的客户经理
     */
    private Integer oldManagerId;

    /**
     * 进行转交服务的客户经理实体
     */
    private User oldManager;
    
    /**
     * 被转交服务的客户经理
     */
    private Integer newManagerId;

    /**
     * 被转交服务的客户经理实体
     */
    private User newManager;
    
    /**
     * 转交时间
     */
    private LocalDateTime time;

    /**
     * 转交原因
     */
    private String reason;

    
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getOldManagerId() {
        return oldManagerId;
    }

    public void setOldManagerId(Integer oldManagerId) {
        this.oldManagerId = oldManagerId;
    }

    public Integer getNewManagerId() {
        return newManagerId;
    }

    public void setNewManagerId(Integer newManagerId) {
        this.newManagerId = newManagerId;
    }

    public String getTime() {
        return LocalDateTimeUtil.formatTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getOldManager() {
        return oldManager;
    }

    public void setOldManager(User oldManager) {
        this.oldManager = oldManager;
    }

    public User getNewManager() {
        return newManager;
    }

    public void setNewManager(User newManager) {
        this.newManager = newManager;
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
        ServiceTransfer other = (ServiceTransfer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getServiceId() == null ? other.getServiceId() == null : this.getServiceId().equals(other.getServiceId()))
            && (this.getOldManagerId() == null ? other.getOldManagerId() == null : this.getOldManagerId().equals(other.getOldManagerId()))
            && (this.getNewManagerId() == null ? other.getNewManagerId() == null : this.getNewManagerId().equals(other.getNewManagerId()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getServiceId() == null) ? 0 : getServiceId().hashCode());
        result = prime * result + ((getOldManagerId() == null) ? 0 : getOldManagerId().hashCode());
        result = prime * result + ((getNewManagerId() == null) ? 0 : getNewManagerId().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", oldManagerId=").append(oldManagerId);
        sb.append(", newManagerId=").append(newManagerId);
        sb.append(", time=").append(time);
        sb.append(", reason=").append(reason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}