package com.wandering.wonderland.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wandering.wonderland.model.JumbotronContent;
import com.wandering.wonderland.model.ServicesContent;
import com.wandering.wonderland.repository.ServicesRepository;


@Controller
public class ServicesController extends PermissionController {
	

	// instance of Repositories
	private ServicesRepository service;
	
	// autowire the repository to the controller
	@Autowired
	public ServicesController(ServicesRepository service) {
		this.service = service;
	}

	@RequestMapping("/services")
	public String services(HttpSession session) {
		// adds last jumbo 
		JumbotronContent jumboMain = findLastJumbo();		
		if (jumboMain != null) {
			session.setAttribute("jumboMain", jumboMain);
		}	
		// add last to services
		List<ServicesContent> mainTwo = findTwoLastServices();
		ServicesContent service1 = mainTwo.get(0);
		if (service1 != null) {
			session.setAttribute("service1", service1);
		}	
		ServicesContent service2 = mainTwo.get(1);
		if (service2 != null) {
			session.setAttribute("service2", service2);
		}	
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
			}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}
		return "services/services";
	}
	
	public List<ServicesContent> findTwoLastServices() {
		List<ServicesContent> services = service.findAll();
		if (services.size()-1 > 0) {
			List<ServicesContent> mainTwo = new ArrayList<>();
			for (int i = services.size()-1; i >= 0; i--) {
				if (mainTwo.size() == 2) {
					return mainTwo;
				}
				else if (mainTwo.size() == 0) {
					if (services.get(i).getType().equals("top")) {
						mainTwo.add(services.get(i));
					}
				} else if (mainTwo.size() == 1) {
					if (services.get(i).getType().equals("bottom")) {
						mainTwo.add(services.get(i));
					}
				}
			
			}
			return mainTwo;
		} else {
			ServicesContent s1 = new ServicesContent();
			ServicesContent s2 = new ServicesContent();
			s1.setHeadline("Blue's website and software design");
			String content = "Welcome to Blue's website and software design. This site is brand new "
					+ "and is the model for the site I hope to market. Everything is self contained "
					+ "and can be edited from the administration account. If your interesterd, send "
					+ "me an email on the contact page.";
			s1.setContent(content);
			s1.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/computer-02.jpg");
			s2.setHeadline("Blue's website and software design");
			s2.setContent(content);
			s2.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/computer-02.jpg");
			services.add(s1);
			services.add(s2);
		}
		return services;
	}
	
}
