package com.portfolio.strategy;

import com.portfolio.stock.PoolOfStocks;

public class MaxReturnOverDrawDownStrategy extends Strategy{
    public MaxReturnOverDrawDownStrategy(PoolOfStocks poolOfStocks) {
        super(poolOfStocks);
        currentBestMetricInitialValue = -9999d;
        optimizedMetric = currentBestMetricInitialValue;
    }

    @Override
    public double getMetric() {
        return listOfStock.calculateReturn()/listOfStock.calculateDrawdownAverage();
    }

    @Override
    public boolean isBetter(double temp, double currentBest) {
        if(temp>currentBest){
            return true;
        }
        return false;
    }
}
