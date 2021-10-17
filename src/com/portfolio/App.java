package com.portfolio;

import com.portfolio.stock.PoolOfStocks;
import com.portfolio.stock.Stock;
import com.portfolio.strategy.MaxReturnOverDrawDownStrategy;
import com.portfolio.strategy.MaxReturnOverSTDStrategy;
import com.portfolio.strategy.MinDrawdownStrategy;
import com.portfolio.strategy.Strategy;


public class App {
    public static void main(String[] args) {
        //create 4 stock
        Stock hpg = new Stock("HPG");
        Stock fpt = new Stock("FPT");
        Stock msn = new Stock("MSN");
        Stock vic = new Stock("VIC");
        //create a portfolio
        PoolOfStocks myPortfolio = new PoolOfStocks("ABC");
        //add stocks to the portfolio
        myPortfolio.addStock(hpg);
        myPortfolio.addStock(fpt);
        myPortfolio.addStock(msn);
        myPortfolio.addStock(vic);
        // read the stock price from csv file
        myPortfolio.getPriceData();
        // calculate return and draw down average of each stock then print it.
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
        //min draw down strategy
        Strategy minDrawdownStrategyABC = new MinDrawdownStrategy(myPortfolio);
        minDrawdownStrategyABC.evaluate();
        System.out.println("Return = "+minDrawdownStrategyABC.getReturn()*100);
        System.out.println("MinDrawdown = "+minDrawdownStrategyABC.getOptimizedMetric()*100);
        System.out.println(minDrawdownStrategyABC.getOptimizedCombination());
        System.out.println("--------------------------------------------");
        // max return/standard deviation of return strategy
        Strategy maxReturnOverSTDABC = new MaxReturnOverSTDStrategy(myPortfolio);
        maxReturnOverSTDABC.evaluate();
        System.out.println("Return = "+maxReturnOverSTDABC.getReturn()*100);
        System.out.println("MaxReturnOverSTD = "+maxReturnOverSTDABC.getOptimizedMetric());
        System.out.println(maxReturnOverSTDABC.getOptimizedCombination());
        System.out.println("--------------------------------------------");
        // max return/draw down average strategy
        Strategy maxReturnOverDrawdownABC = new MaxReturnOverDrawDownStrategy(myPortfolio);
        maxReturnOverDrawdownABC.evaluate();
        System.out.println("Return = "+maxReturnOverDrawdownABC.getReturn()*100);
        System.out.println("maxReturnOverDrawdown = "+maxReturnOverDrawdownABC.getOptimizedMetric());
        System.out.println(maxReturnOverDrawdownABC.getOptimizedCombination());

        long t2 = System.currentTimeMillis();
        // time executed
        System.out.println(t2-t1);
    }
}
/* RESULT

HPG-->  Return :53.807602361948405 Drawdown Average :16.152968661632748
FPT-->  Return :32.44515520576743 Drawdown Average :7.1358462644125265
MSN-->  Return :23.345434626154933 Drawdown Average :24.969638741412293
VIC-->  Return :38.46985029992498 Drawdown Average :9.630242322469885
com.portfolio.strategy.MinDrawdownStrategy
search 14641 cases
Return = 35.43927920310713
MinDrawdown = 5.547516593412017
[10, 9, 0, 5]
--------------------------------------------
com.portfolio.strategy.MaxReturnOverSTDStrategy
search 14641 cases
Return = 31.812900981474375
MaxReturnOverSTD = 1.1303100609336008
[0, 4, 0, 1]
--------------------------------------------
com.portfolio.strategy.MaxReturnOverDrawDownStrategy
search 14641 cases
Return = 37.71784499863177
maxReturnOverDrawdown = 6.60194808638393
[10, 4, 0, 3]
4360

Process finished with exit code 0
* */
