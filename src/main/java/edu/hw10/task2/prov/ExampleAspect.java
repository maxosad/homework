package edu.hw10.task2.prov;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {
    @Pointcut("@annotation(LogExecutionTime)")
    public void callAtMyServiceAnnotation() {
    }

    @Before("callAtMyServiceAnnotation()")
    public void beforeCallAt() {
        System.out.println("dibjfjbvljdnvlfdsjvldjbvfhoej");
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("egnfdsignjfdsl");

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    @LogExecutionTime
    public void serve() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {}
    }

    public static void main(String[] args) {
        ExampleAspect m = new ExampleAspect();

        m.serve();

    }
}
