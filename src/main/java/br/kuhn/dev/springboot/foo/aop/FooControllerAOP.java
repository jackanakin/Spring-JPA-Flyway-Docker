package br.kuhn.dev.springboot.foo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class FooControllerAOP {
    
    @Around("execution(* br.kuhn.dev.springboot.foo.controller.*.*(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		String method = theProceedingJoinPoint.getSignature().toShortString();
        log.warn("\n=====>>> Executing @Around on method: " + method);

		long begin = System.currentTimeMillis();
		
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			log.warn(e.getMessage());

			throw e;
		}
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		log.warn("\n=====> Duration: " + duration / 1000.0 + " seconds");
		
		return result;
	}
}
