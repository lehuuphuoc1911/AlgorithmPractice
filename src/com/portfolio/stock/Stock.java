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
    protected double Risk;

    private static final int cycle = 250;


    public Stock(String name) {
        Name = name;
    }

    public List<Float> getPrice() {
        return Price;
    }

    public Float getPriceAt(int day){
        return Price.get(day);
    }

    public String getName() {
        return Name;
    }

    public double getReturn() {
        return Return;
    }

    public double getRisk() {
        return Risk;
    }

    public void getPriceData(){
        BufferedReader csvReader;
        Price = new ArrayList<>();
        try{
            csvReader = new BufferedReader(new FileReader("src\\com\\portfolio\\resource\\stockPrice\\"+this.getName()+".csv"));
            try {
                String row;
                //(row = csvReader.readLine()) != null
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

    public void calculateReturn(){
        double growRate = 0f;
        for (int i = 0; i<getDataLength()-cycle;i++){
            growRate += (double) (Price.get(i+cycle)-Price.get(i))/Price.get(i);
        }
        growRate/= getDataLength()-cycle;
        Return = growRate;
    }

    public void calculateRisk(){
        double risk = 0f;
        for (int i = 0; i<getDataLength()-cycle;i++){
            double growRate = (double) (Price.get(i+cycle)-Price.get(i))/Price.get(i);
            risk+= (growRate - Return)*(growRate - Return);
        }
        risk/= getDataLength()-cycle - 1;
        risk = Math.sqrt(risk);
        Risk = risk;
    }

    public int getDataLength(){
        return Price.size();
    }

}
