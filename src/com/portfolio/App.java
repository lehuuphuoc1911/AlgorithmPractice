package com.portfolio;

import com.portfolio.stock.Stock;

import javax.imageio.IIOException;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Stock hpg = new Stock("VIC");
        hpg.getPriceData();
        hpg.calculateReturn();
        hpg.calculateRisk();
        System.out.println("Return :"+hpg.getReturn()*100);
        System.out.println("Risk :"+hpg.getRisk()*100);
    }
}
