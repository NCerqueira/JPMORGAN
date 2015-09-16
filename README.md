# JPMorgan
Super Simple Stocks Exercise
Requirements
	Provide working source code that will :-
	For a given stock, 
	calculate the dividend yield
	calculate the P/E Ratio
	record a trade, with timestamp, quantity of shares, buy or sell indicator and price
	Calculate Stock Price based on trades recorded in past 15 minutes
	Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
Constraints & Notes
	Written in one of these languages:
	Java, C#, C++, Python
	No database or GUI is required, all data need only be held in memory
	Formulas and data provided are simplified representations for the purpose of this exercise
Table1. Sample data from the Global Beverage Corporation Exchange

Stock Symbol	Type	Last Dividend	Fixed Dividend	Par Value	
TEA	Common	0		100	
POP	Common	8		100	
ALE	Common	23		60	
GIN	Preferred	8	2%	100	
JOE	Common	13		250	
All number values in pennies
Table 2. Formula
Dividend Yield	
      Common -> (Last Dividend)/(Ticker Price)	
      Preferred -> (Fixed Dividend .Par Value)/(Ticker Price)
P/E Ratio	(Ticker Price)/Dividend
Geometric Mean	√(n&p_1 p_2 p_3…p_n )
Stock Price	(∑_i▒〖 Trade 〖Price〗_i×〖Quantity〗_i 〗)/(∑_i▒〖Quantity〗_i )
