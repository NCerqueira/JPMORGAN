/**
 * @author Nuno Cerqueira
 *
 */
package model;

import java.sql.Timestamp;

public class Trade {
	public enum TradeTypes {
		Buy, Sell
	}
	private TradeTypes type;
	private String stock; // The Stock Object
	private Timestamp timestamp;
	private int shareQuantity;
	private int tradedPrice;

	/**
	 * Constructor
	 */
	public Trade(TradeTypes type, String stock, int shareQuantity, int tradePrice) {
		super();
		if (type.equals(TradeTypes.Buy))
			this.type = TradeTypes.Buy;
		else if (type.equals(TradeTypes.Sell))
			this.type = TradeTypes.Sell;
		this.stock = stock;
		java.util.Date date = new java.util.Date();
		this.timestamp = new Timestamp(date.getTime());
		this.shareQuantity = shareQuantity;
		this.tradedPrice = tradePrice;
	}

	/**
	 * @return the class vars in a String
	 */
	@Override
	public String toString() {
		return timestamp + " " + type + ", Stock:" + stock + ", Quantity:" + shareQuantity + ", Price:" + tradedPrice;
	}

	// Getters/Setters
	/**
	 * @return the type
	 */
	public TradeTypes getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TradeTypes type) {
		this.type = type;
	}

	/**
	 * @return the stock
	 */
	public String getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(String stock) {
		this.stock = stock;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the shareQuantity
	 */
	public int getShareQuantity() {
		return shareQuantity;
	}

	/**
	 * @param shareQuantity
	 *            the shareQuantity to set
	 */
	public void setShareQuantity(int shareQuantity) {
		this.shareQuantity = shareQuantity;
	}

	/**
	 * @return the tradedPrice
	 */
	public int getTradedPrice() {
		return tradedPrice;
	}

	/**
	 * @param tradedPrice
	 *            the tradedPrice to set
	 */
	public void setTradedPrice(int tradedPrice) {
		this.tradedPrice = tradedPrice;
	}

}