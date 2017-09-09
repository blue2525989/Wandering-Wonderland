package com.wanderingwonderland.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("homePageService")
public class HomeService {

	public List<String> homeContent() {
		List<String> model = new ArrayList<String>();
		String content = "We sell designer vintage to modern clothing and accessories. "
				+ "Be sure to check our ebay store often as we're always finding new "
				+ "treasures to share! Thanks for stopping by."
				+ "<br/><br/>"
				+ "<div align='center'><a target='_blank' href='http://www.ebay.com/usr/raindanceresale'>"
				+ "<img class='img-fluid' src='images/ebay-button.gif'/></a></div>";
		model.add(content);
		return model;
	}
}
