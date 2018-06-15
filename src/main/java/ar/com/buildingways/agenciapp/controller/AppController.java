package ar.com.buildingways.agenciapp.controller;

import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.buildingways.agenciapp.model.AccountDailyRecord;
import ar.com.buildingways.agenciapp.model.User;
import ar.com.buildingways.agenciapp.service.AccountDailyRecordService;
import ar.com.buildingways.agenciapp.service.UserService;

@RestController
@RequestMapping("/agenciapp")
public class AppController {
	
	@Autowired
	AccountDailyRecordService accountDailyRecordService;
	
	@Autowired
	UserService userService;
	
	/** Login de la aplicación.
	 *
	 */
	@PostMapping(value="/login", produces="application/json")
	public ResponseEntity<User> login(@RequestBody User user) {
		User res = null;
		if(user!=null) {
			res = new User();
			res.setUsername(user.getUsername());
			res.setEnabled(user.getUsername() == 723204 && user.getPassword().equals("antonelli"));
		}
		return new ResponseEntity<User>(res, HttpStatus.OK);
	}
	
	/** Recupera diariamente los datos de los usuarios habilitados para usar la aplicación.
	 *	Actualiza la tabla Users, UserDetails y Accounts.
	 */
	@GetMapping(value="/loadUsers")
	public void loadUsers() {
		userService.loadUsers();
	}
	
	/** Recupera diariamente los movimientos de cuenta de los usuarios habilitados para usar la aplicación.
	 *	Actualiza la AccountDailyRecords.
	 */
	@GetMapping(value="/loadAccountDailyRecords/{currentDate}")
	public Collection<AccountDailyRecord> loadAccountDailyRecords(@PathVariable("currentDate") String date) {
		DateTime currentDate = new DateTime(Integer.parseInt(date.substring(0, 4)), 
				Integer.parseInt(date.substring(4, 6)), 
				Integer.parseInt(date.substring(6, 8)), 0, 0, 0, 0);
		return accountDailyRecordService.loadAccountDailyRecords(currentDate);
	}

	/** Consulta el resultado de los movimientos de cuenta para un agenciero en el día de la fecha.
	 *	Consulta la tabla AccountDailyRecords.
	 */
	@GetMapping(value="/getGlobalAccountDailyRecord/{username}/{currentDate}")
	public Collection<AccountDailyRecord> getAccountDailyRecord(@PathVariable("username") int username, 
														  		@PathVariable("currentDate") String date) {
		DateTime currentDate = new DateTime(Integer.parseInt(date.substring(0, 4)), 
											Integer.parseInt(date.substring(4, 6)), 
											Integer.parseInt(date.substring(6, 8)), 0, 0, 0, 0);
		return accountDailyRecordService.getGlobalAccountDailyRecord(username, currentDate);
	}
	
	/** Consulta el desglose de movimientos de cuenta por juego y evento para un agenciero en el día de la fecha.
	 *	Consulta la tabla AccountDailyRecords.
	 */
	@GetMapping(value="/getItemizedAccountDailyRecord/{username}/{currentDate}")
	public Collection<AccountDailyRecord> getAccountDailyRecordByGame(@PathVariable("username") int username, 
														  			  @PathVariable("currentDate") String date) {
		DateTime currentDate = new DateTime(Integer.parseInt(date.substring(0, 4)), 
				Integer.parseInt(date.substring(4, 6)), 
				Integer.parseInt(date.substring(6, 8)), 0, 0, 0, 0);
		return accountDailyRecordService.getItemizedAccountDailyRecord(username, currentDate);
	}

}