package com.portfolioTest;
import com.portfolio.stock.PoolOfStocks;
import com.portfolio.stock.Stock;
import com.portfolio.strategy.MinDrawdownStrategy;
import com.portfolio.strategy.Strategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {
    @Test
   void isLinearIndependent(){

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(2);
        list.add(2);
        list.add(0);
        assertEquals(list, Strategy.getCombination(88, 4, 5));
        assertEquals(false,Strategy.isLinearIndependent(list));

    }


}
