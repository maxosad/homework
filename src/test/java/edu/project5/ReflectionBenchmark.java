package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    private static final String METHOD_NAME = "name";
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaFunction;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    @Setup
    @SneakyThrows
    public void setup()  {
        student = new Student("Alexander", "Biryukov");

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        final MethodType methodTypeString = MethodType.methodType(String.class);
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME, methodTypeString);

        CallSite callSite = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            methodHandle,
            MethodType.methodType(String.class, Student.class)
        );

        lambdaFunction = ((Function<Student, String>) callSite.getTarget().invokeExact());

        method = Student.class.getDeclaredMethod(METHOD_NAME);
        method.setAccessible(true);
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        bh.consume(student.name());
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        bh.consume(method.invoke(student));
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        bh.consume((String) methodHandle.invoke(student));
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        bh.consume(lambdaFunction.apply(student));
    }
}
