package ar.com.buildingways.agenciapp.util;

public class SQLQueries {

	// SELECTS
	public static final String LOAD_USERS = "SELECT * FROM v_Users";
	
	// STORED PROCEDURES
	public static final String LOAD_ACCOUNT_DAILY_RECORDS = "CC_LiquidacionPorJuegoEvento"; 
	
	// DELETES
	public static final String DELETE_ACCOUNT_DAILY_RECORDS = "TRUNCATE TABLE account_daily_records";
}
