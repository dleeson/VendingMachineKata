package com.DeniseLeeson;


public class MoneyBagImpl<Money> extends MoneyBag<Money> {
    @Override
    public Double getDominationTotal(String description) {
        return ((MoneyImpl) this.getMoney(description)).getTotal();
    }

    @Override
    public Double getTotal() {
        Double totalMoney = 0.00;
        for (Money money : this) {
            totalMoney += ((MoneyImpl)money).getTotal();
        }
        return totalMoney;
    }

    @Override
    public Money getMoney(String description) {
        Money money = null;
        for (int indx=0; indx < this.size(); indx ++){

            if (((MoneyImpl)this.get(indx)).getDescription().equals(description)) {
               money = this.get(indx);
           }
        }
        return money;
    }
}
