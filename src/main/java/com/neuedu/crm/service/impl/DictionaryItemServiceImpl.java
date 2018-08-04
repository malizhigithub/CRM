package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.DictionaryItemMapper;
import com.neuedu.crm.pojo.DictionaryItem;
import com.neuedu.crm.pojo.DictionaryItemExample;
import com.neuedu.crm.service.IDictionaryItemService;
/**
 * 
 * @author HuangWanzong
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DictionaryItemServiceImpl implements IDictionaryItemService{

    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;
    
    @Override
    public long countByDictionaryItemExample(DictionaryItemExample dictionaryItemExample) {
        return dictionaryItemMapper.countByExample(dictionaryItemExample);
    }

    @Override
    public boolean deleteByDictionaryItemExample(DictionaryItemExample dictionaryItemExample) {
        int res = 0;
        res = dictionaryItemMapper.deleteByExample(dictionaryItemExample);
        
        if(res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        int res = 0;
        res = dictionaryItemMapper.deleteByPrimaryKey(id);
        if(res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertDictionaryItem(DictionaryItem dictionaryItem) {
        int res = 0;
        try {
            //如果typeId不存在，尝试从item中的DictionaryType对象提取id
            if(dictionaryItem.getTypeId() == null) {
                dictionaryItem.setTypeId(dictionaryItem.getDictionaryType().getId());
            }
            res = dictionaryItemMapper.insert(dictionaryItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(res > 0) {
            return true;
        }
            
        return false;
    }

    @Override
    public boolean insertDictionaryItemSelective(DictionaryItem dictionaryItem) {
        int res = 0;
        try {
            //如果typeId不存在，尝试从item中的DictionaryType对象提取id
            if(dictionaryItem.getTypeId() == null) {
                dictionaryItem.setTypeId(dictionaryItem.getDictionaryType().getId());
            }
            res = dictionaryItemMapper.insertSelective(dictionaryItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<DictionaryItem> selectByDictionaryItemExample(DictionaryItemExample dictionaryItemExample) {
        return dictionaryItemMapper.selectByExample(dictionaryItemExample);
    }

    @Override
    public DictionaryItem selectDictionaryItemByPrimaryKey(Integer id) {
        return dictionaryItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByDictionaryItemExample(DictionaryItem dictionaryItem,
            DictionaryItemExample dictionaryItemExample) {
        int res = 0;
        res = dictionaryItemMapper.updateByExample(dictionaryItem, dictionaryItemExample);
        if(res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateByDictionaryItemExampleSelective(DictionaryItem dictionaryItem,
            DictionaryItemExample dictionaryItemExample) {
        int res = 0;
        res = dictionaryItemMapper.updateByExampleSelective(dictionaryItem, dictionaryItemExample);
        if(res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDictionaryItemByPrimaryKeySelective(DictionaryItem dictionaryItem) {
        int res = 0;
        res = dictionaryItemMapper.updateByPrimaryKeySelective(dictionaryItem);
        if(res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDictionaryItemByPrimaryKey(DictionaryItem dictionaryItem) {
        int res = 0;
        res = dictionaryItemMapper.updateByPrimaryKey(dictionaryItem);
        if(res > 0) {
            return true;
        }
        return false;
    }

}
