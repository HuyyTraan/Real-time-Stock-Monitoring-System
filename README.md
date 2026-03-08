# Assignment 2: Real-time Stock Monitoring System

## Course Information
 
-   **Course**: Advanced Programming (CO2039) 
-   **Project**: Real-time Stock Monitoring System
-   **Objective**: Track stock prices in real-time and receive alerts based on predefined conditions.

---

## Getting Started

### Prerequisites 

Ensure you have **Java and Maven** installed. Check your versions using:

```sh
java -version
mvn -version
```

<i>**Note**: **Java 8+** and **Maven 3+** are required.</i>

### Compile the Project

Use Maven to clean, compile, and package the project:

```sh
mvn clean package
```

This will generate a `.jar` file in `target/`.

### Run the Program

To start real-time stock monitoring:

```sh
mvn exec:java -Dexec.mainClass=com.myproject.Main
```

Maven automatically handles dependencies and execution.

---

## **📌 📂 Project Structure**

This project follows **Maven's standard structure**:

```
Initial Code/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/myproject/  # Java source files
 │   │   │   ├── Main.java
 │   │   │   ├── Stock.java
 │   │   │   ├── StockPrice.java
 │   │   │   ├── StockFeeder.java               ❌ Students must implement
 │   │   │   ├── StockViewer.java
 │   │   │   ├── StockAlertView.java            ❌ Students must implement
 │   │   │   ├── StockRealtimePriceView.java    ❌ Students must implement
 │   │   │   ├── StockTickerView.java
 │   │   │   ├── Logger.java
 │   │   │   ├── PriceFetchManager.java
 │   │   │   ├── PriceFetcher.java
 │   │   │   ├── HosePriceFetchLib.java
 │   │   │   ├── HoseAdapter.java               ❌ Students must implement
 │   │   │   ├── HoseData.java
 │   │   │   ├── HnxPriceFetchLib.java
 │   │   │   ├── HnxAdapter.java
 │   │   │   ├── HnxData.java
 │   ├── resources/
 │   │   ├── stocks.json          # Stock price data
 ├── target/                      # Compiled files
 ├── pom.xml                       # Maven configuration
 ├── README.md                     # Documentation
```

<i>**Note**: The stock price data is now in `stocks.json` instead of hardcoded values.</i>

---

## Features

-   📊 Real-time stock price updates (from `stocks.json`).
-   🔔 Customizable stock alerts based on price thresholds.
-   📡 Fetches stock data dynamically from both **HOSE** and **HNX** exchanges.
-   🕒 Runs on an automated schedule (updates every 10 seconds).
-   🔄 Supports different stock viewers (`Realtime`, `Ticker`, `Alert`).

---

## Student Tasks

Students must implement the following classes:

-   `HoseAdapter.java`
    -   Fetches stock data from `HosePriceFetchLib`.
    -   Implements the `PriceFetcher` interface.
    -   Converts raw data into `StockPrice` objects.
-   `StockFeeder.java`
    -   Manages stock price updates.
    -   Implements the Observer pattern to notify viewers (`StockAlertView`, `StockRealtimePriceView`, `StockTickerView`).
    -   Supports registering and unregistering stock viewers.
-   `StockAlertView.java`
    -   Alerts when stock prices go above or below a defined threshold.
    -   Implements the `StockViewer` interface.
    -   Uses `Logger.logAlert()` to notify price changes.
-   `StockRealtimePriceView.java`
    -   Displays real-time stock prices as they update.
    -   Implements the `StockViewer` interface.
    -   Uses `Logger.logRealtime()` to print the latest stock price.
