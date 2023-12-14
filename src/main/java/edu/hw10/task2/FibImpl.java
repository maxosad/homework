package edu.hw10.task2;

public class FibImpl implements FibCalculator{
    @Override
    @Cache(persist = true)
    public long fib(int number) {
        if (number == 1 || number == 2) {
            return 1;
        } else {
            return fib(number - 1) + fib(number - 2);
        }
    }
}
