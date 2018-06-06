package ar.com.buildingways.agenciapp.service

import ar.com.buildingways.agenciapp.model.User
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
	
}