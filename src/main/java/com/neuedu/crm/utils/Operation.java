package com.neuedu.crm.utils;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**	自定义注解类  定义controller方法的中文含义
 *  @Target({METHOD,TYPE}) 表示这个注解可以用用在类/接口上，还可以用在方法上
	@Retention(RetentionPolicy.RUNTIME) 表示这是一个运行时注解，即运行起来之后，才获取注解中的相关信息，而不像基本注解如@Override 那种不用运行，在编译时eclipse就可以进行相关工作的编译时注解。
	@Inherited 表示这个注解可以被子类继承
	@Documented 表示当执行javadoc的时候，本注解会生成相关文档
 * @author huangqingwen
 *
 */

@Target({METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Operation {
	
	String name();
}
