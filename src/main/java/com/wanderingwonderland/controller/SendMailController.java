package com.wanderingwonderland.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wanderingwonderland.model.EmailData;
import com.wanderingwonderland.service.EmailService;

@RestController
public class SendMailController {

	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST, 
			consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public Map<String, Object> sendEmail(@RequestBody EmailData emailData) {
		Map<String, Object> model = new HashMap<String, Object>();
		String saved = "";
		try {
			emailService.sendMail(emailData.getName(), emailData.getSurname(), 
					emailData.getEmail(), emailData.getPhone(), emailData.getMessage());
			saved = "Thank you, " + emailData.getName() + " " + emailData.getSurname() 
			+ ", your message is on the way and will be responded to shortly!";
		} catch (Exception e) {
			saved = e.getMessage();
		} finally {
			model.put("saved", saved);
		}		
		return model;
	}
	
}
