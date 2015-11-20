package com.flows.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.flows.entity.User;
import com.flows.security.realm.UserRealm.ShiroUser;

/**
 * 日志切面类
 * 
 * @author Wanggg
 *
 */
@Component
//@Aspect
public class LogAnnotationAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAnnotationAspect.class);

	// 定义切入点
	@Pointcut("execution(* com.flows.service.impl.*.*(..))")
	private void allMethod() {
	}

	// 针对指定的切入点表达式选择的切入点应用前置通知
	//@Before("allMethod()")
	public void before(JoinPoint call) {

		String className = call.getTarget().getClass().getName();
		String methodName = call.getSignature().getName();

		System.out.println("【注解-前置通知】:" + className + "类的" + methodName + "方法开始了");
	}

	// 访问命名切入点来应用后置通知
	//@AfterReturning("allMethod()")
	public void afterReturn() {
		System.out.println("【注解-后置通知】:方法正常结束了");
	}

	// 应用最终通知
	//@After("allMethod()")
	public void after() {
		System.out.println("【注解-最终通知】:不管方法有没有正常执行完成," + "一定会返回的");
	}

	// 应用异常抛出后通知
	//@AfterThrowing("allMethod()")
	public void afterThrowing() {
		System.out.println("【注解-异常抛出后通知】:方法执行时出异常了");
	}

	// 应用周围通知
	@Around("allMethod()")
	public Object doAround(ProceedingJoinPoint call) throws Throwable {
		//底层原理，通过ThreadLocal 获取subject,从前线程中获取subject
		Subject subject = SecurityUtils.getSubject();
		logger.info("subject principal is--->" + subject.getPrincipal());
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		if(shiroUser != null){
			User user = shiroUser.user;
			logger.info( "id---->" + user.getId() );
		}
		Object result = null;
		this.before(call);// 相当于前置通知
		try {
			long startTime = System.currentTimeMillis();
			result = call.proceed();
			this.afterReturn(); // 相当于后置通知
		} catch (Throwable e) {
			this.afterThrowing(); // 相当于异常抛出后通知
			throw e;
		} finally {
			this.after(); // 相当于最终通知
		}
		return result;
	}
}
