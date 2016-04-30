package com.Driver;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Generic_Functions {
	
	private final static Logger logger = LoggerFactory.getLogger(Generic_Functions.class);

	public static void keywordDriver(String vKeyword,Xls_Reader xtd,String vModuleName,int m,html_result hr) throws Throwable
	{
		String vObjExclValue,ObjString,vExp,vVal;
		int vObjCnt,vObjIndex;
		List<WebElement> vObjAct;
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(DriverScript.getPath("/Objects/Objects.properties"));
		prop.load(fis);
		switch (vKeyword)
		{
		case "Fn_LaunchApp":
			Fn_LaunchApp();
			break;
		case "Fn_Wait":
			Fn_Wait();
			break;	
		case "Fn_VerifyTitle":
			vExp=xtd.getCellData(vModuleName, "Expected", m);
			Fn_VerifyTitle(vExp,hr);
			break;
		case "Fn_ObjectExist":
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);					
			ObjString=prop.getProperty(vObjExclValue);
			vObjCnt=ObjectExist(ObjString);
			Fn_ObjectExist(vObjCnt,hr);
			break;
		case "Fn_SetValue":
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);
			vVal=xtd.getCellData(vModuleName, "Value", m);
			vObjIndex=Integer.parseInt(xtd.getCellData(vModuleName, "Index", m));
			ObjString=prop.getProperty(vObjExclValue);
			vObjAct=ExtractObject(ObjString);
			vObjCnt=ObjectExist(ObjString);
			Fn_SetValue(vObjAct,vVal,vObjCnt,vObjIndex,hr);
			break;
		
		case "Fn_SelectValue":
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);
			vVal=xtd.getCellData(vModuleName, "Value", m);
			vObjIndex=Integer.parseInt(xtd.getCellData(vModuleName, "Index", m));
			ObjString=prop.getProperty(vObjExclValue);
			vObjAct=ExtractObject(ObjString);
			vObjCnt=ObjectExist(ObjString);
			Fn_SelectValue(vObjAct,vVal,vObjCnt,vObjIndex,hr);
			break;
		case "Fn_Click":
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);
			vObjIndex=Integer.parseInt(xtd.getCellData(vModuleName, "Index", m));	
			ObjString=prop.getProperty(vObjExclValue);
			vObjAct=ExtractObject(ObjString);
			vObjCnt=ObjectExist(ObjString);
			Fn_Click(vObjAct,vObjCnt,vObjIndex,hr);
			break;
		case "Fn_MouseMove":			
			vObjExclValue=xtd.getCellData(vModuleName, "Object1", m);
			vObjIndex=Integer.parseInt(xtd.getCellData(vModuleName, "Index", m));	
			ObjString=prop.getProperty(vObjExclValue);
			vObjAct=ExtractObject(ObjString);
			vObjCnt=ObjectExist(ObjString);
			 Fn_MouseMove(vObjAct,vObjCnt,hr);	
			break;
		default:
			logger.info("Wrong Keyword");
		}
	}
	public static void Fn_LaunchApp()
	{
		DriverScript.driver.get(DriverScript.vProjectUrl);
		DriverScript.driver.manage().window().maximize();
		logger.info(DriverScript.vProjectUrl);
		//DriverScript.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public static void Fn_Wait()
	{
		DriverScript.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public static void Fn_VerifyTitle(String vExp,html_result hr) throws Throwable
	{
		String actTitle=DriverScript.driver.getTitle();
		if(actTitle.equals(vExp))
		{
			logger.info("PASS");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_VerifyTitle", "Title should be :"+vExp, "Title matched successfully :"+actTitle, "PASS");
		}
		else
		{
			logger.info("FAIL");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_VerifyTitle", "Title should be :"+vExp, "Title not matched :"+actTitle, "FAIL");
		}
	}
	
	public static void Fn_ObjectExist(int vObjCnt,html_result hr) throws Throwable
	{
		if(vObjCnt==1)
		{
			logger.info("PASS");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_ObjectExist", "Object should be exist", "Object exist", "PASS");
		}
		else
		{
			logger.info("FAIL");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_ObjectExist", "Object should be exist", "Object does not exist", "FAIL");
		}
	}
	
	public static void Fn_SetValue(List<WebElement> vObj,String vVal,int vobjCnt,int vObjIndex,html_result hr) throws Throwable
	{
		if(vobjCnt>0)
		{
			vObj.get(vObjIndex).clear();
			vObj.get(vObjIndex).sendKeys(vVal);
			logger.info("PASS");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_SetValue", vVal+": should be entered successfully", "Value entered successfully", "PASS");
		}
		else
		{
			logger.info("FAIL");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_SetValue", vVal+": should be entered successfully", "Object does not found", "FAIL");
		}
		
	}
	
		public static void Fn_SelectValue(List<WebElement> vObj,String vVal,int vobjCnt,int vObjIndex,html_result hr) throws Throwable
	{
		if(vobjCnt>0)
		{
			
			vObj.get(vObjIndex).sendKeys(vVal);
			logger.info("PASS");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_SelectValue", vVal+": should be selected successfully", "Value selected successfully", "PASS");
		}
		else
		{
			logger.info("FAIL");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_SelectValue", vVal+": should be selected successfully", "Object does not found", "FAIL");
		}
		
	}
	
	public static void Fn_Click(List<WebElement> vObj,int vobjCnt,int objIndex,html_result hr) throws Throwable
	{
		if(vobjCnt>0)
		{
			
			vObj.get(objIndex).click();
			logger.info("PASS");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_Click", "object should be clickable", "Object selected successfully", "PASS");
		}
		else
		{
			logger.info("FAIL");
			hr.fgInsertResult(DriverScript.ResFilePath, "Fn_Click", "object should be clickable", "Object does not found", "FAIL");

		}
		
	}
	public static void Fn_MouseMove(List<WebElement> vObj,int vObjCnt,html_result hr) throws Throwable
	{						
		if(vObjCnt>0)
		{
			Actions act=new Actions(DriverScript.driver);
			act.moveToElement((WebElement) vObj).perform();			
			Thread.sleep(3000);
			logger.info("Pass");
			hr.fgInsertResult(DriverScript.ResFilePath, "Move Mouse", "Expected", "Actual", "PASS");
		}
		else
		{
			logger.info("Fail");
			hr.fgInsertResult(DriverScript.ResFilePath, "Move Mouse", "Expected", "Actual", "FAIL");
		}			
	}


	public static int ObjectExist(String vObjString)
	{
		int vObjCnt=0;			
		String[] arr=vObjString.split("@@@");	
		switch(arr[0])
		{
		case "xpath":
			vObjCnt=DriverScript.driver.findElements(By.xpath(arr[1])).size();
			break;
		case "id":
			vObjCnt=DriverScript.driver.findElements(By.id(arr[1])).size();
			break;
		case "name":
			vObjCnt=DriverScript.driver.findElements(By.name(arr[1])).size();
			break;
		case "linkText":
			vObjCnt=DriverScript.driver.findElements(By.linkText(arr[1])).size();
			break;
		case "partialLinkText":
			vObjCnt=DriverScript.driver.findElements(By.partialLinkText(arr[1])).size();
			break;
		case "cssSelector":
			vObjCnt=DriverScript.driver.findElements(By.cssSelector(arr[1])).size();
			break;	
		}		
		return vObjCnt;
	}

	
	public static List<WebElement> ExtractObject(String vObjString)
	{
		List<WebElement> vFindObj = null;
		
		String[] arr=vObjString.split("@@@");	
		switch(arr[0])
		{
		case "xpath":
			vFindObj=DriverScript.driver.findElements(By.xpath(arr[1]));
			break;
		case "id":
			vFindObj=DriverScript.driver.findElements(By.id(arr[1]));
			break;
		case "name":
			vFindObj=DriverScript.driver.findElements(By.name(arr[1]));
			break;
		case "linkText":
			vFindObj=DriverScript.driver.findElements(By.linkText(arr[1]));
			break;
		case "partialLinkText":
			vFindObj=DriverScript.driver.findElements(By.partialLinkText(arr[1]));
			break;
		case "cssSelector":
			vFindObj=DriverScript.driver.findElements(By.cssSelector(arr[1]));
			break;
		}	
		
		
		
		return vFindObj;
	}



}
