package controler;

import static controler.StockFormula.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import model.CoreDataSingleton;
import model.Stock;
import model.Stock.StockTypes;
import model.StockTradeTuple;
import model.Trade.TradeTypes;
import model.Trade;

public class SuperSimpleStocksApp {

	public static void main(String[] args) {
		CoreDataSingleton data = CoreDataSingleton.getInstance();
		try (Scanner scanner = new Scanner(System.in)) {
			int selected = 99999;
			do {
				boolean skipUserRead = false;
				// Menu
				System.out.println("Select one of the following options");
				System.out.println("1:Add a stock");
				System.out.println("2:Add a trade");
				System.out.println("3:Calculate Dividend Yeld");
				System.out.println("4:Calculate P/E Racio");
				System.out.println("5:Geometric Mean");
				System.out.println("6:Calculate Stock Price");
				System.out.println("0:Exit(lose all changes)");

				// Read Input
				if (scanner.hasNextInt())
					selected = scanner.nextInt();
				else
					scanner.nextLine();

				// Respond to Input
				String stockSymbol;
				String type;
				String lastDivident;
				String fixedDividend;
				String parValue;
				String quantity;
				String price;
				String tickerPrice;
				switch (selected) {
				case 1: // Add a stock
					System.out.println(
							"Please input the following data: Stock symbol, type(Common or Preferred), \n"
							+ "Last Dividend, Fixed Dividend %(write \"Null\" to pass a null value), Par Value");
					stockSymbol = scanner.next();
					type = scanner.next();
					lastDivident = scanner.next();
					fixedDividend = scanner.next();
					parValue = scanner.next();
					try {
						Stock stock;
						StockTypes st;
						if (type.equalsIgnoreCase(StockTypes.Common.name()))
							st = StockTypes.Common;
						else if (type.equalsIgnoreCase(StockTypes.Preferred.name()))
							st = StockTypes.Preferred;
						else {
							System.err.println("Invalid Stock type");
							break;
						}
						if (fixedDividend.equalsIgnoreCase("Null"))
							stock = new Stock(stockSymbol, st, Integer.parseInt(lastDivident), (Integer) null,
									Integer.parseInt(parValue));
						else
							stock = new Stock(stockSymbol, st, Integer.parseInt(lastDivident),
									Integer.parseInt(fixedDividend), Integer.parseInt(parValue));
						data.addStock(stock);
						skipUserRead = true;
					} catch (NumberFormatException e) {
						System.err.println(
								"Invalid Input: lastDivident,fixedDividend,parValue should be number with no decimal point.");
					}
					break;
				case 2: // add a trade
					System.out.println(
							"Please input the following data: Type(Buy or Sell),Stock symbol, Quantity , Price");
					type = scanner.next();
					stockSymbol = scanner.next();
					quantity = scanner.next();
					price = scanner.next();
					try {
						Trade trade;
						if (type.equalsIgnoreCase(TradeTypes.Buy.name()))
							trade = new Trade(TradeTypes.Buy, stockSymbol, Integer.parseInt(quantity),
									Integer.parseInt(price));
						else if (type.equalsIgnoreCase(TradeTypes.Sell.name()))
							trade = new Trade(TradeTypes.Sell, stockSymbol, Integer.parseInt(quantity),
									Integer.parseInt(price));
						else {
							System.err.println("Invalid Trade type");
							break;
						}
						data.addTrade(trade);
						skipUserRead = true;
					} catch (NumberFormatException e) {
						System.err.println("Invalid Input: quantity,price should be a number with no decimal point.");
					}
					break;
				case 3: // Calculate Dividend Yeld
					System.out.println("Please input the following data: Stock symbol, ticker price");
					stockSymbol = scanner.next();
					tickerPrice = scanner.next();
					try {
						Stock stock = data.getStockTradeTuple(stockSymbol).getStock();
						System.out.println(DividendYield(stock, Integer.parseInt(tickerPrice)).toString());
					} catch (NumberFormatException e) {
						System.err.println("Invalid Input: ticker price should be a number with no decimal point.");
					} catch (NullPointerException e) {
						System.err.println("Stock selected does not exist");
					}
					break;
				case 4: // Calculate P/E Racio
					System.out.println("Please input the following data: Stock symbol, ticker price");
					stockSymbol = scanner.next();
					tickerPrice = scanner.next();
					try {
						Stock stock = data.getStockTradeTuple(stockSymbol).getStock();
						System.out.println(PERatio(stock, Integer.parseInt(tickerPrice)).toString());
					} catch (NumberFormatException e) {
						System.err.println("Invalid Input: ticker price should be a number with no decimal point.");
					} catch (NullPointerException e) {
						System.err.println("Stock selected does not exist");
					}
					break;
				case 5:// 5:Geometric Mean
					try {
						Map<String, StockTradeTuple> stockTradeMap = data.getStockTradeMap();
						List<BigDecimal> bigList = new ArrayList<>();
						for (Entry<String, StockTradeTuple> stockTradeTuple : stockTradeMap.entrySet()) {
							List<Trade> tlist2 = stockTradeTuple.getValue().getTradeList();
							bigList.add(stockPriceFifteenMin(tlist2));
						}
						System.out.println("Geometric mean :"
								+ geometricMean(bigList).setScale(3, BigDecimal.ROUND_HALF_UP).toString());
					} catch (NullPointerException e) {
						System.err.println("Stock selected does not exist");
					}
					break;
				case 6:// Calculate Stock Price
					System.out.println("Please input the following data: Stock Symbol");
					stockSymbol = scanner.next();
					try {
						List<Trade> tlist = data.getStockTradeTuple(stockSymbol).getTradeList();
						System.out.println("Stock Price: " + stockPriceFifteenMin(tlist).toString());
					} catch (NullPointerException e) {
						System.err.println("Stock selected does not exist");
					}
					break;
				case 0:
					skipUserRead = true;
					break;
				default:
					System.err.println("Invalid Choice");
				}
				// Clean Scanner
				if (scanner.hasNextLine())
					scanner.nextLine();
				// User Read
				if (!skipUserRead) {
					System.out.println("Press Enter to continue");
					scanner.nextLine();
				}
			} while (selected != 0);
		}
	}

}
