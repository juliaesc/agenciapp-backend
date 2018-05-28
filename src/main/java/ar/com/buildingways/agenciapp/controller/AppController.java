package ar.com.buildingways.agenciapp.controller;

import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.buildingways.agenciapp.model.AccountActivity;
import ar.com.buildingways.agenciapp.service.AccountActivityService;

@RestController
@RequestMapping("/")
public class AppController {
	
	@Autowired
	AccountActivityService accountActivityService;

	@GetMapping(value="/getAccountActivity/{username}/{currentDate}")
	public Collection<AccountActivity> getAccountActivity(@PathVariable("username") int username, 
														  @PathVariable("currentDate") String date) {
		DateTime currentDate = new DateTime(2017, 11, 6, 0, 0, 0, 0);
		return accountActivityService.getAccountActivity(username, currentDate);
	}

}