package edu.hw11.task2;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeBehaviorOfExistingClass {
    @Test
    @SneakyThrows
    public void shouldCallOurFunction(){
        int r = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum")
                .and(isDeclaredBy(ArithmeticUtils.class)
                    .and(returns(int.class))))
            .intercept(MethodDelegation.to(ArithmeticUtilsChanged.class))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .newInstance()
            .sum(3, 4);

        assertEquals(3 * 4 ,r);
    }
}
