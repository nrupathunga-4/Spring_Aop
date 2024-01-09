package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.entity.UserLogin;
import com.example.demo.exception.GlobalExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {

	@Pointcut("@annotation(com.example.demo.annotation.CustomAnnotation)")
	public void loggingPointCut(){ 
		
	}
	
	@Before("loggingPointCut()")
	public void before(JoinPoint joinPoint)
	{
		log.info("Before Method Invoked ::"+joinPoint.getSignature());
	}
	
	@AfterReturning("loggingPointCut() && args(user)")
	public void after(JoinPoint joinPoint,User user)
	{
		log.info("After Method Is Invoked::"+user);
	}
	
	@AfterThrowing("loggingPointCut() &&  args(ex)")
	public void after(JoinPoint joinPoint,GlobalExceptionHandler ex)
	{
 	log.info("After Throwing Method Is Invoked::"+ex);
    }
	
	@Around("loggingPointCut() && args(user)")
	public Object around(ProceedingJoinPoint joinPoint,User user) throws Throwable
	{
		log.info("Before Method Is Invoked::"+joinPoint.getArgs()[0]);
		Object object = joinPoint.proceed();
		
		log.info("After Method Is Invoked::"+joinPoint.getArgs()[0]);
		return object;
	}
}
