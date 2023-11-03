package edu.hw3.Task6;

public class Stock {
    private String name;
    private Integer price;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Stock)) {
            return false;
        }

        return name.equals(((Stock) obj).getName()) && price.equals(((Stock) obj).getPrice());
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 10 + price.hashCode();
    }
}
