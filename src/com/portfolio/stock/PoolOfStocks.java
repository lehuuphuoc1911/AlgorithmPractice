package com.portfolio.stock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoolOfStocks extends Stock{
    private List<Stock> listOfStock;
    private List<Integer> numberOfShares;
    private int numberOfStocks = 0;

    public PoolOfStocks(String name) {
        super(name);
        listOfStock = new ArrayList<>();
        numberOfShares = new ArrayList<>();
        Price = new ArrayList<>();
    }

    public void addStock(Stock stock){
        listOfStock.add(stock);
        numberOfShares.add(1);
        numberOfStocks ++;
    }

    public boolean setNumberOfShares(List<Integer> numberOfShares) {
        if(numberOfShares.size() == this.numberOfShares.size()){
            this.numberOfShares = numberOfShares;
            return true;
        }
        return false;
    }

    @Override
    public void getPriceData() {
        for (Stock stock:this.listOfStock
             ) {
            stock.getPriceData();
        }
    }

    public void calculatePrice(){
        int dataLength = Integer.MAX_VALUE;
        for (Stock stock:listOfStock
             ) {
            if (stock.getDataLength()< dataLength){
                dataLength = stock.getDataLength();
            }
        }

        Price = new ArrayList<>();
        for(int i = 0; i< dataLength; i++){
            Float priceAtI =0f;
            for (int j = 0; j< numberOfStocks; j++){
                priceAtI+= numberOfShares.get(j)*listOfStock.get(j).getPriceAt(i);
            }
            Price.add(priceAtI);
        }
    }



    public int getNumberOfStocks() {
        return numberOfStocks;
    }
}
