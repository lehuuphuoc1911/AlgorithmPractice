package com.portfolio;

import com.portfolio.stock.PoolOfStocks;
import com.portfolio.stock.Stock;
import com.portfolio.strategy.MaxReturnOverDrawDownStrategy;
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
        long t1 = System.currentTimeMillis();
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
        System.out.println("--------------------------------------------");
        Strategy maxReturnOverDrawdownABC = new MaxReturnOverDrawDownStrategy(myPortfolio);
        maxReturnOverDrawdownABC.evaluate();
        System.out.println("Return = "+maxReturnOverDrawdownABC.getReturn()*100);
        System.out.println("maxReturnOverDrawdown = "+maxReturnOverDrawdownABC.getOptimizedMetric());
        System.out.println(maxReturnOverDrawdownABC.getOptimizedCombination());
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }
}
/* THIS IS THE RESULT WHICH IS PRINTED ON THE CONSOLE
* HPG-->  Return :53.807602361948405 Drawdown Average :16.152968661632748
FPT-->  Return :32.44515520576743 Drawdown Average :7.1358462644125265
MSN-->  Return :23.345434626154933 Drawdown Average :24.969638741412293
VIC-->  Return :38.46985029992498 Drawdown Average :9.630242322469885
com.portfolio.strategy.MinDrawdownStrategy
search 160000 cases
Return = 35.31522946568105
MinDrawdown = 5.547273590824271
[19, 18, 0, 10]
--------------------------------------------
com.portfolio.strategy.MaxReturnOverSTDStrategy
search 160000 cases
Return = 31.787280008400327
MaxReturnOverSTD = 1.1305480261513114
[0, 17, 0, 4]
--------------------------------------------
com.portfolio.strategy.MaxReturnOverDrawDownStrategy
search 160000 cases
Return = 37.78498492009014
maxReturnOverDrawdown = 6.604968421513805
[13, 5, 0, 4]
43505

Process finished with exit code 0
* */
