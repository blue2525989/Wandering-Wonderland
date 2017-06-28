package com.wandering.wonderland.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wandering.wonderland.model.ContactFeatureContent;
import com.wandering.wonderland.model.JumbotronContent;
import com.wandering.wonderland.repository.ContactFeatureContentRepository;

@Controller
public class ContactController extends PermissionController {

	// instance of Repositories
	private ContactFeatureContentRepository feature;
	
	// autowire the repository to the controller
	@Autowired
	public ContactController(ContactFeatureContentRepository feature) {
		this.feature = feature;
	}
	
	
	@RequestMapping("/contact")
	public String contact(HttpSession session) {
		// adds last jumbo 
		JumbotronContent jumboMain = findLastJumbo();		
		if (jumboMain != null) {
			session.setAttribute("jumboMain", jumboMain);
		}
		findLastContactFeature(session);
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
		}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}
		return "contact/contact";
	}
	
	public void findLastContactFeature(HttpSession session) {
		long size = feature.count();
		if (size > 0) {
			session.setAttribute("contactMain", feature.getOne(size));
		} else {
			ContactFeatureContent feature = new ContactFeatureContent();
			feature.setHeadline("Blue's website and software design");
			String content = "Welcome to Blue's website and software design. This site is brand new "
					+ "and is the model for the site I hope to market. Everything is self contained "
					+ "and can be edited from the administration account. If your interesterd, send "
					+ "me an email on the contact page.";
			feature.setContent(content);
			feature.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/system/banner01.jpg");
			session.setAttribute("contactMain", feature);
		}
	}
}
