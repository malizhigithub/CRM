package test.service;

import com.neuedu.crm.utils.Operation;

@Operation(name="sajdio")
public class TestAnno {
	
	@Operation(name="name")
	public void testMethodAnno(){
		
	}
	
	public static void main(String[] args) {
		Operation method = TestAnno.class.getAnnotation(Operation.class);
		java.lang.reflect.Method[] methods = TestAnno.class.getMethods();
		for (java.lang.reflect.Method method2 : methods) {
			System.out.println(method2.getName());
		}
		System.err.println(method.name());
	}
}
