package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * customer_transfer
 * @author 
 */
public class CustomerTransfer implements Serializable {
    /**
     * 客户转移记录id
     */
    private Integer id;

    /**
     * 被转移的客户id
     */
    private Integer customerId;

    /**
     * 转移前的客户经理
     */
    private Integer oldManagerId;
    private User oldManager;
    /**
     * 转移后的客户经理
     */
    private Integer newManagerId;
    private User newManager;
    /**
     * 转移原因
     */
    private String reason;

    /**
     * 转移时间
     */
    private LocalDateTime time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
        CustomerTransfer other = (CustomerTransfer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getOldManagerId() == null ? other.getOldManagerId() == null : this.getOldManagerId().equals(other.getOldManagerId()))
            && (this.getNewManagerId() == null ? other.getNewManagerId() == null : this.getNewManagerId().equals(other.getNewManagerId()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getOldManagerId() == null) ? 0 : getOldManagerId().hashCode());
        result = prime * result + ((getNewManagerId() == null) ? 0 : getNewManagerId().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customerId=").append(customerId);
        sb.append(", oldManagerId=").append(oldManagerId);
        sb.append(", newManagerId=").append(newManagerId);
        sb.append(", reason=").append(reason);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}