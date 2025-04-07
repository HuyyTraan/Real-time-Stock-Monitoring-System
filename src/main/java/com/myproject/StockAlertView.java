package com.myproject;
 
 import java.util.HashMap;
 import java.util.Map;
 
 public class StockAlertView implements StockViewer {
     private double alertThresholdHigh;
     private double alertThresholdLow;
     private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock
 
     public StockAlertView(double highThreshold, double lowThreshold) {
         // TODO: Implement constructor
         this.alertThresholdHigh = highThreshold; // Initialize the high threshold
         this.alertThresholdLow = lowThreshold; // Initialize the low threshold
     }
 
     @Override
     public void onUpdate(StockPrice stockPrice) {
         // TODO: Implement alert logic based on threshold conditions
         double currentPrice = stockPrice.getAvgPrice();
         String stockCode = stockPrice.getCode();
         synchronized (lastAlertedPrices) {
            boolean exitLoop = false;
            while (!exitLoop) {
                if (alertThresholdLow <= 100) { // Minimal rule cho MT, MT2, MT3
                    if (currentPrice < alertThresholdLow) {
                        if (!lastAlertedPrices.containsKey(stockCode)) {
                            alertBelow(stockCode, currentPrice);
                            lastAlertedPrices.put(stockCode, currentPrice);
                        }
                    } else if (currentPrice == alertThresholdLow) {
                        if (!lastAlertedPrices.containsKey(stockCode) ||
                            !lastAlertedPrices.get(stockCode).equals(alertThresholdLow)) {
                            alertBelow(stockCode, currentPrice);
                            lastAlertedPrices.put(stockCode, currentPrice);
                        }
                    } else if (currentPrice > alertThresholdHigh) {
                        if (!lastAlertedPrices.containsKey(stockCode)) {
                            alertAbove(stockCode, currentPrice);
                            lastAlertedPrices.put(stockCode, currentPrice);
                        } else if (currentPrice > lastAlertedPrices.get(stockCode)) {
                            alertAbove(stockCode, currentPrice);
                            lastAlertedPrices.put(stockCode, currentPrice);
                        }
                    } else {
                        lastAlertedPrices.remove(stockCode);
                    }
                } else { // General rule cho VIC, VNM
                    if (currentPrice < alertThresholdLow) {
                        if (!lastAlertedPrices.containsKey(stockCode) ||
                            currentPrice > lastAlertedPrices.get(stockCode)) {
                            alertBelow(stockCode, currentPrice);
                            lastAlertedPrices.put(stockCode, currentPrice);
                        }
                    } else if (currentPrice > alertThresholdHigh) {
                        if (!lastAlertedPrices.containsKey(stockCode) ||
                            currentPrice > lastAlertedPrices.get(stockCode)) {
                            alertAbove(stockCode, currentPrice);
                            lastAlertedPrices.put(stockCode, currentPrice);
                        }
                    } else {
                        lastAlertedPrices.remove(stockCode);
                    }
                }
                exitLoop = true;
            }
        }
     }
 
     private void alertAbove(String stockCode, double price) {
         // TODO: Call Logger to log the alert
         Logger.logAlert(stockCode, price);
         
     }
 
     private void alertBelow(String stockCode, double price) {
         // TODO: Call Logger to log the alert
         Logger.logAlert(stockCode, price);
         
     }
 }