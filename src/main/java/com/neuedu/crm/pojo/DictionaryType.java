package com.neuedu.crm.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * dictionary_type
 * @author 
 */
public class DictionaryType implements Serializable {
    private Integer id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 对于本数据字典的数据字典子项列表
     */
    private List<DictionaryItem> dictionaryItems;
    
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

    public List<DictionaryItem> getDictionaryItems() {
        return dictionaryItems;
    }

    public void setDictionaryItems(List<DictionaryItem> dictionaryItems) {
        this.dictionaryItems = dictionaryItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dictionaryItems == null) ? 0 : dictionaryItems.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        DictionaryType other = (DictionaryType) obj;
        if (dictionaryItems == null) {
            if (other.dictionaryItems != null)
            {
                return false;
            }
        } else if (!dictionaryItems.equals(other.dictionaryItems))
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
        return true;
    }

    @Override
    public String toString() {
        return "DictionaryType [id=" + id + ", name=" + name + ", dictionaryItems=" + dictionaryItems + "]";
    }

    


    
}