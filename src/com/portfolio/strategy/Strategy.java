package com.portfolio.strategy;


import com.portfolio.stock.PoolOfStocks;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategy {
    PoolOfStocks listOfStock;
    int MAX_SHARES = 5;
    Strategy(PoolOfStocks poolOfStocks){
        listOfStock = poolOfStocks;
    }

    abstract double evaluate();

    List<Integer> getNumberOfSharesCombination(long counter){
        int numberOfStocks = listOfStock.getNumberOfStocks();
        List<Integer> numberOfShares = new ArrayList<>();
        for(int i = 0 ; i< numberOfStocks; i++){
            numberOfShares.add((int) counter%MAX_SHARES);
            counter/= MAX_SHARES;
        }
        return numberOfShares;
    }
    boolean isLinearIndependent(List<Integer> combination){
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
}
