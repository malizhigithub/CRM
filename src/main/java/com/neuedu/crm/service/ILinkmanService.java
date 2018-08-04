package com.neuedu.crm.service;

import java.util.List;
import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.LinkmanExample;
import com.neuedu.crm.pojo.User;
/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface ILinkmanService {

	/**
	 * 根据linkmanExample统计条数
	 * @author malizhi
	 * @param linkmanExample
	 * @return long
	 * @version 1.0
	 * @exception Nothing
	 */
	public long countByLinkmanExample(LinkmanExample linkmanExample);

	/**
	 * 根据linkmanExample删除Linkman
	 * @author malizhi
	 * @param linkmanExample
	 * @return boolean
	 * @version 1.0
	 * @exception Nothing
	 */
    public boolean deleteByLinkmanExample(LinkmanExample linkmanExample);

    /**
     * 根据主键id删除Linkman
     * @author malizhi
     * @param id
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean deleteLinkmanByPrimaryKey(Integer id);

    /**
     * 描述：插入一条Linkman数据，如果参数linkman中字段为空，代表此字段插入为空
     * @author MaLizhi
     * @date 2018/07/24
     * @version 1.0
     * @param linkman
     * @return boolean
     * @since 1.8
     *
     */
    public boolean insertLinkman(Linkman linkman);

    /**
     * 插入一条Linkman数据，如果参数linkman中字段为空，代表此字段为数据库默认值
     * @author malizhi
     * @param linkman
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean insertLinkmanSelective(Linkman linkman);

    /**
     * 描述：根据linkmanExample查找Linkman
     * @author MaLizhi
     * @date 2018/07/24
     * @version 1.0
     * @param linkmanExample
     * @return List<Linkman>
     * @since 1.8
     *
     */
    public List<Linkman> selectByLinkmanExample(LinkmanExample linkmanExample);

    /**
     * 
     * 描述：
     * @author MaLizhi
     * @date 2018/07/24
     * @version 1.0
     * @param id
     * @return Linkman
     * @since 1.8
     *
     */
    public Linkman selectLinkmanByPrimaryKey(Integer id);

    /**
     * 更新Linkman，参数linkman中，如有有字段为空，代表不更新此字段，linkmanExample参数为where条件
     * @author malizhi
     * @param linkman
     * @param linkmanExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateByLinkmanExampleSelective(Linkman linkman, LinkmanExample linkmanExample);

    /**
     * 更新Linkman，参数linkman中，如有有字段为空，代表对应也更新为空，linkmanExample参数为where条件
     * @author malizhi
     * @param linkman
     * @param linkmanExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateByLinkmanExample(Linkman linkman, LinkmanExample linkmanExample);

    /**
     * 根据主键更新Linkman，参数linkman中，如果字段为空，代表不更新此字段
     * @author malizhi
     * @param linkman
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateLinkmanByPrimaryKeySelective(Linkman linkman);

    /**
     * 根据主键更新Linkman，参数linkman中，如果字段为空，代表相应字段也更新为空
     * @author malizhi
     * @param linkman
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateLinkmanByPrimaryKey(Linkman linkman);

    /**
     * 
     * 描述：根据用户ID，查找该用户下的所有联系人信息
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param user
     * @return List<Linkman>
     * @since 1.8
     *
     */
    public List<Linkman> selectLinkmanByUser(User user);
	
}
