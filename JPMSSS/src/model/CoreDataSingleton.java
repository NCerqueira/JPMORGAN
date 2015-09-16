/**
 * @author Nuno Cerqueira
 *
 */
package model;

import java.util.HashMap;
import java.util.Map;

public class CoreDataSingleton {
	// TODO implement serialization and methods to retain data when user closes the aplication
	// private static final long serialVersionUID = -1784818843819161333L;

	private Map<String, StockTradeTuple> stockTradeMap = new HashMap<>(); // String is Stock type

	/**
	 * Ads a Stock to the stockTradeMap
	 */
	public void addStock(Stock stock) {
		StockTradeTuple exists = stockTradeMap.get(stock.getSymbol());
		if (exists == null)
			stockTradeMap.put(stock.getSymbol(), new StockTradeTuple(stock));
		else
			System.err.println("Stock already exists and cannot be re-aded");
	}

	/**
	 * Ads a trade to a given Stock in the stockTradeMap
	 */
	public void addTrade(Trade trade) {
		StockTradeTuple tuple = stockTradeMap.get(trade.getStock());
		if (tuple != null)
			tuple.addTrade(trade);
		else
			System.err.println("Trade rejected: Cannot trade a stock that does not exist.");
	}

	/**
	 * @return the StockTradeTuple
	 */
	public StockTradeTuple getStockTradeTuple(String stockSymbol) {
		return stockTradeMap.get(stockSymbol);
	}

	/**
	 * @return the stockTradeMap
	 */
	public Map<String, StockTradeTuple> getStockTradeMap() {
		return stockTradeMap;
	}

	// Classic Lazy Singleton
	private static CoreDataSingleton instance = null;

	/**
	 * Exists only to defeat instantiation.
	 */
	private CoreDataSingleton() {
		// Exists only to defeat instantiation.
	}

	/**
	 * @return the instance
	 */
	public static CoreDataSingleton getInstance() {
		if (instance == null) {
			instance = new CoreDataSingleton();
		}
		return instance;
	}

}