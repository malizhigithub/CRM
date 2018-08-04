package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.neuedu.crm.utils.LocalDateTimeUtil;

/**
 * orders
 * @author 
 */
public class Orders implements Serializable {
    private Integer id;

    /**
     * 日期
     */
    private LocalDateTime date;

    /**
     * 送货地址
     */
    private String address;

    /**
     * 总金额
     */
    private Double price;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 客户
     */
    private Integer customerId;
    
    /**
     * 客户
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

    public String getDate() {
        return LocalDateTimeUtil.formatTime(date, "yyyy-MM-dd");
    }
    
    public LocalDateTime getLocalDateTimeDate() {
    	return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
        Orders other = (Orders) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        return result;
    }

    @Override
	public String toString() {
		return "Orders [id=" + id + ", date=" + date + ", address=" + address + ", price=" + price + ", status="
				+ status + ", customerId=" + customerId + ", customer=" + customer + "]";
	}
}