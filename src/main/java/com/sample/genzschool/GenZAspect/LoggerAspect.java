package com.sample.genzschool.GenZAspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
@Aspect
public class LoggerAspect {

   @Around("execution(* com.sample.genzschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint)throws Throwable{
       log.info(joinPoint.getSignature().toString() + " method execution start .. ");
       Instant start = Instant.now();
       Object returnObj = joinPoint.proceed();
       Instant end = Instant.now();
       long timeElasped = Duration.between(start, end).toMillis();
       log.info("Time taken for " + joinPoint.getSignature().toString() + " method execution : " + timeElasped);
       log.info(joinPoint.getSignature().toString() + " method execution end .. ");
       return returnObj;
   }

   @AfterThrowing(value = "execution(* com.sample.genzschool..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex){
       log.error(joinPoint.getSignature().toString()+ " has thrown exception due to " + ex.getMessage());
   }
}
