package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.ServiceTransfer;
import com.neuedu.crm.pojo.ServiceTransferExample;
/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface IServiceTransferService {
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransferExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByServiceTransferExample(ServiceTransferExample serviceTransferExample);

	/**
	 * 
	 * 描述：按照Example 删除ServiceTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransferExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByServiceTransferExample(ServiceTransferExample serviceTransferExample);

	/**
	 * 
	 * 描述：按照ServiceTransfer主键id删除ServiceTransfer
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
	 * 描述：插入一条ServiceTransfer数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransfer  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertServiceTransfer(ServiceTransfer serviceTransfer);
	
	/**
	 * 
	 * 描述：插入一条ServiceTransfer数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransfer 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(ServiceTransfer serviceTransfer);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransferExample 查询条件
	 * @return List<ServiceTransfer> 含ServiceTransfer的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<ServiceTransfer> selectByServiceTransferExample(ServiceTransferExample serviceTransferExample);
	
	/**
	 * 
	 * 描述：按照ServiceTransfer 的id 查找 ServiceTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return ServiceTransfer 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public ServiceTransfer selectServiceTransferByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新ServiceTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransfer 对象中若有空则更新字段为null
	 * @param serviceTransferExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByServiceTransferExample(ServiceTransfer serviceTransfer, ServiceTransferExample serviceTransferExample);
	
	/**
	 * 
	 * 描述：更新ServiceTransfer 
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransfer 对象中若有空则不会更新此字段
	 * @param serviceTransferExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByServiceTransferExampleSelective(ServiceTransfer serviceTransfer, ServiceTransferExample serviceTransferExample);
		
	/**
	 * 
	 * 描述：按照ServiceTransfer id 更新ServiceTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransfer 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateServiceTransferByPrimaryKeySelective(ServiceTransfer serviceTransfer);
	
	/**
	 * 
	 * 描述：按照ServiceTransfer id 更新ServiceTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param serviceTransfer 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateServiceTransferByPrimaryKey(ServiceTransfer serviceTransfer);	
}
