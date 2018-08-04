package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * permission
 * @author 
 */
public class Permission implements Serializable {
    /**
     * 权限id
     */
    private Integer id;

    /**
     * 上级id
     */
    private Integer pid;
    
    /**
     * 父级权限
     */
    private Permission parentPermission;
    
    /**
     * 子级权限
     */
    private List<Permission> childPermission;
    
    

    public Permission getParentPermission() {
		return parentPermission;
	}

	public void setParentPermission(Permission parentPermission) {
		this.parentPermission = parentPermission;
	}

	public List<Permission> getChildPermission() {
		return childPermission;
	}

	public void setChildPermission(List<Permission> childPermission) {
		this.childPermission = childPermission;
	}

	/**
     * 概要类型：0为菜单 1为功能
     */
    private Integer type;

    /**
     * 权限标题
     */
    private String title;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限对应可使用的url
     */
    private String url;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限状态：0 为正常 1为禁用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
        Permission other = (Permission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
	public String toString() {
		return "Permission [id=" + id + ", pid=" + pid + ", parentPermission=" + parentPermission + ", childPermission="
				+ childPermission + ", type=" + type + ", title=" + title + ", description=" + description + ", url="
				+ url + ", code=" + code + ", status=" + status + "]";
	}
    
    
}