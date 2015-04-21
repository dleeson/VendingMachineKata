package com.DeniseLeeson;


public abstract class Item {
    private Double cost;
    private String selector;
    private int count;

    public Item(String selector, Double cost, int count) {

        setSelector(selector);
        setCost(cost);
        setCount(count);
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getSelector() {return selector; }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
