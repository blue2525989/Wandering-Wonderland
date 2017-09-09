package com.wanderingwonderland.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("aboutService")
public class AboutService {

	public List<String> aboutPageContent() {
		List<String> model = new ArrayList<String>();
		String content = "Thanks for checking out Wandering Wonderland! I am proud to "
				+ "offer you unique and vintage accessories from around the world. All of "
				+ "my items usually are the only one I have, so when they are gone they are gone! "
				+ "<br/><br/>"
				+ "I have been running my eBay store for several years now and enjoy having the "
				+ "extra income it provides. I have a beautiful family that supports my passions "
				+ "whether it be finding wonderful cloths to brighten peoples days with or working "
				+ "on our farm. We work together as a family unit."
				+ "<br/><br/>"
				+ "Be sure to also check out <a href='http://tickingtoybomb.com/'>Ticking Toy Bomb</a> "
				+ "for some amazingly unique jewelry upcycled from old toy parts!";
		model.add(content);
		return model;
	}
}
