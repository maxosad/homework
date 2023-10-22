package edu.hw3.Task6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    private PriorityQueue<Stock> priorityQueue;

    public StockMarketImpl() {
        Comparator<Stock> cmp = (o1, o2) -> o2.getPrice().compareTo(o1.getPrice());
        this.priorityQueue = new PriorityQueue<>(cmp);
    }

    @Override
    public void add(Stock stock) {
        priorityQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        priorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        System.out.println(new Stock("1", 1).equals(new Stock("1", 1)));
    }
}
