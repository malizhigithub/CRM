package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.neuedu.crm.utils.LocalDateTimeUtil;

/**
 * customer_loss
 * @author 
 */
public class CustomerLoss implements Serializable {
    /**
     * 客户流失管理id
     */
    private Integer id;

    /**
     * 流失用户的id
     */
    private Integer customerId;

    /**
     * 上次下单时间
     */
    private LocalDateTime lastOrderTime;

    /**
     * 暂缓措施
     */
    private String measure;

    /**
     * 追加暂缓措施
     */
    private String measureAppend;

    /**
     * 确认流失时间
     */
    private LocalDateTime lossDate;

    /**
     * 流失原因
     */
    private String reason;

    /**
     * 流失状态  0将要流失   1挽回客户   2确认流失
     */
    private String status;
    
    
    /**
     * 流失的客户
     */
    private Customer customer;
    
   
    public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

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

    public String getLastOrderTime() {
        return LocalDateTimeUtil.formatTime(lastOrderTime, "yyyy-MM-dd");
    }

    public void setLastOrderTime(LocalDateTime lastOrderTime) {
        this.lastOrderTime = lastOrderTime;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getMeasureAppend() {
        return measureAppend;
    }

    public void setMeasureAppend(String measureAppend) {
        this.measureAppend = measureAppend;
    }

    public String getLossDate() {
        return LocalDateTimeUtil.formatTime(lossDate, "yyyy-MM-dd");
    }

    public void setLossDate(LocalDateTime lossDate) {
        this.lossDate = lossDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
//    	switch (status) {
//			case "0":
//				return "将要流失";
//				
//			case "1":
//				return "暂缓流失";
//			
//			case "2":
//				return "已流失";
//			default:
//				return "未知";
//		}
    	return status;
    }

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
        CustomerLoss other = (CustomerLoss) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getLastOrderTime() == null ? other.getLastOrderTime() == null : this.getLastOrderTime().equals(other.getLastOrderTime()))
            && (this.getMeasure() == null ? other.getMeasure() == null : this.getMeasure().equals(other.getMeasure()))
            && (this.getMeasureAppend() == null ? other.getMeasureAppend() == null : this.getMeasureAppend().equals(other.getMeasureAppend()))
            && (this.getLossDate() == null ? other.getLossDate() == null : this.getLossDate().equals(other.getLossDate()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getLastOrderTime() == null) ? 0 : getLastOrderTime().hashCode());
        result = prime * result + ((getMeasure() == null) ? 0 : getMeasure().hashCode());
        result = prime * result + ((getMeasureAppend() == null) ? 0 : getMeasureAppend().hashCode());
        result = prime * result + ((getLossDate() == null) ? 0 : getLossDate().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
	public String toString() {
		return "CustomerLoss [id=" + id + ", customerId=" + customerId + ", lastOrderTime=" + lastOrderTime
				+ ", measure=" + measure + ", measureAppend=" + measureAppend + ", lossDate=" + lossDate + ", reason="
				+ reason + ", status=" + status + ", customer=" + customer + "]";
	}
}