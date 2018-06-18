package ar.com.buildingways.agenciapp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	/** Actualiza diariamente los datos de los usuarios habilitados para usar la aplicación
	 *	que se encuentran en las tablas Users, UserDetails y Accounts.
	 */
	@GetMapping(value="/updateUsers")
	public void updateUsers() {
		userService.updateUsers();
	}
	
	/** Actualiza diariamente los movimientos de cuenta de los usuarios habilitados para usar la aplicación
	 *	que se encuentran en la tabla AccountDailyRecords.
	 */
	@GetMapping(value="/updateAccountDailyRecords")
	public void updateAccountDailyRecords() {
		accountDailyRecordService.updateAccountDailyRecords();
	}

	/** Consulta el resultado de los movimientos de cuenta para un agenciero en el día de la fecha.
	 *	Consulta la tabla AccountDailyRecords.
	 */
	@GetMapping(value="/getGlobalAccountDailyRecord")
	public Collection<AccountDailyRecord> getAccountDailyRecord() { 
		return accountDailyRecordService.getGlobalAccountDailyRecord();
	}
	
	/** Consulta el desglose de movimientos de cuenta por juego y evento para un agenciero en el día de la fecha.
	 *	Consulta la tabla AccountDailyRecords.
	 */
	@GetMapping(value="/getItemizedAccountDailyRecord/{username}/{currentDate}")
	public Collection<AccountDailyRecord> getAccountDailyRecordByGame() {
		return accountDailyRecordService.getItemizedAccountDailyRecord();
	}

}