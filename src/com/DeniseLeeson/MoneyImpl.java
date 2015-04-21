package com.DeniseLeeson;

public class MoneyImpl extends Money {
    private Double value;
    private String description;
    private Double total = 0.00;


    public MoneyImpl(String description, Double value) {
        setDescription(description);
        setValue(value);
        addTotal(value);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotal() {return total;}

    public void setTotal(Double total) {this.total = total;}

    public void addTotal(Double value) { setTotal(total += value);}

    public void subtractTotal(Double value) {setTotal(total -= value); }
}
