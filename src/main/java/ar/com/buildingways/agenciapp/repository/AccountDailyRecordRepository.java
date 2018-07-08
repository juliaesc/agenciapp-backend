package ar.com.buildingways.agenciapp.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.buildingways.agenciapp.dao.AccountDailyRecordDao;
import ar.com.buildingways.agenciapp.model.Account;
import ar.com.buildingways.agenciapp.model.AccountDailyRecord;

@Repository("accountDailyRecordRepository")
public interface AccountDailyRecordRepository extends CrudRepository<AccountDailyRecord, Integer>, AccountDailyRecordDao {

	Collection<AccountDailyRecord> findByAccount(Account account);
	
}