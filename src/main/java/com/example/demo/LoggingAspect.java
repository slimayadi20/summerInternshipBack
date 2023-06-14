package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    /*  @After("execution(* com.example.demo.services.*.add*(..))")
      public void logMethodCalls(){
          log.info("Successfully Added");
      }
  */
   /* @Around("execution(* com.example.demo.services.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }*/
    /*@Around("execution(* com.example.exam_assurance.service.*.get*(..))")
    public void logMethodCalls(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info(elapsedTime + "teest");*/
}
/* @Before("execution(* com.example.demo.Services.*.*(..))")
    public void logMethodCalls(JoinPoint joinPoint){
        String methodSignature = joinPoint.getSignature().toString();
        log.info(methodSignature + "At :  " + LocalDateTime.now());
    }*/
/*

 */

