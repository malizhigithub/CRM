package com.neuedu.crm.mapper;

import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * DAO公共基类，由MybatisGenerator自动生成请勿修改
 * @author MybatisGenerator
 * @param <Model> The Model Class
 * @param <PK> The Primary Key Class
 * @param <E> The Example Class
 */
public interface MyBatisBaseDao<Model, PK extends Serializable, E> {
    /**
     * 描述：根据example统计符合条件的数据量
     * @author MybatisGenerator
     * @version 1.0
     * @param example
     * @return long
     * @since 1.8
     *
     */
    long countByExample(E example);

    /**
     * 描述：根据example删除数据
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param example
     * @return int
     * @since 1.8
     *
     */
    int deleteByExample(E example);

    /**
     * 描述：根据主键删除数据
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param id
     * @return int
     * @since 1.8
     *
     */
    int deleteByPrimaryKey(PK id);

    /**
     * 描述：插入数据（若数据为空则设对应的字段为空)
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param record
     * @return int
     * @since 1.8
     *
     */
    int insert(Model record);

    /**
     * 描述：插入数据，若属性不存在则对应的字段设为数据库默认值
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param record
     * @return int
     * @since 1.8
     *
     */
    int insertSelective(Model record);

    /**
     * 描述：查询数据
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param example
     * @return List<Model>
     * @since 1.8
     *
     */
    List<Model> selectByExample(E example);

    /**
     * 描述：根据主键查询数据
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param id
     * @return Model
     * @since 1.8
     *
     */
    Model selectByPrimaryKey(PK id);

    /**
     * 描述：更新数据,若属性为空则对应字段不更新
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param record
     * @param example
     * @return int
     * @since 1.8
     *
     */
    int updateByExampleSelective(@Param("record") Model record, @Param("example") E example);

    /**
     * 
     * 描述：更新数据，若属性为空则对应字段也设置为空
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param record
     * @param example
     * @return int
     * @since 1.8
     *
     */
    int updateByExample(@Param("record") Model record, @Param("example") E example);

    /**
     * 描述：根据主键更新数据,若属性为空则对应字段不更新
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param record
     * @return int
     * @since 1.8
     *
     */
    int updateByPrimaryKeySelective(Model record);

    /**
     * 描述：根据主键更新数据，若属性为空则对应字段也设置为空
     * @author MybatisGenerator
     * @date 2018/07/24
     * @version 1.0
     * @param record
     * @return int
     * @since 1.8
     *
     */
    int updateByPrimaryKey(Model record);
}