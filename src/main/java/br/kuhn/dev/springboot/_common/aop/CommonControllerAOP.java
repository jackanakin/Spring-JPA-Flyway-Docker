package br.kuhn.dev.springboot._common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.kuhn.dev.springboot._common.util.HttpServletRequestUtil;
import br.kuhn.dev.springboot._core.security.entity.User;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class CommonControllerAOP {

    @Around("execution(* br.kuhn.dev.springboot.*.controller.*.*(..))")
    public Object aroundControllers(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String method = theProceedingJoinPoint.getSignature().toShortString();
        StringBuilder incoming = new StringBuilder("User:")
                .append(user.getId())
                .append("@")
                .append(method)
                .append(":")
                .append(HttpServletRequestUtil.getIpAddr(request))
                .append(",")
                .append(request.getRequestURL().toString());

        for (Object arg : theProceedingJoinPoint.getArgs()) {
            incoming.append(arg.toString());
        }

        log.warn(incoming.toString());

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
        log.warn("Duration: " + duration / 1000.0 + " seconds");

        return result;
    }
}
