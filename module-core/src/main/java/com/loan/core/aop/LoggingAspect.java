package com.loan.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.loan..controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            log.info("Method [{}.{}] called with arg [{}], took {}ms", className, methodName, joinPoint.getArgs(),
              endTime - startTime);
            return result;
        } catch (Exception e) {
            log.error("aop log - error: {}", e.getMessage());
            throw e;
        }
    }
}
