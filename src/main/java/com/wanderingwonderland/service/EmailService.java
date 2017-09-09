package com.wanderingwonderland.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
@Qualifier("emailService")
public class EmailService {
    static final String TO = "mywanderingwonderland@gmail.com";
    
    @Autowired
    BasicAWSCredentials creds;
    
    public void sendMail(String name, String surname, String email, 
    	    String phone, String message) {

    	if (phone == null || phone == "") {
    		phone = "no phone #";
    	}
    	SendEmailRequest request = new SendEmailRequest()
 		       .withSource(TO);
 		
 		List<String> toAddresses = new ArrayList<String>();
 		toAddresses.add(TO);
 		Destination dest = new Destination().withToAddresses(toAddresses);
 		
 		// set fromEmail as BCC
 		List<String> toBccAddresses = new ArrayList<String>();
 		toBccAddresses.add(TO);
 		dest.setBccAddresses(toBccAddresses);
 		request.setDestination(dest);
 		
 		Content subjContent = new Content().withData(phone +" "+ name +" "+ surname +" "+ email);
 		Message msg = new Message().withSubject(subjContent);
 		
 		// Include a body in HTML formats.
 		Content htmlContent = new Content().withData(phone +"<br/>"+ name +"<br/>"+ surname +"<br/>"+ email
 				+"<br/><br/><br/>"+ message);
 		Body body = new Body().withHtml(htmlContent);
 		msg.setBody(body);
 		
 		request.setMessage(msg);
 		
 		// Set AWS access credentials.
 		@SuppressWarnings("deprecation")
		AmazonSimpleEmailServiceClient client =
 		       new AmazonSimpleEmailServiceClient(creds).withRegion(
 		            				  Regions.US_WEST_2);
 		
 		// Call Amazon SES to send the message. 
 		try {
 		   client.sendEmail(request);
 		
 		} catch (AmazonClientException e) {
 		   e.printStackTrace();
 		} catch (Exception e) {
 		   e.printStackTrace();
 		}
    }
    
    public List<String> emailPageContent() {
    	List<String> model = new ArrayList<String>();
    	String content = "See something on the eBay store and interested in knowing more? "
    			+ "Feel free to send me a message and I will be happy to answer any questions "
    			+ "you may have about the item."
    			+ "<br/><br/>"
    			+ "Know what you want but didn't see it listed? Let me know and I can check my inventory "
    			+ "to see if I have something similar.";
    	model.add(content);
    	return model;
    }
}
