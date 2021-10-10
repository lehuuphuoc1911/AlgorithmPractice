package com.portfolio.strategy;

import com.portfolio.stock.PoolOfStocks;

public class MaxRRStrategy extends Strategy{
    public MaxRRStrategy(PoolOfStocks poolOfStocks) {
        super(poolOfStocks);
    }

    @Override
    double evaluate() {
        return 0;
    }
}
