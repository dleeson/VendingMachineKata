package com.DeniseLeeson;

public class Change {
    private int dollarCount = 0;
    private int quarterCount = 0;
    private int dimeCount = 0;
    private int nickelCount = 0;
    private Double moneyLeft = 0.00;

    public Change(Double moneyLeft, int dollarCount, int quarterCount, int dimeCount, int nickelCount) {
        this.moneyLeft = moneyLeft;
        this.dollarCount = dollarCount;
        this.quarterCount = quarterCount;
        this.dimeCount = dimeCount;
        this.nickelCount = nickelCount;
    }

    public void calculateChange(){
        Double remainder = 0.00;
        if (moneyLeft > .99) {
            remainder =  (moneyLeft / Constants.DOLLAR_AMOUNT);
            dollarCount = remainder.intValue();
            moneyLeft -= dollarCount * Constants.DOLLAR_AMOUNT;

        }

        if (moneyLeft > .25){
            remainder = moneyLeft / Constants.QUARTER_AMOUNT;
            quarterCount = remainder.intValue();
            moneyLeft -= quarterCount * Constants.QUARTER_AMOUNT;
        }

        if (moneyLeft > .10) {
            remainder = moneyLeft / Constants.DIME_AMOUNT;
            dimeCount = remainder.intValue();
            moneyLeft -= roundDouble(dimeCount * Constants.DIME_AMOUNT);
        }

        if (roundDouble(moneyLeft) > 0.00) {
            nickelCount = 1;
        }
    }

    public int getDollarCount() {
        return dollarCount;
    }

    public void setDollarCount(int dollarCount) {
        this.dollarCount = dollarCount;
    }

    public int getQuarterCount() {
        return quarterCount;
    }

    public void setQuarterCount(int quarterCount) {
        this.quarterCount = quarterCount;
    }

    public int getDimeCount() {
        return dimeCount;
    }

    public void setDimeCount(int dimeCount) {
        this.dimeCount = dimeCount;
    }

    public int getNickelCount() {
        return nickelCount;
    }

    public void setNickelCount(int nickelCount) {
        this.nickelCount = nickelCount;
    }

    public Double getMoneyLeft() {
        return moneyLeft;
    }

    public void setMoneyLeft(Double moneyLeft) {
        this.moneyLeft = moneyLeft;
    }


    private double roundDouble(double amount)
    {
        return (Math.round(amount * 100.00) / 100.00);
    }

}
