package ru.gb.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NameLoggerAspect {

    @Pointcut("execution(* ru.gb.springdemo.ReaderService.*(..))")
    public void myServiceBeanMethodsPointcut() {
    }

    @Around("myServiceBeanMethodsPointcut()")
  public Object aroundMyServiceBeanMethodsPointcut(ProceedingJoinPoint joinPoint) {
    try {
      //Object proceed = joinPoint.proceed();
        System.out.println("It's work!!");
      return "proceed";
    } catch (Throwable e) {
      return "exception was thrown: [" + e.getMessage() + "]";
    }
  }

}
