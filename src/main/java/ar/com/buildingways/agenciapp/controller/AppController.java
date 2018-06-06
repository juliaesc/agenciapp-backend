package ar.com.buildingways.agenciapp.controller;

import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;
import ar.com.buildingways.agenciapp.service.AccountDailyRecordService;
import ar.com.buildingways.agenciapp.service.UserService;

@RestController
@RequestMapping("/agenciapp")
public class AppController {
	
	@Autowired
	AccountDailyRecordService accountDailyRecordService;
	
	@Autowired
	UserService userService;

	@GetMapping(value="/getAccountDailyRecord/{username}/{currentDate}")
	public Collection<AccountDailyRecord> getAccountDailyRecord(@PathVariable("username") int username, 
														  		@PathVariable("currentDate") String date) {
		DateTime currentDate = new DateTime(Integer.parseInt(date.substring(0, 4)), 
											Integer.parseInt(date.substring(4, 6)), 
											Integer.parseInt(date.substring(6, 8)), 0, 0, 0, 0);
		return accountDailyRecordService.getAccountDailyRecord(username, currentDate);
	}
	
	@GetMapping(value="/getAccountDailyRecordByGame/{username}/{currentDate}")
	public Collection<AccountDailyRecord> getAccountDailyRecordByGame(@PathVariable("username") int username, 
														  			  @PathVariable("currentDate") String date) {
		DateTime currentDate = new DateTime(Integer.parseInt(date.substring(0, 4)), 
				Integer.parseInt(date.substring(4, 6)), 
				Integer.parseInt(date.substring(6, 8)), 0, 0, 0, 0);
		return accountDailyRecordService.getAccountDailyRecordByGame(username, currentDate);
	}

}