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
@RequestMapping("/")
public class AppController {
	
	@Autowired
	AccountDailyRecordService accountDailyRecordService;
	
	@Autowired
	UserService userService;
	
	/** Login de la aplicación.
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
	 *	Actualiza las tablas Users, UserDetails y Accounts.
	 */
	@GetMapping(value="/updateUsers")
	public void updateUsers() {
		userService.updateUsers();
	}
	
	/** Recupera diariamente los movimientos de cuenta de los agencieros.
	 *	Actualiza la tabla AccountDailyRecords.
	 */
	@GetMapping(value="/updateAccountDailyRecords")
	public void updateAccountDailyRecords() {
		accountDailyRecordService.updateAccountDailyRecords();
	}

	/** Recupera los movimientos de cuenta desglosados por juego y evento del agenciero logueado 
	 *  y la fecha de vencimiento para su pago.
	 *  Consulta la tabla AccountDailyRecords.
	 */
	@GetMapping(value="/getAccountDailyRecords")
	public Collection<AccountDailyRecord> getAccountDailyRecords() {
		// Cuando se implemente lo de seguridad desde el front, se enviará como parámetro
		// el usuario que está logueado.
		User user = new User();
		user.setId(1);
		return accountDailyRecordService.getAccountDailyRecords(user);
	}
	
	/** Recupera el monto diario a liquidarse de la cuenta del agenciero.
	 *  Consulta la tabla AccountDailyRecords.
	 */
	@GetMapping(value="/getAccountDailySettlement")
	public double getAccountDailySettlement() {
		// Cuando se implemente lo de seguridad desde el front, se enviará como parámetro
		// el usuario que está logueado.
		User user = new User();
		user.setId(1);
		return accountDailyRecordService.getAccountDailySettlement(user);
	}

}