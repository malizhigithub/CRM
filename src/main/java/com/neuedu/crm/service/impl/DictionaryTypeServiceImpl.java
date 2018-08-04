package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.DictionaryItemMapper;
import com.neuedu.crm.mapper.DictionaryTypeMapper;
import com.neuedu.crm.pojo.DictionaryItemExample;
import com.neuedu.crm.pojo.DictionaryType;
import com.neuedu.crm.pojo.DictionaryTypeExample;
import com.neuedu.crm.service.IDictionaryTypeService;


/**
 * 
 * @author huangwanzong
 *
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryTypeServiceImpl implements IDictionaryTypeService {

    @Autowired
    private DictionaryTypeMapper dictionaryTypeMapper;

    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;
    
    @Override
    public long countByDictionaryTypeExample(DictionaryTypeExample dictionaryTypeExample) {
        return dictionaryTypeMapper.countByExample(dictionaryTypeExample);
    }

    @Override
    public boolean deleteByDictionaryTypeExample(DictionaryTypeExample dictionaryTypeExample) {
        if(dictionaryTypeMapper.deleteByExample(dictionaryTypeExample)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        if(dictionaryTypeMapper.deleteByPrimaryKey(id)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertDictionaryType(DictionaryType dictionaryType) {
        int res = 0;
        try {
            res = dictionaryTypeMapper.insert(dictionaryType);
        }catch (Exception e) {
            System.out.println("添加DictionaryType 时出现一个异常.");
            e.printStackTrace();
        }
        if(res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertDictionaryTypeSelective(DictionaryType dictionaryType) {
        int res = 0;
        try {
            res = dictionaryTypeMapper.insertSelective(dictionaryType);
        }catch (Exception e) {
            System.out.println("添加DictionaryType 时出现一个异常.");
            e.printStackTrace();
        }
        if(res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<DictionaryType> selectByDictionaryTypeExample(DictionaryTypeExample dictionaryTypeExample) {
        
        List<DictionaryType> list = dictionaryTypeMapper.selectByExample(dictionaryTypeExample);
        
        for(DictionaryType dictionaryType : list) {
            DictionaryItemExample example = new DictionaryItemExample();
            example.createCriteria().andTypeIdEqualTo(dictionaryType.getId());
            dictionaryType.setDictionaryItems(dictionaryItemMapper.selectByExample(example));
        }
        
        return list;
    }

    @Override
    public DictionaryType selectDictionaryTypeByPrimaryKey(Integer id) {
        DictionaryType dictionaryType = dictionaryTypeMapper.selectByPrimaryKey(id);
        DictionaryItemExample example = new DictionaryItemExample();
        example.createCriteria().andTypeIdEqualTo(dictionaryType.getId());
        dictionaryType.setDictionaryItems(dictionaryItemMapper.selectByExample(example));
        return dictionaryType;
    }

    @Override
    public boolean updateByDictionaryTypeExample(DictionaryType dictionaryType,
            DictionaryTypeExample dictionaryTypeExample) {
        if(dictionaryTypeMapper.updateByExample(dictionaryType, dictionaryTypeExample)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateByDictionaryTypeExampleSelective(DictionaryType dictionaryType,
            DictionaryTypeExample dictionaryTypeExample) {
        if(dictionaryTypeMapper.updateByExampleSelective(dictionaryType, dictionaryTypeExample)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDictionaryTypeByPrimaryKeySelective(DictionaryType dictionaryType) {
        if(dictionaryTypeMapper.updateByPrimaryKeySelective(dictionaryType)>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDictionaryTypeByPrimaryKey(DictionaryType dictionaryType) {
        if(dictionaryTypeMapper.updateByPrimaryKey(dictionaryType)>0) {
            return true;
        }
        return false;
    }

}
