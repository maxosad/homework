package edu.hw10.task2.prov;

import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {
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
    public void serve() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) {
        ExampleAspect m = new ExampleAspect();
        try {
            m.serve();
        } catch (Exception ignore) { }
    }
}
