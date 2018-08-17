package ar.com.buildingways.agenciapp.util;

public class SQLQueries {

	// SELECTS
	public static final String LOAD_USERS =
			"SELECT legajo,nombre,apellido,nombre_agencia,mail,domicilio,comisionista,cant_terminales," +
					"cuit,titular_cuenta,tipo_cuenta,n_cuenta,sucursal_banco,cbu,tiene_DA,porc_IIBB " +
			"FROM v_Users";
	public static final String SELECT_ACCOUNT_SETTLEMENT =
			"SELECT debt - credit + interest as total FROM account_daily_records where account_id = ?";

}
