package br.kuhn.dev.springboot._core.security.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Aspect
@Slf4j
@Component
public class SecurityLogger {

	@Around("execution(* br.kuhn.dev.springboot._core.security.controller.*.*(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

		String method = theProceedingJoinPoint.getSignature().toShortString();
		log.warn("\n=====>>> Executing @Around on method: " + method);

		Object result = null;

		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			log.warn(e.getMessage());

			throw e;
		}

		return result;
	}
}
