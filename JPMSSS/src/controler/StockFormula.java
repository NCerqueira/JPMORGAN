/**
 * @author Nuno Cerqueira
 *
 */
package controler;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import model.Stock;
import model.Stock.StockTypes;
import model.Trade;

public final class StockFormula {
	/**
	 * @return the DividendYield of a Given Stock using tickerPrice
	 */
	public final static BigDecimal DividendYield(Stock stock, int tickerPrice) {
		// Common Stock
		if (stock.getType().equals(StockTypes.Common)) {
			if (stock.getLastDividend() != 0)
				return BigDecimal.valueOf(stock.getLastDividend()).divide(BigDecimal.valueOf(tickerPrice), 3,
						BigDecimal.ROUND_HALF_UP);
		}
		// Preferred Stock
		else if (stock.getType().equals(StockTypes.Preferred)) {
			if (stock.getLastDividend() != 0)
				return BigDecimal.valueOf(stock.getFixedDividend()).divide(BigDecimal.valueOf(100), 3, BigDecimal.ROUND_HALF_UP)
						.multiply(BigDecimal.valueOf(stock.getParValue()))
						.divide(BigDecimal.valueOf(tickerPrice), 3, BigDecimal.ROUND_HALF_UP);
		}
		return new BigDecimal(0);
	}

	/**
	 * @return the PERatio of a Given Stock using tickerPrice
	 */
	public final static BigDecimal PERatio(Stock stock, int tickerPrice) {
		BigDecimal divisor = DividendYield(stock, tickerPrice);
		if (divisor.compareTo(BigDecimal.valueOf(0)) != 0)
			return BigDecimal.valueOf(tickerPrice).divide(divisor, 3, BigDecimal.ROUND_HALF_UP);
		return BigDecimal.valueOf(0);
	}

	/**
	 * @return the geometricMean using all stock prices
	 */
	public final static BigDecimal geometricMean(List<BigDecimal> allStockPriceList) {
		// b. Calculate the GBCE All Share Index using the geometric mean of
		// prices for all stocks
		BigDecimal bTotal = new BigDecimal(1);
		for (BigDecimal bigDecimal : allStockPriceList) {
			bTotal = bTotal.multiply(bigDecimal);
		}
		return BigDecimal.valueOf(Math.pow(bTotal.doubleValue(), 1.0 / allStockPriceList.size()));
	}

	/**
	 * @return the stockPrice using last 15 min trades
	 */
	public final static BigDecimal stockPriceFifteenMin(List<Trade> tradeList) {
		// iv. Calculate Stock Price based on trades recorded in past 15 minutes
		if (tradeList == null)
			return BigDecimal.valueOf(0);
		long numerator = 0L;
		long denominator = 0;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -15);
		Timestamp timestampMinusFifteenMin = new Timestamp(cal.getTime().getTime());
		for (Trade trade : tradeList) {
			if (trade.getTimestamp().after(timestampMinusFifteenMin)) {
				numerator += (trade.getTradedPrice() * trade.getShareQuantity());
				denominator += trade.getShareQuantity();
			}
		}
		if (denominator == 0)
			return BigDecimal.valueOf(0);
		return BigDecimal.valueOf(numerator).divide(BigDecimal.valueOf(denominator), 3, BigDecimal.ROUND_HALF_UP);
	}
}
