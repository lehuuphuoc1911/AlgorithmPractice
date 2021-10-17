package com.portfolio.strategy;


import com.portfolio.stock.PoolOfStocks;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategy {
    // list of stocks in the pool stock
    PoolOfStocks listOfStock;
    // maximum number of shares of each stock in the pool
    int MAX_SHARES = 10;
    // in order to select the best combination, we have to create a metric to compare each,
    // optimized combination will have the biggest or smallest value
    Double optimizedMetric;
    List<Integer> optimizedCombination;
    //depend on the strategy need to find max or min of something
    double currentBestMetricInitialValue;


    Strategy(PoolOfStocks poolOfStocks){
        listOfStock = poolOfStocks;
    }

    abstract double getMetric();
    abstract boolean isBetter(double temp, double currentBest);

    public double evaluate(){
        listOfStock.getPriceData();

        long caseNum = Strategy.powLong(MAX_SHARES+1,listOfStock.getNumberOfStocks());
        System.out.println(this.getClass().getName());
        System.out.println("search "+caseNum + " cases");
        double currentBestMetric=currentBestMetricInitialValue;
        List<Integer> tempCombination=null;
        for(long i = 1;i<caseNum;i++){
            List<Integer> combination;
            combination = Strategy.getCombination(i,listOfStock.getNumberOfStocks(), this.MAX_SHARES);
            if(Strategy.isLinearIndependent(combination)){
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
            settle(tempCombination,currentBestMetric);
            return currentBestMetric;
        }
        return -1;
    }

    /*
    if counter = 1234, digits = 6, base = 10   -> return [4, 3, 2, 1, 0, 0]
    if counter = 0x123B, digits = 5, base = 16 -> return [11, 3, 2, 1, 0]
     */
    public static List<Integer> getCombination(long counter, int digits, int base){

        List<Integer> numberOfShares = new ArrayList<>();
        for(int i = 0 ; i< digits; i++){
            numberOfShares.add((int) counter%(base+1));
            counter/= base+1;
        }
        return numberOfShares;
    }

    /*
    input = [x1,x2,...]; x is Integer
    return false if set of integers k>1, y1, y1 ... ;which k[y1,y2,...]=[x1,x2,...]
    otherwise return true
     */
    public static boolean isLinearIndependent(List<Integer> combination){
        int max = Strategy.getMax(combination);
        for(int i=2; i<max+1;i++){
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

    private static long powLong(int a, int positiveOnly){
        if(positiveOnly==0){
            return 1;
        } else {
            return  a*powLong(a,positiveOnly-1);
        }
    }
    public double getReturn(){
        return listOfStock.calculateReturn();
    }


    //
    private static int getMax(List<Integer> list){
        int max = 0;
        for (int m:list
        ) {
            if(m>max){
                max = m;
            }
        }
        return max;
    }

    private void settle(List<Integer> tempCombination, double currentBestMetric){
        optimizedCombination=tempCombination;
        optimizedMetric=currentBestMetric;
        listOfStock.setNumberOfShares(optimizedCombination);
        listOfStock.calculatePrice();
    }
}
