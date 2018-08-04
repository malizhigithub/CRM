package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.neuedu.crm.utils.LocalDateTimeUtil;

/**
 * sale_opportunity
 * @author 
 */
public class SaleOpportunity implements Serializable {
    private Integer id;

    /**
     * 客户id
     */
    private Integer customerId;
    
    /**
     * 客户对象
     */
    private Customer customer;

    /**
     * 机会来源
     */
    private String source;

    /**
     * 成功几率
     */
    private String success;

    /**
     * 机会概要
     */
    private String general;

    /**
     * 客户联系人
     */
    private Integer contactId;
    
    /**
     * 客户联系人对象
     */
    private Linkman linkman;

    /**
     * 客户联系人电话
     */
    private String contactPhone;

    /**
     * 机会描述
     */
    private String description;

    /**
     * 销售机会创建人
     */
    private Integer creater;
    
    /**
     * 创建人对象
     */
    private User createrUser;

    /**
     * 销售机会创建时间
     */
    private LocalDateTime createDate;

    /**
     * 被分配给的客户经理
     */
    private Integer managerId;

    /**
     * 被分配的客户经理对象
     */
    private User manager;
    
    /**
     * 分配时间
     */
    private LocalDateTime allotDate;

    /**
     * 销售机会的状态
     */
    private String status;

    /**
     * 是否已经被删除  0：未删除  1：已删除
     */
    private Integer deleteStatus;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public String getCreateDate() {
    	return  LocalDateTimeUtil.formatTime(this.createDate, "yyyy-MM-dd HH:mm:ss");
    	//return LocalDateTimeUtil.parse(localDateTimeStr, "yyyy-MM-dd HH:mm:ss");
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getAllotDate() {
    	return  LocalDateTimeUtil.formatTime(this.allotDate, "yyyy-MM-dd HH:mm:ss");
    	//return LocalDateTimeUtil.parse(localDateTimeStr, "yyyy-MM-dd HH:mm:ss");
    }

    public void setAllotDate(LocalDateTime allotDate) {
        this.allotDate = allotDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
    
    
    

    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Linkman getLinkman() {
		return linkman;
	}

	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}

	public User getCreaterUser() {
		return createrUser;
	}

	public void setCreaterUser(User createrUser) {
		this.createrUser = createrUser;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
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
        SaleOpportunity other = (SaleOpportunity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getSuccess() == null ? other.getSuccess() == null : this.getSuccess().equals(other.getSuccess()))
            && (this.getGeneral() == null ? other.getGeneral() == null : this.getGeneral().equals(other.getGeneral()))
            && (this.getContactId() == null ? other.getContactId() == null : this.getContactId().equals(other.getContactId()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCreater() == null ? other.getCreater() == null : this.getCreater().equals(other.getCreater()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getManagerId() == null ? other.getManagerId() == null : this.getManagerId().equals(other.getManagerId()))
            && (this.getAllotDate() == null ? other.getAllotDate() == null : this.getAllotDate().equals(other.getAllotDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null : this.getDeleteStatus().equals(other.getDeleteStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getSuccess() == null) ? 0 : getSuccess().hashCode());
        result = prime * result + ((getGeneral() == null) ? 0 : getGeneral().hashCode());
        result = prime * result + ((getContactId() == null) ? 0 : getContactId().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCreater() == null) ? 0 : getCreater().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getManagerId() == null) ? 0 : getManagerId().hashCode());
        result = prime * result + ((getAllotDate() == null) ? 0 : getAllotDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        return result;
    }

	@Override
	public String toString() {
		return "SaleOpportunity [id=" + id + ", customerId=" + customerId + ", customer=" + customer + ", source="
				+ source + ", success=" + success + ", general=" + general + ", contactId=" + contactId + ", linkman="
				+ linkman + ", contactPhone=" + contactPhone + ", description=" + description + ", creater=" + creater
				+ ", createrUser=" + createrUser + ", createDate=" + createDate + ", managerId=" + managerId
				+ ", manager=" + manager + ", allotDate=" + allotDate + ", status=" + status + ", deleteStatus="
				+ deleteStatus + "]";
	}
    
}