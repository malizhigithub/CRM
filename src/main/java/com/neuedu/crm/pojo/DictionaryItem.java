package com.neuedu.crm.pojo;

import java.io.Serializable;

/**
 * dictionary_item
 * @author 
 */
public class DictionaryItem implements Serializable {
    private Integer id;

    /**
     * 字典类型id
     */
    private Integer typeId;

    /**
     * 该数据字典所属的数据字典类型
     */
    private DictionaryType dictionaryType;
    
    /**
     * 字典名称
     */
    private String name;

    /**
     * 字典状态 0：只可读   1：可以进行curd
     */
    private Integer status;
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    public DictionaryType getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(DictionaryType dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dictionaryType == null) ? 0 : dictionaryType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((typeId == null) ? 0 : typeId.hashCode());
        return result;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        DictionaryItem other = (DictionaryItem) obj;
        if (dictionaryType == null) {
            if (other.dictionaryType != null)
            {
                return false;
            }
        } else if (!dictionaryType.equals(other.dictionaryType))
        {
            return false;
        }
        if (id == null) {
            if (other.id != null)
            {
                return false;
            }
        } else if (!id.equals(other.id))
        {
            return false;
        }
        if (name == null) {
            if (other.name != null)
            {
                return false;
            }
        } else if (!name.equals(other.name))
        {
            return false;
        }
        if (status == null) {
            if (other.status != null)
            {
                return false;
            }
        } else if (!status.equals(other.status))
        {
            return false;
        }
        if (typeId == null) {
            if (other.typeId != null)
            {
                return false;
            }
        } else if (!typeId.equals(other.typeId))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DictionaryItem [id=" + id + ", typeId=" + typeId + ", dictionaryType=" + dictionaryType + ", name="
                + name + ", status=" + status + "]";
    }

    
}