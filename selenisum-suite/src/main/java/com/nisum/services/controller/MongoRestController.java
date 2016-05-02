package com.nisum.services.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;


@RestController
@RequestMapping(value="/mongo")
public class MongoRestController {

	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ResponseEntity<Map> getGreeting(@PathVariable String name) {
				Map<String,String> m = new HashMap<String,String>();
				m.put("name", name);
				System.out.println("GET:"+name);
		return new ResponseEntity<Map>(m, HttpStatus.ACCEPTED);
	}

	
	@RequestMapping(value = "/postjson", method = RequestMethod.POST,produces="application/json", consumes="application/json")
	public ResponseEntity<String> postJsonTest(
		    @RequestBody(required = true) String strPostRequest) {
		
		String strPost=strPostRequest;
		
		System.out.println("POST:"+strPost);
			
			return new ResponseEntity<String>(strPost, HttpStatus.CREATED);
	}
	

	
	

	
}