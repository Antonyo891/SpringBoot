package ru.gb.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeLogAspect {
    @Pointcut("within(@ru.gb.demo.aspect.TimeLog *)")
    public void beansAnnotatedWith(){
    }
    @Pointcut("@annotation(ru.gb.demo.aspect.TimeLog)")
    public void methodAnnotatedWith(){
    }

    @Around("beansAnnotatedWith() || methodAnnotatedWith()")
    public Object timeLogAspect(ProceedingJoinPoint joinPoint) throws Throwable {
       long timeStart = System.currentTimeMillis();
       try {
           Object returnValue = joinPoint.proceed();
           long elapsedTime = System.currentTimeMillis()-timeStart;
           log.info("Class = {}, MethodName = {}, elapsedTime = {}", joinPoint.getTarget().getClass(),
                   joinPoint.getSignature().getName(), elapsedTime);
           return returnValue;
       } catch (Throwable e) {
           log.error("exception = [{}, {}]",e.getClass(), e.getMessage());
           throw e;
       }
    }
}
