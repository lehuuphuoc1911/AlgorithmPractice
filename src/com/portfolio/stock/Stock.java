package com.portfolio.stock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    // stock's price each day
    protected List<Float> Price;
    //length of Price[]
    protected String Name;
    // Return: average return after 1 year (250 days)
    protected double Return;
    // Risk: standard deviation of Return;
    protected double ReturnSTD;
    // drawdown from all-time high average
    protected double drawdownAverage;
    // one year has roughly 250 trading days
    private static final int cycle = 250;

    // constructor
    public Stock(String name) {
        Name = name;
    }
    // get price at a specific day
    public Float getPriceAt(int day){
        return Price.get(day);
    }
    // getter
    public String getName() {
        return Name;
    }
    public double getReturn() {
        return Return;
    }
    public double getReturnSTD() {
        return ReturnSTD;
    }
    public double getDrawdownAverage() {
        return drawdownAverage;
    }
    public int getDataLength(){
        return Price.size();
    }

    // read price data from csv file
    public void getPriceData(){
        BufferedReader csvReader;
        Price = new ArrayList<>();
        try{
            csvReader = new BufferedReader(new FileReader("src\\com\\portfolio\\resource\\stockPrice\\"+this.getName()+".csv"));
            try {
                String row;
                int i = 0;
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    Price.add(Float.parseFloat(data[5]));
                }
            }catch (IOException ioException){
                System.out.println("io exception");
            }
        } catch (FileNotFoundException e){
            System.out.println("file not found");
        }

    }

    // if you bought the stock in a random date, how many % of return you statically get after exact one year on average
    public double calculateReturn(){
        double growRate = 0d;
        for (int i = 0; i<getDataLength()-cycle;i++){
            growRate += (double) (Price.get(i+cycle)-Price.get(i))/Price.get(i);
        }
        growRate/= getDataLength()-cycle;
        Return = growRate;
        return growRate;
    }

    //calculate annual return standard deviation
    public double calculateARSTD(){
        double risk = 0d;
        for (int i = 0; i<getDataLength()-cycle;i++){
            double growRate = (double) (Price.get(i+cycle)-Price.get(i))/Price.get(i);
            risk+= (growRate - Return)*(growRate - Return);
        }
        risk/= getDataLength()-cycle - 1;
        risk = Math.sqrt(risk);
        ReturnSTD = risk;
        return risk;
    }


    // if you bought the stock at all-time high, how many % would you be losing on average
    public double calculateDrawdownAverage(){
        double allTimeHigh = 0;
        double drawdown = 0;
        for (int i = 0; i<getDataLength();i++){
            if(Price.get(i)>=allTimeHigh){
                allTimeHigh= Price.get(i);
            } else {
                drawdown+= (allTimeHigh-Price.get(i))/allTimeHigh;
            }
        }
        this.drawdownAverage = drawdown/Price.size();
        return  drawdown/Price.size();
    }
    public void calculateAll(){
        getPriceData();
        calculateReturn();
        calculateARSTD();
        calculateDrawdownAverage();
    }

}
