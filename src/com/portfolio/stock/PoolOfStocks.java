package com.portfolio.stock;


import java.util.ArrayList;
import java.util.List;

public class PoolOfStocks extends Stock{
    // pool of stock contain a several of stock
    private List<Stock> listOfStock;
    // number of share of each stock for a pool unit
    private List<Integer> numberOfShares;
    private int numberOfStocks = 0;

    // constructor
    public PoolOfStocks(String name) {
        super(name);
        listOfStock = new ArrayList<>();
        numberOfShares = new ArrayList<>();
        Price = new ArrayList<>();
    }

    // add stock to the pool
    public void addStock(Stock stock){
        listOfStock.add(stock);
        numberOfShares.add(1);
        numberOfStocks ++;
    }

    // getter
    public boolean setNumberOfShares(List<Integer> numberOfShares) {
        if(numberOfShares.size() == this.numberOfShares.size()){
            this.numberOfShares = numberOfShares;
            return true;
        }
        return false;
    }

    //read price data from csv file
    @Override
    public void getPriceData() {
        for (Stock stock:this.listOfStock
             ) {
            stock.getPriceData();
        }
    }

    //calculate the value of a pool unit
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

    // getter
    public int getNumberOfStocks() {
        return numberOfStocks;
    }
}
