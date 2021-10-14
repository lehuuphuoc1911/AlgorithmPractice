package com.portfolio.strategy;

import com.portfolio.stock.PoolOfStocks;

public class MinDrawdownStrategy extends Strategy{
    public MinDrawdownStrategy(PoolOfStocks poolOfStocks) {
        super(poolOfStocks);
    }



    @Override
    public  double getMetric(){
        return listOfStock.calculateDrawdownAverage();
    }

    @Override
    public boolean isBetter(double temp, double currentBest) {
        if(temp<currentBest){
            return true;
        }
        return false;
    }


}
/*
    @Override
    public double evaluate() {
        //listOfStock.getPriceData();

        long casenum = Strategy.powLong(MAX_SHARES+1,listOfStock.getNumberOfStocks());
        System.out.println("MinDrawdownStrategy");
        System.out.println("search "+casenum + " cases");
        double tempMetric=Long.MAX_VALUE;
        List<Integer> tempCombination=null;
        for(long i = 1;i<casenum;i++){
            List<Integer> combination;
            combination = this.getNumberOfSharesCombination(i);
            if(this.isLinearIndependent(combination)){
                if (listOfStock.setNumberOfShares(combination)){
                    listOfStock.calculatePrice();
                    listOfStock.calculateDrawdownAverage();
                    if(listOfStock.getDrawdownAverage()<tempMetric){
                        tempMetric = listOfStock.getDrawdownAverage();
                        tempCombination= combination;
                    }
                } else {
                    System.out.println("Array length error");
                    return -1;
                }
            }
        }
        if (tempCombination!=null){
            optimizedCombination=tempCombination;
            optimizedMetric=tempMetric;
            return tempMetric;
        }

        return -1;
    }

 */