package edu.hw10.task2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

    @Around("@annotation(Cache)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        joinPoint.
        System.out.println("qqwerw");
        var res = joinPoint.proceed();
        return res;
    }

}
