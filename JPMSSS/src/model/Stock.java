/**
 * @author Nuno Cerqueira
 *
 */
package model;

public class Stock {
	public enum StockTypes {
		Common, Preferred
	}

	private String symbol;
	private StockTypes type;
	private int lastDividend;
	private Integer fixedDividend;
	private int parValue;
	private int lastTradePrice;

	/**
	 * @return the class vars in a String
	 */
	@Override
	public String toString() {
		// One-line description of the Stock
		if (type.equals(StockTypes.Common)) {
			return getSymbol() + ", Type:" + type + ", Last Dividend:" + getLastDividend() + ", Par Value:"
					+ getParValue() + ", Last Traded Price:" + getLastTradePrice();
		} else if (type.equals(StockTypes.Preferred)) {
			return getSymbol() + ", Type:" + type + ", Last Dividend:" + getLastDividend() + ", Fixed Dividend:"
					+ getFixedDividend() + ", Par Value:" + getParValue() + ", Last Traded Price:"
					+ getLastTradePrice();
		}
		return "MALFORMED STOCK";
	}

	/**
	 * Constructor
	 */
	public Stock(String symbol, StockTypes type, int lastDividend, Integer fixedDividend, int parValue) {
		super();
		this.symbol = symbol;
		if (type.equals(StockTypes.Common))
			this.type = StockTypes.Common;
		else if (type.equals(StockTypes.Preferred))
			this.type = StockTypes.Preferred;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the lastDividend
	 */
	public int getLastDividend() {
		return lastDividend;
	}

	/**
	 * @param lastDividend
	 *            the lastDividend to set
	 */
	public void setLastDividend(int lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @return the fixedDividend
	 */
	public Integer getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * @param fixedDividend
	 *            the fixedDividend to set
	 */
	public void setFixedDividend(Integer fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/**
	 * @return the parValue
	 */
	public int getParValue() {
		return parValue;
	}

	/**
	 * @param parValue
	 *            the parValue to set
	 */
	public void setParValue(int parValue) {
		this.parValue = parValue;
	}

	/**
	 * @return the type
	 */
	public StockTypes getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(StockTypes type) {
		this.type = type;
	}

	/**
	 * @return the lastTradePrice
	 */
	public int getLastTradePrice() {
		return lastTradePrice;
	}

	/**
	 * @param lastTradePrice
	 *            the lastTradePrice to set
	 */
	public void setLastTradePrice(int lastTradePrice) {
		this.lastTradePrice = lastTradePrice;
	}
}