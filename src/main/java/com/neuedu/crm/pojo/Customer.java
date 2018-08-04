package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * customer
 * @author 
 */
public class Customer implements Serializable {
    /**
     * 客户编号
     */
    private Integer id;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 客户类别（数据字典）
     */
    private String type;

    /**
     * 客户等级（数据字典）
     */
    private String level;

    /**
     * 客户状态（数据字典）
     */
    private String status;

    /**
     * 客户信用度（数据字典）
     */
    private String credit;

    /**
     * 客户所在地区
     */
    private String area;

    /**
     * 公司详细地址
     */
    private String companyAddress;

    /**
     * 公司电话
     */
    private String companyPhone;

    /**
     * 邮政编码
     */
    private String postCode;

    /**
     * 传真地址
     */
    private String faxAddress;

    /**
     * 公司网站
     */
    private String companyWebsite;

    /**
     * 营业执照注册号
     */
    private String licenseNumber;

    /**
     * 法人
     */
    private String corporation;

    /**
     * 年营业额
     */
    private Double annualSale;

    /**
     * 开户银行
     */
    private String depositBank;

    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 地税登记号
     */
    private String landTaxNumber;

    /**
     * 国税登记号
     */
    private String nationalTaxNumber;

    /**
     * 客户来源（数据字典）
     */
    private String source;

    /**
     * 客户描述
     */
    private String description;

    /**
     * 客户成熟度（数据字典）
     */
    private String maturity;

    /**
     * 相关文档保存地址
     */
    private String document;

    /**
     * 删除状态 0为未删除 1为已删除
     */
    private Integer deleteStatus;

    /**
     * 客户所属的客户经理（外键）
     */
    private Integer managerId;
    private User manager;

    /**
     * 客户主要意向产品（外键）
     */
    private Integer productId;
    private Product product;
    /**
     * 创建人（经理）
     */
    private String creater;
    private User createrObject;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getFaxAddress() {
        return faxAddress;
    }

    public void setFaxAddress(String faxAddress) {
        this.faxAddress = faxAddress;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public Double getAnnualSale() {
        return annualSale;
    }

    public void setAnnualSale(Double annualSale) {
        this.annualSale = annualSale;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getLandTaxNumber() {
        return landTaxNumber;
    }

    public void setLandTaxNumber(String landTaxNumber) {
        this.landTaxNumber = landTaxNumber;
    }

    public String getNationalTaxNumber() {
        return nationalTaxNumber;
    }

    public void setNationalTaxNumber(String nationalTaxNumber) {
        this.nationalTaxNumber = nationalTaxNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCreater() {
        Integer createrId = null;
        try {
            createrId = Integer.valueOf(creater);
        }catch (Exception e) {
        }
        return createrId;
    }

    public void setCreater(Integer creater) {
        if(creater != null) {
            this.creater = creater.toString();
        }else {
            this.creater = null;
        }
        
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    
    
    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getCreaterObject() {
        return createrObject;
    }

    public void setCreaterObject(User createrObject) {
        this.createrObject = createrObject;
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
        Customer other = (Customer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCredit() == null ? other.getCredit() == null : this.getCredit().equals(other.getCredit()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getCompanyAddress() == null ? other.getCompanyAddress() == null : this.getCompanyAddress().equals(other.getCompanyAddress()))
            && (this.getCompanyPhone() == null ? other.getCompanyPhone() == null : this.getCompanyPhone().equals(other.getCompanyPhone()))
            && (this.getPostCode() == null ? other.getPostCode() == null : this.getPostCode().equals(other.getPostCode()))
            && (this.getFaxAddress() == null ? other.getFaxAddress() == null : this.getFaxAddress().equals(other.getFaxAddress()))
            && (this.getCompanyWebsite() == null ? other.getCompanyWebsite() == null : this.getCompanyWebsite().equals(other.getCompanyWebsite()))
            && (this.getLicenseNumber() == null ? other.getLicenseNumber() == null : this.getLicenseNumber().equals(other.getLicenseNumber()))
            && (this.getCorporation() == null ? other.getCorporation() == null : this.getCorporation().equals(other.getCorporation()))
            && (this.getAnnualSale() == null ? other.getAnnualSale() == null : this.getAnnualSale().equals(other.getAnnualSale()))
            && (this.getDepositBank() == null ? other.getDepositBank() == null : this.getDepositBank().equals(other.getDepositBank()))
            && (this.getBankAccount() == null ? other.getBankAccount() == null : this.getBankAccount().equals(other.getBankAccount()))
            && (this.getLandTaxNumber() == null ? other.getLandTaxNumber() == null : this.getLandTaxNumber().equals(other.getLandTaxNumber()))
            && (this.getNationalTaxNumber() == null ? other.getNationalTaxNumber() == null : this.getNationalTaxNumber().equals(other.getNationalTaxNumber()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getMaturity() == null ? other.getMaturity() == null : this.getMaturity().equals(other.getMaturity()))
            && (this.getDocument() == null ? other.getDocument() == null : this.getDocument().equals(other.getDocument()))
            && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null : this.getDeleteStatus().equals(other.getDeleteStatus()))
            && (this.getManagerId() == null ? other.getManagerId() == null : this.getManagerId().equals(other.getManagerId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getCreater() == null ? other.getCreater() == null : this.getCreater().equals(other.getCreater()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCredit() == null) ? 0 : getCredit().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getCompanyAddress() == null) ? 0 : getCompanyAddress().hashCode());
        result = prime * result + ((getCompanyPhone() == null) ? 0 : getCompanyPhone().hashCode());
        result = prime * result + ((getPostCode() == null) ? 0 : getPostCode().hashCode());
        result = prime * result + ((getFaxAddress() == null) ? 0 : getFaxAddress().hashCode());
        result = prime * result + ((getCompanyWebsite() == null) ? 0 : getCompanyWebsite().hashCode());
        result = prime * result + ((getLicenseNumber() == null) ? 0 : getLicenseNumber().hashCode());
        result = prime * result + ((getCorporation() == null) ? 0 : getCorporation().hashCode());
        result = prime * result + ((getAnnualSale() == null) ? 0 : getAnnualSale().hashCode());
        result = prime * result + ((getDepositBank() == null) ? 0 : getDepositBank().hashCode());
        result = prime * result + ((getBankAccount() == null) ? 0 : getBankAccount().hashCode());
        result = prime * result + ((getLandTaxNumber() == null) ? 0 : getLandTaxNumber().hashCode());
        result = prime * result + ((getNationalTaxNumber() == null) ? 0 : getNationalTaxNumber().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getMaturity() == null) ? 0 : getMaturity().hashCode());
        result = prime * result + ((getDocument() == null) ? 0 : getDocument().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getManagerId() == null) ? 0 : getManagerId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getCreater() == null) ? 0 : getCreater().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", level=").append(level);
        sb.append(", status=").append(status);
        sb.append(", credit=").append(credit);
        sb.append(", area=").append(area);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", companyPhone=").append(companyPhone);
        sb.append(", postCode=").append(postCode);
        sb.append(", faxAddress=").append(faxAddress);
        sb.append(", companyWebsite=").append(companyWebsite);
        sb.append(", licenseNumber=").append(licenseNumber);
        sb.append(", corporation=").append(corporation);
        sb.append(", annualSale=").append(annualSale);
        sb.append(", depositBank=").append(depositBank);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", landTaxNumber=").append(landTaxNumber);
        sb.append(", nationalTaxNumber=").append(nationalTaxNumber);
        sb.append(", source=").append(source);
        sb.append(", description=").append(description);
        sb.append(", maturity=").append(maturity);
        sb.append(", document=").append(document);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", managerId=").append(managerId);
        sb.append(", productId=").append(productId);
        sb.append(", creater=").append(creater);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}