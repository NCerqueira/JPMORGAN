/**
 * @author Nuno Cerqueira
 *
 */
package model;

import java.util.LinkedList;
import java.util.List;

public class StockTradeTuple {
	private Stock stock;
	private List<Trade> tradeList;

	/**
	 * Constructor
	 */
	public StockTradeTuple(Stock stock) {
		super();
		this.stock = stock;
	}

	/**
	 * Ads a trade to the trade List
	 */
	public void addTrade(Trade trade) {
		if (tradeList == null)
			tradeList = new LinkedList<>();
		tradeList.add(trade);
		stock.setLastTradePrice(trade.getTradedPrice());
	}

	/**
	 * @return the stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * @return the tradeList
	 */
	public List<Trade> getTradeList() {
		return tradeList;
	}

	/**
	 * @param tradeList
	 *            the tradeList to set
	 */
	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}
}
