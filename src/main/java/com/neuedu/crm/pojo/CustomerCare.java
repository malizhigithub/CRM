package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.neuedu.crm.utils.LocalDateTimeUtil;

/**
 * customer_care
 * @author 
 */
public class CustomerCare implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 关怀的联系人ID
     */
    private Integer linkmanId;
    
    /**
     * 联系人实体
     */
    private Linkman linkman;

    /**
     * 记录插入记录时联系人的生日
     */
    private LocalDate birthday;
    
    /**
     * 关怀时间
     */
    private LocalDateTime time;

    /**
     * 关怀详情
     */
    private String detail;

    /**
     * 关怀类型
     */
    private String type;
    
    /**
     * 关怀状态
     */
    private String status;
    
    /**
     * 客户反馈
     */
    private String feedback;

    /**
     * 进行关怀的客户经理
     */
    private Integer managerId;
    
    /**
     * 插入时间
     */
    private LocalDateTime createTime;
    
    /**
     * 进行关怀的客户经理实体
     */
    private User manager;
    
    private static final long serialVersionUID = 1L;

    /**
     * @return the createTime
     */
    public String getCreateTime() {
        return LocalDateTimeUtil.formatTime(createTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLinkmanId() {
        return linkmanId;
    }

    public void setLinkmanId(Integer linkmanId) {
        this.linkmanId = linkmanId;
    }

    public String getTime() {
        return LocalDateTimeUtil.formatTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * @return the linkman
     */
    public Linkman getLinkman() {
        return linkman;
    }

    /**
     * @param linkman the linkman to set
     */
    public void setLinkman(Linkman linkman) {
        this.linkman = linkman;
    }

    /**
     * @return the manager
     */
    public User getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(User manager) {
        this.manager = manager;
    }

    /**
     * @return the birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
        CustomerCare other = (CustomerCare) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLinkmanId() == null ? other.getLinkmanId() == null : this.getLinkmanId().equals(other.getLinkmanId()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getFeedback() == null ? other.getFeedback() == null : this.getFeedback().equals(other.getFeedback()))
            && (this.getManagerId() == null ? other.getManagerId() == null : this.getManagerId().equals(other.getManagerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLinkmanId() == null) ? 0 : getLinkmanId().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getFeedback() == null) ? 0 : getFeedback().hashCode());
        result = prime * result + ((getManagerId() == null) ? 0 : getManagerId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", linkmanId=").append(linkmanId);
        sb.append(", time=").append(time);
        sb.append(", detail=").append(detail);
        sb.append(", type=").append(type);
        sb.append(", feedback=").append(feedback);
        sb.append(", managerId=").append(managerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}