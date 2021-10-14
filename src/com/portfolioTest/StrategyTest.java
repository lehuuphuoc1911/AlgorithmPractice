package com.portfolioTest;
import com.portfolio.stock.PoolOfStocks;
import com.portfolio.stock.Stock;
import com.portfolio.strategy.MinDrawdownStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {
    @Test
   void isLinearIndependent(){
        Stock hpg = new Stock("HPG");
        Stock fpt = new Stock("FPT");
        Stock msn = new Stock("MSN");
        Stock vic = new Stock("VIC");
        PoolOfStocks myPortfolio = new PoolOfStocks("ABC");
        myPortfolio.addStock(hpg);
        myPortfolio.addStock(fpt);
        myPortfolio.addStock(msn);
        myPortfolio.addStock(vic);
        MinDrawdownStrategy minDrawdownStrategy = new MinDrawdownStrategy(myPortfolio);
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(2);
        list.add(0);
        assertEquals(list,minDrawdownStrategy.getNumberOfSharesCombination(88));
        assertEquals(false,minDrawdownStrategy.isLinearIndependent(list));

    }


}
