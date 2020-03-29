package auxiliaryClasses;


public class Paths {
	
	//TOKENS
	
	public static String API_TOKEN = null;
	
	//URL
	
	public static String API_ADDRESS = "http://localhost:3001"; //TEEEEST
//	static public final String API_ADDRESS = "https://api.7markets.de";
//	static public final String API_ADDRESS = "http://192.168.0.100:3001"; // ELIAS HAT VON 3000 auf 3001 gestellt 20.2.20
//	public static String API_ADDRESS = null;

	
	static public final String DIX_ADDRESS = "https://squeezemetrics.com/monitor/static/DIX.csv";
	
	//SENTIMEN
	static public final String TSP_SENTIMENT_ADDRESS = "https://www.tsptalk.com/sentiment.php";
	static public final String NDR_SENTIMENT_ADDRESS = "https://www.ndr.com/invest/infopage/S574";
	static public final String NAAIM_SENTIMENT_ADDRESS = "http://www.naaim.org/programs/naaim-exposure-index/";
	static public final String NAAIM_FULL_SENTIMENT_ADDRESS = "http://www.naaim.org/wp-content/plugins/ip-chart/export.php";
	
	//PUT CALL CBOE
	static public final String CBOE_DAILY_MARKET_STATS_ADDRESS = "http://www.cboe.com/data/current-market-statistics/cboe-daily-market-statistics";
	static public final String CBOE_INTRADAY_MARKET_STATS_ADDRESS = "http://www.cboe.com/data/current-market-statistics";
	static public final String CBOE_TOTAL_PUTCALL_FULLHISTORY_ADRESS = "http://www.cboe.com/publish/scheduledtask/mktdata/datahouse/totalpc.csv";
	static public final String CBOE_ETP_PUTCALL_FULLHISTORY_ADRESS = "http://www.cboe.com/publish/scheduledtask/mktdata/datahouse/etppc.csv";
	static public final String CBOE_EQUITY_PUTCALL_FULLHISTORY_ADRESS = "http://www.cboe.com/publish/scheduledtask/mktdata/datahouse/equitypc.csv";
	static public final String CBOE_INDEX_PUTCALL_FULLHISTORY_ADRESS = "http://www.cboe.com/publish/scheduledtask/mktdata/datahouse/indexpc.csv";
	static public final String CBOE_VIX_PUTCALL_FULLHISTORY_ADRESS = "http://www.cboe.com/publish/scheduledtask/mktdata/datahouse/vixpc.csv";
	
	//Corporate Ethics
	static public final String CORPORATE_CRITIC_ADDRESS = "http://corporatecritic.org";
	
	//-------------------------------------------------
	static public final String API_PATH = "/api/v1";
	
	public static String coronaSim =API_PATH+"/anydata/?colName=coronaSim2";
	
	//API CALLS
	static public final String GET_STOCK_IDS_BY_EXCHANGE = API_PATH + "/stocks/list?exchange=";
	static public final String GET_STOCK_IDS_BY_SECTOR = API_PATH + "/fundamentalcalcs/listSector?sector=";
	
	static public final String MARKET_TIMING_SIGNALS_CALL = API_PATH + "/marketsignals/";
	static public final String MARKET_TIMING_UPDATE_SIGNALS_CALL = API_PATH + "/marketsignals/update/";
	static public final String SMOOTHED_MARKET_TIMING_STATE_CALL = API_PATH + "/smoothedMarketSignals/";
	static public final String SMOOTHED_MARKET_TIMING_STATE_UPDATE_CALL = API_PATH + "/smoothedMarketSignals/update/";
	//TODO schema and stuff
	public static final String FUNDAMENTAL_BACKTEST_EXCHANGE = API_PATH + "/anydata/?colName=fundamentalexchangebacktestresults";
	public static final String FUNDAMENTAL_BACKTEST_SECTOR = API_PATH + "/anydata/?colName=fundamentalsectorbacktestresults";
	public static final String FUNDAMENTAL_BACKTEST_MARKETCAP = API_PATH + "/anydata/?colName=fundamentaltmarketcapbacktestresults";
	static public final String MARKET_TIMING_BACKTEST_CALL = API_PATH + "/anydata/?colName=markettimingbacktestresults";
	static public final String MARKET_TIMING_TOTAL_CALL = API_PATH + "/anydata/?colName=smoothedAndFilteredSignals";
	
	static public final String STOCK_CALL = API_PATH + "/stocks/";
	static public final String ETF_CALL = API_PATH + "/etfs/";
	static public final String FUNDAMENTAL_CALL = API_PATH + "/fundamentalcalcs/";
	static public final String HISTORICAL_QUOTES_CALL = API_PATH + "/historicalQuotes/";
	static public final String COMPANY_CALL = API_PATH + "/companies/";
	
	
	
	
	//PC data
	static public final String CBOE_PUTCALL_HISTORY_CALL = API_PATH + "/pc/";
	static public final String CBOE_INTRADAY_PUTCALL_HISTORY_CALL = API_PATH + "/intradayPC";
	
	//Darkpools
	static public final String DIX_CALL = API_PATH + "/dix/";
	
	//Sentiment data
	static public final String TSP_SENTIMENT_CALL = API_PATH + "/tsp/";
	static public final String NAAIM_SENTIMENT_CALL = API_PATH + "/naaim/";
	static public final String NDR_SENTIMENT_CALL = API_PATH + "/ndr/";
	
	//Tools
	static public final String TOOLS_CALL = API_PATH + "/tools/";
	static public final String UPDATE_STOCKS_FOR_EXCHANGE_CALL = TOOLS_CALL + "/updateStocksForExchange/";
	public static final String SORT_SIGNALS_CALL = TOOLS_CALL + "/sortsignals";
	
	
	//patterns
	static public final String UPDATE_DAILY_PATTERNS_FOR_EXCHANGE_CALL = TOOLS_CALL +"/updateFoundPattern/";
	static public final String UPDATE_PATTERN_BACKTEST_FOR_EXCHANGE_CALL = TOOLS_CALL +"/updateBacktestPattern/";
	static public final String UPDATE_DAILY_TOP_STOCK_FOR_EXCHANGE_CALL = TOOLS_CALL +"/updateDailyTopStock/";
	public static final String GET_PATTERN_BACKTEST_BY_EXCHANGE = API_PATH+"/backtestPattern/";
	public static final String GET_DAILY_PATTERNS_BY_EXCHANGE = API_PATH+"/foundPattern/";
	public static final String GET_DAILY_TOP_STOCK_BY_EXCHANGE = API_PATH+"/dailyTopStock/";
	public static final String GET_PATTERNS_BY_STOCK_ID = API_PATH+"/patternsPerStock/";
	public static final String GET_PATTERNS_BY_STOCKS = API_PATH+"/patternsPerStock/";
	
	public static final String GET_PREDICTION_CHANNEL_BY_STOCK_ID = API_PATH+"/predictionChannels/";
	public static final String POST_PREDICTION_CHANNEL_BY_STOCK_ID = API_PATH+"/predictionChannels/";
	
	//currencies
	public static final String GET_EXCHANGE_RATE = API_PATH+"/tools/getExchangeRate/";
	
	
	
	//ethics
	static public final String ETHICS_CALL = API_PATH +"/ethics/";
	static public final String CORP_CRIT_CALL = API_PATH +"/corpCritic/";

	//request handling
	public static final String JAVA_FINISHED = TOOLS_CALL+"javaReply";

	
	
	
	
}
