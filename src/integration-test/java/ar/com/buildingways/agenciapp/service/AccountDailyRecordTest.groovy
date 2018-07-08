package ar.com.buildingways.agenciapp.service

import java.sql.*;
import groovy.sql.Sql
import ar.com.buildingways.agenciapp.model.AccountDailyRecord
import ar.com.buildingways.agenciapp.repository.AccountDailyRecordRepository;
import ar.com.buildingways.agenciapp.model.User
import org.joda.time.DateTime
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import spock.mock.DetachedMockFactory
import java.util.ArrayList
import java.util.Iterator
import ar.com.buildingways.agenciapp.dao.AccountDailyRecordDao
import ar.com.buildingways.agenciapp.dao.AccountDailyRecordDaoImpl
import ar.com.buildingways.agenciapp.AgenciAppBackendApplication

@SpringBootTest
class AccountDailyRecordTest extends Specification {
	
//	User user = new User()
//	AccountDailyRecord accountDailyRecord = new AccountDailyRecord()
//	AccountDailyRecord accountDailyRecordDB  = new AccountDailyRecord()
//	AccountDailyRecordRepository accountDailyRecordRepository = Mock(AccountDailyRecordRepository)
//	Collection<AccountDailyRecord> accountDailyRecordList = new ArrayList<AccountDailyRecord>()
//	Collection<AccountDailyRecord> accountDailyRecordDBList = new ArrayList<AccountDailyRecord>()
//	@Autowired
//	AccountDailyRecordService accountDailyRecordService = Mock(AccountDailyRecordService)
//	
//	def setup() {
//		accountDailyRecord.setAccount(user.setId(1))
//		accountDailyRecord.setDebt(100.00)
//		accountDailyRecord.setCredit(50.00)
//		accountDailyRecord.setInterest(1.00)
//		accountDailyRecordList.add(accountDailyRecord)
//		accountDailyRecordRepository.saveAll(accountDailyRecordList)
//	}
//	
//	def "Al guardar los movimientos de cuenta de un usuario en la BD, cuando los recupero me devuelve los mismos movimientos."() {
//				
//		when:
//			accountDailyRecordDBList = zzaccountDailyRecordRepository.findByAccount(user.getAccount())
//		then:
//			Iterator<Object[]> it = accountDailyRecordDBList.iterator()
//			while (it.hasNext()) {
//				accountDailyRecordDB = it.next()
//				accountDailyRecordDB.equals(accountDailyRecord)
//			}
//	}
//	
//	def "Al recuperar los movimientos de cuenta de un usuario y calcular la liquidación, coincide con la recuperada de la BD."() {
//		
//		given:
//			Double tds = accountDailyRecord.getDebt() - accountDailyRecord.getCredit() + accountDailyRecord.getInterest()
//		when:
//			Double tdsDB = accountDailyRecordService.getAccountDailySettlement(user)
//		then:
//			tdsDB == tds
//	}
//	
//	@TestConfiguration 
//	static class Config { 
//		
//		private DetachedMockFactory factory = new DetachedMockFactory() 
//		
//		@Bean 
//		AccountDailyRecordService accountDailyRecordService() { 
//			factory.Mock(AccountDailyRecordService) 
//		} 
//	}
//	
//	@TestConfiguration 
//	static class MockConfig { 
//		def detachedMockFactory = new DetachedMockFactory(); 
//		@Bean AccountDailyRecordService accountDailyRecordService() { 
//			return detachedMockFactory.Stub(AccountDailyRecordService) 
//		} 
//	}
}