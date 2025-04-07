package com.myproject;
 
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
 public class StockFeeder {
     private List<Stock> stockList = new ArrayList<>();
     private Map<String, List<StockViewer>> viewers = new HashMap<>();
     private static StockFeeder instance = null;
 
     // TODO: Implement Singleton pattern
     private StockFeeder() {}
 
     public static StockFeeder getInstance() {
         // TODO: Implement Singleton logic
         return (instance == null) ? (instance = new StockFeeder()) : instance;
     }
 
     public void addStock(Stock stock) {
         // TODO: Implement adding a stock to stockList
         boolean exists = false;
    int i = 0;
    while (i < stockList.size() && !exists) {
        Stock s = stockList.get(i);
        if (s.getCode().equals(stock.getCode())) {
            exists = true;
        }
        i++;
    }
    if (!exists) {
        stockList.add(stock);
    }
     }
 
     public void registerViewer(String code, StockViewer stockViewer) {
         // TODO: Implement registration logic, including checking stock existence
         boolean exists = false;
    int i = 0;
    while (i < stockList.size() && !exists) {
        Stock s = stockList.get(i);
        if (s.getCode().equals(code)) {
            exists = true;
        }
        i++;
    }
    if (!exists) {
        Logger.errorRegister(code);
    }
    List<StockViewer> viewerList = viewers.get(code);
    if (viewerList == null) {
        viewerList = new ArrayList<>();
        viewers.put(code, viewerList);
    }
    
    if (!viewerList.contains(stockViewer)) {
        viewerList.add(stockViewer);
    }
     }    
 
     public void unregisterViewer(String code, StockViewer stockViewer) {
         // TODO: Implement unregister logic, including error logging
         boolean stockExists = false;
    int i = 0;
    while (i < stockList.size() && !stockExists) {
        Stock stock = stockList.get(i);
        if (stock.getCode().equals(code)) {
            stockExists = true;
        }
        i++;
    }
    if (!stockExists) {
        Logger.errorUnregister(code);
        return;
    }
 
    List<StockViewer> viewerList = viewers.get(code);
    if (viewerList == null || !viewerList.contains(stockViewer)) {
        Logger.errorUnregister(code);
        return;
    }
 
    viewerList.remove(stockViewer);
     }
 
     public void notify(StockPrice stockPrice) {
         // TODO: Implement notifying registered viewers about price updates
         String code = stockPrice.getCode();
         List<StockViewer> viewerList = viewers.get(code);
         if (viewerList != null) {
             for (StockViewer viewer : viewerList) {
                 viewer.onUpdate(stockPrice);
             }
         }
     }
 }