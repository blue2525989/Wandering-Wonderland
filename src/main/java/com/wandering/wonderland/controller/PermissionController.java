package com.wandering.wonderland.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wandering.wonderland.model.Image;
import com.wandering.wonderland.model.JumbotronContent;
import com.wandering.wonderland.repository.ImageRepository;
import com.wandering.wonderland.repository.JumboTronRepository;

public class PermissionController {
	
	// instance of Repositories
	@Autowired
	private JumboTronRepository jumbo;
	private ImageRepository imgRepo;
	// autowire the repository to the controller
	

	
	/**
	 * This method checks the users authentication level
	 * @return true or false
	 */
	
	public boolean hasUserRole() {
		// this checks to see if a user has a user role
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean hasUserRole = authentication.getAuthorities().stream()
		         .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		return hasUserRole;
	}
	
	/**
	 * This method returns the users authentication level
	 * @return true or false
	 * 
	 */
	
	public boolean hasAdminRole() {
		// this checks to see if a user has a admin role.
		Authentication authentication2 = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = authentication2.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		return hasAdminRole;
	}

	
	public JumbotronContent findLastJumbo() {
		long size = jumbo.count();
		if (size <= 0) {
			JumbotronContent jumbo = new JumbotronContent();
			jumbo.setHeadline("Blue's website and software design");
			String content = "Welcome to Blue's website and software design. This site is brand new "
					+ "and is the model for the site I hope to market. Everything is self contained "
					+ "and can be edited from the administration account. If your interesterd, send "
					+ "me an email on the contact page.";
			jumbo.setContent(content);
			jumbo.setUrl("https://s3-us-west-2.amazonaws.com/wandering-wonderland-images/system/banner01.jpg");
			return jumbo;
		} else {
			return jumbo.getOne(size);
		}
	}
	
	// find type and adds to a list
	protected List<Image> findListofImages(String type) {
		List<Image> imageList = imgRepo.findAll();
		List<Image> realList = new ArrayList<Image>();
		for (int i = imageList.size()-1; i >= 0; i--) {
			if (imageList.get(i).getType().equalsIgnoreCase(type)) {
				realList.add(imageList.get(i));
			}
		}
		return realList;
	}
}
