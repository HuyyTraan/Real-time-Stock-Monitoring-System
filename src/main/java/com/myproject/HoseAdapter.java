package com.myproject;
 
 import java.util.ArrayList;
 import java.util.List; 
 
 public class HoseAdapter implements PriceFetcher {
     private HosePriceFetchLib hoseLib;
     private List<String> stockCodes;
  
     public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
         // TODO: Implement constructor
         this.hoseLib = hoseLib; // Initialize the HosePriceFetchLib instance
         this.stockCodes = stockCodes; // Initialize the list of stock codes
     }
 
     @Override
     public List<StockPrice> fetch() {
         // TODO: Fetch stock data and convert it to StockPrice list
         List<HoseData> hoseDataList = hoseLib.getPrices(stockCodes); // Fetch data from the library
         List<StockPrice> stockPrices = new ArrayList<>(); // Initialize the list of StockPrice objects
         // Iterate through the fetched data and convert each HoseData to StockPrice
         for (HoseData hoseData : hoseDataList) {
             StockPrice stockPrice = convertToStockPrice(hoseData); // Convert each HoseData to StockPrice
             // Check if the conversion was successful and add to the list
             // If the conversion returns null, it means the data was not valid
             // and should not be added to the stockPrices list
             // Otherwise, add the valid StockPrice to the list
             // This is a placeholder check; you should implement your own validation logic
             // and add it to the stockPrices list
             if (stockPrice != null) {
                 stockPrices.add(stockPrice);
             }
         }
         return stockPrices; // Return the list of StockPrice objects
     }
 
     private StockPrice convertToStockPrice(HoseData hoseData) {
         // TODO: Convert HoseData to StockPrice
         return new StockPrice(
             hoseData.getStockCode(), // Get the stock code from HoseData
             hoseData.getPrice(), // Get the price from HoseData
             hoseData.getVolume(), // Get the volume from HoseData
             hoseData.getTimestamp() // Get the timestamp from HoseData
         );
     }
 }