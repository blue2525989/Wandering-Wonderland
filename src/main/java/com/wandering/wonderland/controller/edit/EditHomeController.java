package com.wandering.wonderland.controller.edit;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wandering.wonderland.controller.PermissionController;
import com.wandering.wonderland.model.CardContent;
import com.wandering.wonderland.model.FeatureContent;
import com.wandering.wonderland.model.Image;
import com.wandering.wonderland.model.JumbotronContent;
import com.wandering.wonderland.repository.CardContentRepository;
import com.wandering.wonderland.repository.FeatureContentRepository;
import com.wandering.wonderland.repository.ImageRepository;
import com.wandering.wonderland.repository.JumboTronRepository;

@Controller
public class EditHomeController extends PermissionController {
	

	// instance of Repositories
	private JumboTronRepository jumbo;
	private CardContentRepository card;
	private FeatureContentRepository feature;
	private ImageRepository imgRepo;
	
	// autowire the repository to the controller
	@Autowired
	public EditHomeController(JumboTronRepository jumbo, ImageRepository imgRepo,
			CardContentRepository card,
			FeatureContentRepository feature) {
		this.jumbo = jumbo;
		this.card = card;
		this.feature = feature;
		this.imgRepo = imgRepo;
	}

	@RequestMapping("/edit-home")
	public String admin(HttpSession session, Model model) {
		
		// adds full list from gallery
		// need to work on slimming down list.
		List<Image> imageList = imgRepo.findAll();
		if (imageList != null) {
			session.setAttribute("imageList", imageList);
		}
		// admin user
		if (hasAdminRole()) {
			session.setAttribute("adminrole", hasAdminRole());
		}
		// regular user
		else if (hasUserRole()) {
			session.setAttribute("userrole", hasUserRole());
		}		
		return "admin/edit-home";
	}
	

	/* for editing jumbo content */
	
	@PostMapping(path="/edit-home/edit-jumbo")
	// request params to save
	public String addNewJumbo(Model model, @RequestParam String headline
			, @RequestParam String content, @RequestParam String url) {

		JumbotronContent jumboContent = new JumbotronContent();
		jumboContent.setHeadline(headline);
		jumboContent.setContent(content);
		jumboContent.setUrl(url);
		jumbo.save(jumboContent);
		return "redirect:/edit-home";
	}
	
	// delete element
	@GetMapping(path="/delete-jumbo")
	public String deleteSingleJumbo(Long ID, HttpSession session) {
		jumbo.delete(ID);
		// add javaScript document pop notifcation
		String saved = "The jumbotron with ID " + ID + " has been deleted.";
		session.setAttribute("saved", saved);
		return "redirect:/saved";
	}
	
	// list all element
	@RequestMapping("/list-jumbo")
	public String listAllUpdates(Model model) {
		Iterable<JumbotronContent> jumboList = jumbo.findAll();
		// Iterator iter = jumboList.iterator();
		if (jumboList != null) {
			model.addAttribute("listMain", jumboList);
		}	
		return "admin/list-all";
	}

	/* for editing card content */
	
	@PostMapping(path="/edit-home/edit-card1")
	// request params to save
	public String addNewCard1(Model model, @RequestParam String headline
			, @RequestParam String content,
			@RequestParam String type) {

		CardContent cardNew = new CardContent();
		cardNew.setHeadline(headline);
		cardNew.setContent(content);
		cardNew.setType(type);
		card.save(cardNew);
		return "redirect:/edit-home";
	}
	
	// delete element
	@GetMapping(path="/delete-card")
	public String deleteCard(Long ID, HttpSession session) {
		card.delete(ID);
		// add javaScript document pop notifcation
		String saved = "The card with ID " + ID + " has been deleted.";
		session.setAttribute("saved", saved);
		return "redirect:/saved";
	}
		
	// list all element
	@RequestMapping("/list-card")
	public String listAllcards(Model model) {
		List<CardContent> cardList = card.findAll();
		// Iterator iter = jumboList.iterator();
		if (cardList != null) {
			model.addAttribute("listMain", cardList);
		}	
		return "admin/list-all-type";
	}
	
	/* for editing feature content */
	
	@PostMapping(path="/edit-home/edit-feature")
	// request params to save
	public String addNewFeature(Model model, @RequestParam String headline
			, @RequestParam String content, @RequestParam String url) {

		FeatureContent featureMain = new FeatureContent();
		featureMain.setHeadline(headline);
		featureMain.setContent(content);
		featureMain.setUrl(url);
		feature.save(featureMain);
		return "redirect:/edit-home";
	}
	
	// delete element
	@GetMapping(path="/delete-feature")
	public String deleteFeature(Long ID, HttpSession session) {
		feature.delete(ID);
		// add javaScript document pop notifcation
		String saved = "The feature with ID " + ID + " has been deleted.";
		session.setAttribute("saved", saved);
		return "redirect:/saved";
	}
		
	// list all element
	@RequestMapping("/list-feature")
	public String listAllFeatures(Model model) {
		List<FeatureContent> featureList = feature.findAll();
		// Iterator iter = jumboList.iterator();
		if (featureList != null) {
			model.addAttribute("listMain", featureList);
		}	
		return "admin/list-all";
	}
	
	
}
