package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.neuedu.crm.utils.LocalDateTimeUtil;

/**
 * service
 * @author 
 */
public class Service implements Serializable {
    private Integer id;

    /**
     * 服务的类型(数据字典)
     */
    private String type;

    /**
     * 服务的概要
     */
    private String general;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 客户实体
     */
    private Customer customer;
    
    /**
     * 服务紧急程度(数据字典)
     */
    private String emergency;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 服务状态(数据字典)
     */
    private String status;

    /**
     * 客户请求内容
     */
    private String request;

    /**
     * 服务的创建人
     */
    private Integer creater;

    /**
     * 服务的创建实体
     */
    private User createrObject;
    
    /**
     * 服务的创建时间
     */
    private LocalDateTime createDate;

    /**
     * 服务的处理内容
     */
    private String handleContent;

    /**
     * 服务的处理人
     */
    private Integer handler;

    /**
     * 服务的处理人实体
     */
    private User handlerObject;
    
    /**
     * 服务的处理时间
     */
    private LocalDateTime handlerTime;

    /**
     * 服务的处理结果
     */
    private String handleResult;

    /**
     * 客户对服务的满意度(数据字典)
     */
    private String degree;

    /**
     * 是否删除状态  0：未删除   1：已删除
     */
    private Integer deleteStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public String getCreateDate() {
        return LocalDateTimeUtil.formatTime(createDate, "yyyy-MM-dd HH:mm:ss");
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent;
    }

    public Integer getHandler() {
        return handler;
    }

    public void setHandler(Integer handler) {
        this.handler = handler;
    }

    public String getHandlerTime() {
        return LocalDateTimeUtil.formatTime(handlerTime, "yyyy-MM-dd HH:mm:ss");
    }

    public void setHandlerTime(LocalDateTime handlerTime) {
        this.handlerTime = handlerTime;
    }

    public String getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(String handleResult) {
        this.handleResult = handleResult;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the createrObject
     */
    public User getCreaterObject() {
        return createrObject;
    }

    /**
     * @param createrObject the createrObject to set
     */
    public void setCreaterObject(User createrObject) {
        this.createrObject = createrObject;
    }

    /**
     * @return the handlerObject
     */
    public User getHandlerObject() {
        return handlerObject;
    }

    /**
     * @param handlerObject the handlerObject to set
     */
    public void setHandlerObject(User handlerObject) {
        this.handlerObject = handlerObject;
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
        Service other = (Service) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getGeneral() == null ? other.getGeneral() == null : this.getGeneral().equals(other.getGeneral()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getEmergency() == null ? other.getEmergency() == null : this.getEmergency().equals(other.getEmergency()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRequest() == null ? other.getRequest() == null : this.getRequest().equals(other.getRequest()))
            && (this.getCreater() == null ? other.getCreater() == null : this.getCreater().equals(other.getCreater()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getHandleContent() == null ? other.getHandleContent() == null : this.getHandleContent().equals(other.getHandleContent()))
            && (this.getHandler() == null ? other.getHandler() == null : this.getHandler().equals(other.getHandler()))
            && (this.getHandlerTime() == null ? other.getHandlerTime() == null : this.getHandlerTime().equals(other.getHandlerTime()))
            && (this.getHandleResult() == null ? other.getHandleResult() == null : this.getHandleResult().equals(other.getHandleResult()))
            && (this.getDegree() == null ? other.getDegree() == null : this.getDegree().equals(other.getDegree()))
            && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null : this.getDeleteStatus().equals(other.getDeleteStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getGeneral() == null) ? 0 : getGeneral().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getEmergency() == null) ? 0 : getEmergency().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRequest() == null) ? 0 : getRequest().hashCode());
        result = prime * result + ((getCreater() == null) ? 0 : getCreater().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getHandleContent() == null) ? 0 : getHandleContent().hashCode());
        result = prime * result + ((getHandler() == null) ? 0 : getHandler().hashCode());
        result = prime * result + ((getHandlerTime() == null) ? 0 : getHandlerTime().hashCode());
        result = prime * result + ((getHandleResult() == null) ? 0 : getHandleResult().hashCode());
        result = prime * result + ((getDegree() == null) ? 0 : getDegree().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", general=").append(general);
        sb.append(", customerId=").append(customerId);
        sb.append(", emergency=").append(emergency);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", status=").append(status);
        sb.append(", request=").append(request);
        sb.append(", creater=").append(creater);
        sb.append(", createDate=").append(createDate);
        sb.append(", handleContent=").append(handleContent);
        sb.append(", handler=").append(handler);
        sb.append(", handlerTime=").append(handlerTime);
        sb.append(", handleResult=").append(handleResult);
        sb.append(", degree=").append(degree);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}