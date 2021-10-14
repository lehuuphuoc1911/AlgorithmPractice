package com.portfolio;

import com.portfolio.stock.PoolOfStocks;
import com.portfolio.stock.Stock;
import com.portfolio.strategy.MaxReturnOverSTDStrategy;
import com.portfolio.strategy.MinDrawdownStrategy;
import com.portfolio.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Stock hpg = new Stock("HPG");
        Stock fpt = new Stock("FPT");
        Stock msn = new Stock("MSN");
        Stock vic = new Stock("VIC");
        PoolOfStocks myPortfolio = new PoolOfStocks("ABC");
        PoolOfStocks myPortfolio2 = new PoolOfStocks("ABC");
        myPortfolio.addStock(hpg);
        myPortfolio.addStock(fpt);
        myPortfolio.addStock(msn);
        myPortfolio.addStock(vic);
        myPortfolio.getPriceData();
        //hpg.getPriceData();
        hpg.calculateReturn();
        hpg.calculateDrawdownAverage();
        fpt.calculateReturn();
        fpt.calculateDrawdownAverage();
        msn.calculateReturn();
        msn.calculateDrawdownAverage();
        vic.calculateReturn();
        vic.calculateDrawdownAverage();
        System.out.println(hpg.getName() +"-->  Return :"+hpg.getReturn()*100+" Drawdown Average :"+hpg.getDrawdownAverage()*100);
        System.out.println(fpt.getName() +"-->  Return :"+fpt.getReturn()*100+" Drawdown Average :"+fpt.getDrawdownAverage()*100);
        System.out.println(msn.getName() +"-->  Return :"+msn.getReturn()*100+" Drawdown Average :"+msn.getDrawdownAverage()*100);
        System.out.println(vic.getName() +"-->  Return :"+vic.getReturn()*100+" Drawdown Average :"+vic.getDrawdownAverage()*100);

        Strategy minDrawdownStrategyABC = new MinDrawdownStrategy(myPortfolio);
        minDrawdownStrategyABC.evaluate();
        System.out.println("Return = "+minDrawdownStrategyABC.getReturn()*100);
        System.out.println("MinDrawdown = "+minDrawdownStrategyABC.getOptimizedMetric()*100);
        System.out.println(minDrawdownStrategyABC.getOptimizedCombination());
        System.out.println("--------------------------------------------");
        Strategy maxReturnOverSTDABC = new MaxReturnOverSTDStrategy(myPortfolio);
        maxReturnOverSTDABC.evaluate();
        System.out.println("Return = "+maxReturnOverSTDABC.getReturn()*100);
        System.out.println("MaxReturnOverSTD = "+maxReturnOverSTDABC.getOptimizedMetric());
        System.out.println(maxReturnOverSTDABC.getOptimizedCombination());
    }
}
