package ar.com.buildingways.agenciapp
import java.sql.*;
import groovy.sql.Sql

class DBConnection {
	
	static void main(String[] args) {
		def sql = Sql.newInstance('jdbc:sqlserver://iplc086.iplyc.gov.ar:1433;databaseName=AgenciApp_DB',
		   'AG_USU_Admin', 'admin', 'com.microsoft.sqlserver.jdbc.SQLServerDriver')
			sql.eachRow('SELECT @@VERSION '){ row ->
		   println row[0]
		}
		sql.close()
	 }
}
