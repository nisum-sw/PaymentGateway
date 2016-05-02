package com.nisum.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nisum.domain.Report;
import com.nisum.service.WebDriverService;
import com.nisum.util.TestScenarios;
import com.thoughtworks.selenium.webdriven.commands.Type;

@Controller
public class SelenisumController {

	@Autowired
	private ServletContext context;

	@Autowired
	private WebDriverService driverScript;

	@RequestMapping(value = "/getElements", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> getElements(@RequestParam("input") String input) {
		input = URLDecoder.decode(input);
		WebDriver browser = new FirefoxDriver();
		browser.get(input);

		List<WebElement> link = browser.findElements(By.xpath("//*[@id]"));

		List<String> lst = new ArrayList<String>();
		JsonArray jArray = new JsonArray();
		for (WebElement ele : link) {
			lst.add(ele.getAttribute("id"));
			JsonObject jObj = new JsonObject();
			jObj.addProperty("name", ele.getAttribute("id"));
			jArray.add(jObj);
		}

		return new ResponseEntity<String>(jArray.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getTestScenario", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<String> getTestScenario(@RequestParam("input") String input) throws Exception {
		return new ResponseEntity<String>(TestScenarios.getTestSuite(), HttpStatus.OK);
	}

	@RequestMapping(value = "/executeTest", method = RequestMethod.POST, produces = "application/text")
	@ResponseBody
	public ResponseEntity<String> executeTest(@RequestBody String input) {
		String report = "No Report Generated";
		input = URLDecoder.decode(input);
		try {
			String reportPath = context.getRealPath("");

			report = driverScript.main(reportPath + "Results");

			// String pathArr[] = report.split("Results");
			// ("../../resources/DriverFiles/ProjectDriver.xlsx");
			// report= context.getContextPath()+"/Results"+ pathArr[1];
			// System.out.println(pathArr[1]);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(report, HttpStatus.OK);
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Report>> getReports(@RequestParam("input") String input) throws Exception {
		Iterable<Report> reportItr = driverScript.getReports();
		List<Report> reports = new ArrayList<Report>();
		reportItr.forEach(report -> reports.add(report));
		return new ResponseEntity<List<Report>>(reports, HttpStatus.OK);
	}

}
