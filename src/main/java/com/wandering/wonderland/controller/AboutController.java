package com.wandering.wonderland.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wandering.wonderland.model.AboutContent;
import com.wandering.wonderland.model.JumbotronContent;
import com.wandering.wonderland.repository.AboutContentRepository;

@Controller
public class AboutController extends PermissionController {
	
	// instance of Repositories
	private AboutContentRepository about;
	// autowire the repository to the controller
	@Autowired
	public AboutController(AboutContentRepository about) {
		this.about = about;
	}

	
	@RequestMapping("/about")
	public String about(HttpSession session) {
		// adds last jumbo 
		JumbotronContent jumboMain = findLastJumbo();		
		if (jumboMain != null) {
			session.setAttribute("jumboMain", jumboMain);
		}
		// try just counting instead of returning full list to speed up times
		long size = about.count();
		if (size > 0) {
			AboutContent aboutCon = about.getOne(size);
			if (aboutCon != null) {
				session.setAttribute("aboutMain", aboutCon);
			}
		} else {
			AboutContent aboutCon = new AboutContent();
			aboutCon.setHeadline("Blue's website and software design");
			String content = "Welcome to Blue's website and software design. This site is brand new "
					+ "and is the model for the site I hope to market. Everything is self contained "
					+ "and can be edited from the administration account. If your interesterd, send "
					+ "me an email on the contact page.";
			aboutCon.setContent(content);
			aboutCon.setUrl("https://s3-us-west-2.amazonaws.com/blue-company-images/system/banner01.jpg");
			session.setAttribute("aboutMain", aboutCon);
			
		}
		
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
		}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}
		return "about/about";
	}
}
