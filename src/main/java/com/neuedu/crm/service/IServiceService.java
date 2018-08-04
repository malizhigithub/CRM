package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.Service;
import com.neuedu.crm.pojo.ServiceExample;
/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface IServiceService {
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByServiceExample(ServiceExample serviceExample);

	/**
	 * 
	 * 描述：按照Example 删除Service
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByServiceExample(ServiceExample serviceExample);

	/**
	 * 
	 * 描述：按照Service主键id删除Service
	 * @author wujunyou
	 * @version 1.0
	 * @param id 数据字典id
	 * @return boolean 删除结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：插入一条Service数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param service  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertService(Service service);
	
	/**
	 * 
	 * 描述：插入一条Service数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param service 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(Service service);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceExample 查询条件
	 * @return List<Service> 含Service的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<Service> selectByServiceExample(ServiceExample serviceExample);
	
	/**
	 * 
	 * 描述：按照Service 的id 查找 Service
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return Service 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public Service selectServiceByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新Service
	 * @author wujunyou
	 * @version 1.0
	 * @param service 对象中若有空则更新字段为null
	 * @param serviceExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByServiceExample(Service service, ServiceExample serviceExample);
	
	/**
	 * 
	 * 描述：更新Service 
	 * @author wujunyou
	 * @version 1.0
	 * @param service 对象中若有空则不会更新此字段
	 * @param serviceExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByServiceExampleSelective(Service service, ServiceExample serviceExample);
		
	/**
	 * 
	 * 描述：按照Service id 更新Service
	 * @author wujunyou
	 * @version 1.0
	 * @param service 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateServiceByPrimaryKeySelective(Service service);
	
	/**
	 * 
	 * 描述：按照Service id 更新Service
	 * @author wujunyou
	 * @version 1.0
	 * @param service 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateServiceByPrimaryKey(Service service);

    /**
     * 描述：批量删除服务
     * @author wanghaoyu
     * @version 1.0
     * @param ids
     * @return 
     * @exception Nothing
     * @since 1.8
     * 
     */
    public boolean deleteServicesByPrimaryKey(String ids);	
}
