package com.DeniseLeeson;


public abstract class Money {
    public abstract Double getValue();

    public abstract void setValue(Double value);

    public abstract String getDescription();

    public abstract void setDescription(String description);

    public abstract Double getTotal();

    public abstract void setTotal(Double total);

    public abstract void addTotal(Double value);

    public abstract void subtractTotal(Double value);

    public abstract void setCount(int count);

    public abstract int getCount();
}
