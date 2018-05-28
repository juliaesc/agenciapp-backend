package ar.com.buildingways.agenciapp.service

import ar.com.buildingways.agenciapp.model.AccountActivity
import ar.com.buildingways.agenciapp.model.User
import ar.com.buildingways.agenciapp.service.AccountActivityService
import org.joda.time.DateTime
import java.sql.Date
import spock.lang.Specification

class UserServiceTest extends Specification {

	def "Seteando un username y pass a un User, obtenemos el username y password seteados"() {
		
		given:
			User user = new User()
		
		when:
			user.setUsername(723204)
			user.setPassword("pass")
		
		then:
			user.getUsername() == 723204
			user.getPassword().equals("pass")
	
	}
	
	def "Dado el legajo 723204 y la fecha 6/11/2017, ejecuto un método y me devuelve el estado de su cuenta corriente"() {
		
		given:
			AccountActivityService accountActivityService = new AccountActivityServiceImpl()
			Collection<AccountActivity> accountActivities = new ArrayList<AccountActivity>()
			int username = 723204
			DateTime currentDate = new DateTime(2017, 11, 6, 0, 0, 0, 0)
			accountActivities = accountActivityService.getAccountActivity(username, currentDate)
				
		when:
			accountActivities.size()
		
		then:
			 113
	
	}
}
