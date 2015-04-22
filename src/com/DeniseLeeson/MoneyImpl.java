package com.DeniseLeeson;

public class MoneyImpl extends Money {
    private Double value;
    private String description;
    private Double total = Constants.ZERO_AMOUNT;
    private int count;


    public MoneyImpl(String description, Double value) {
        setDescription(description);
        setValue(value);
        addTotal(value);
        setCount(0);
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

    @Override
    public void setCount(int count) {
        this.count = count;
        setTotal(count * getValue());
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder();
        sb.append("Description: ")
                .append(getDescription())
                .append(Constants.SPACER)
                .append("Value: ")
                .append(getValue().toString())
                .append(Constants.SPACER)
                .append("Count: ")
                .append(getCount())
                .append(Constants.SPACER)
                .append("Total: ")
                .append(getTotal().toString());

        return sb.toString();
    }
}
