package com.portfolio.strategy;


import com.portfolio.stock.PoolOfStocks;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategy {
    PoolOfStocks listOfStock;
    int MAX_SHARES = 10;
    Double optimizedMetric;
    List<Integer> optimizedCombination;
    double currentBestMetricInitialValue = Double.MAX_VALUE;
    private double Return =0d;
    Strategy(PoolOfStocks poolOfStocks){
        listOfStock = poolOfStocks;
        optimizedMetric = currentBestMetricInitialValue;
    }

    abstract double getMetric();
    abstract boolean isBetter(double temp, double currentBest);

    public double evaluate(){
        //listOfStock.getPriceData();

        long casenum = Strategy.powLong(MAX_SHARES+1,listOfStock.getNumberOfStocks());
        System.out.println(this.getClass().getName());
        System.out.println("search "+casenum + " cases");
        double currentBestMetric=currentBestMetricInitialValue;
        List<Integer> tempCombination=null;
        for(long i = 1;i<casenum;i++){
            List<Integer> combination;
            combination = this.getNumberOfSharesCombination(i);
            if(this.isLinearIndependent(combination)){
                if (listOfStock.setNumberOfShares(combination)){
                    listOfStock.calculatePrice();
                    double temp;
                    temp = getMetric();

                    if(isBetter(temp,currentBestMetric)){
                        currentBestMetric = temp;
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
            optimizedMetric=currentBestMetric;
            listOfStock.setNumberOfShares(optimizedCombination);
            listOfStock.calculatePrice();
            return currentBestMetric;
        }

        return -1;
    }

    public List<Integer> getNumberOfSharesCombination(long counter){
        int numberOfStocks = listOfStock.getNumberOfStocks();
        List<Integer> numberOfShares = new ArrayList<>();
        for(int i = 0 ; i< numberOfStocks; i++){
            numberOfShares.add((int) counter%(MAX_SHARES+1));
            counter/= MAX_SHARES+1;
        }
        return numberOfShares;
    }
    public boolean isLinearIndependent(List<Integer> combination){
        for(int i=2; i<MAX_SHARES+1;i++){
            boolean isDependent = true;
            for (int j = 0; j< combination.size();j++){
                if (combination.get(j)%i >0){
                    isDependent = false;
                    break;
                }
            }
            if(isDependent){
                return false;
            }
        }
        return true;
    }

    public Double getOptimizedMetric() {
        return optimizedMetric;
    }

    public List<Integer> getOptimizedCombination() {
        return optimizedCombination;
    }

    public static long powLong(int a, int positiveOnly){
        if(positiveOnly==0){
            return 1;
        } else {
            return  a*powLong(a,positiveOnly-1);
        }
    }
    public double getReturn(){
        return listOfStock.calculateReturn();
    }
}
