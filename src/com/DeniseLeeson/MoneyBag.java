package com.DeniseLeeson;

import java.util.ArrayList;

public abstract class MoneyBag<Money> extends ArrayList<Money> {

    public abstract Double getDominationTotal(String description);

    public abstract Double getTotal();

    public abstract Money getMoney(String description);

}
