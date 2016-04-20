package com.Driver;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverScript {

	public static WebDriver driver;
	public static String vProjectUrl,ResFilePath,vProjectName,vModuleName,vTCName,vTCDesc;
	public static int failval,Cntflag,passval,failcnt,actfails;
	
	public static String getPath(String str) throws UnsupportedEncodingException{
		String path = DriverScript.class.getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/com/Driver/");
		//("../../resources/DriverFiles/ProjectDriver.xlsx");
		System.out.println(fullPath);
		System.out.println(pathArr[0]);
		
		return pathArr[0]+str;
	}
	
	public static String main(String reportPath) throws Throwable {
	    String vProjectRun,vBrowserType,vDBName,vDBuser,vDBpassword,vModulesRun,vModuleFiles,vTestDataFiles,vTCRun,vTDSteps,vKeyword;
		int ProjectCnt,ModuleCnt,vTCCnt,vTDCnt,flag,vRowNum;
		
		
		
	    Xls_Reader xr=new Xls_Reader(getPath("/DriverFiles/ProjectDriver.xlsx"));
        ProjectCnt=xr.getRowCount("Projects");
        for(int i=2;i<=ProjectCnt;i++)
        {
        	vProjectRun=xr.getCellData("Projects", "Run", i);
        	if(vProjectRun.equalsIgnoreCase("ON"))
        	{
        		
        		
        		driver=new FirefoxDriver();
        		vProjectName=xr.getCellData("Projects", "ProjectName", i);
        		vProjectUrl=xr.getCellData("Projects", "ProjectUrl", i);
        		vModuleFiles=xr.getCellData("Projects", "ModuleFiles", i);
        		vTestDataFiles=xr.getCellData("Projects", "TestDataFiles", i);
        		vDBName=xr.getCellData("Projects", "DBName", i);
        		vDBuser=xr.getCellData("Projects", "DBUser", i);
        		vDBpassword=xr.getCellData("Projects", "DBPassword", i);
        		vBrowserType=xr.getCellData("Projects", "BrowerType", i);
        		System.out.println(vProjectName+"||"+vProjectUrl+"||"+vBrowserType+"||"+vModuleFiles+"||"+vTestDataFiles);
        		
        		Xls_Reader xm=new Xls_Reader(getPath("/DriverFiles/ModuleDriver/"+vModuleFiles));
        		Xls_Reader xtd=new Xls_Reader(getPath("/TestDataFiles/"+vTestDataFiles));
        		ModuleCnt=xr.getRowCount(vProjectName);
        		
        		
        		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        		Date date = new Date();		
        		String ProjectStartTime=dateFormat.format(date);
        		//Html result code
        		Xls_Reader xres=new Xls_Reader(getPath("/Results/ExcelResult.xlsx"));
        		Cntflag=0;
        		actfails=0;
        		html_result hr=new html_result();
        		ResFilePath=hr.CreateResultFileAndPath(vProjectName,reportPath);
        		hr.fg_OpenResultsFile(ResFilePath, vProjectName);
        		
        		for(int j=2;j<=ModuleCnt;j++)
        		{
        			vModulesRun=xr.getCellData(vProjectName, "Run", j);	
        			if(vModulesRun.equalsIgnoreCase("ON"))
        			{
        				vModuleName=xr.getCellData(vProjectName, "ModuleName", j);
        				System.out.println(vModuleName);
        				vTCCnt=xm.getRowCount(vModuleName);
        				for(int k=2;k<=vTCCnt;k++)
        				{
        					vTCRun=xm.getCellData(vModuleName, "Run", k).trim();        					
        					if(vTCRun.equalsIgnoreCase("ON"))
        					{
        						vTCName=xm.getCellData(vModuleName, "TCName", k).trim();
        						System.out.println(vTCName);
        						Date t1 = new Date();		
        		        		String TCStartTime=dateFormat.format(t1);
        						Cntflag=Cntflag+1;
        						failcnt=0;
        						flag=0;
        						vRowNum=0;
        						vTDCnt=xtd.getRowCount(vModuleName);
        						for(int m=2;m<=vTDCnt;m++)
        						{
        							vTDSteps=xtd.getCellData(vModuleName, "TC_Steps_Description", m);
        							if(vTCName.equals(vTDSteps))
        							{
        								flag=1;
        								vRowNum=m;
        								vTCDesc=xtd.getCellData(vModuleName, "TC_Steps_Description", m-1).replaceAll("//", "");
        								System.out.println(vTCDesc);
        								hr.fgInsertStep(ResFilePath);
        							}
        							if((flag==1) && (m>vRowNum))
        							{
        								if(vTDSteps.contains("//"))
        								{
        									break;
        								}
        								else
        								{
        									vKeyword=vTDSteps.trim();
        									System.out.println(vKeyword);
        									Generic_Functions GF=new Generic_Functions();
        									GF.keywordDriver(vKeyword,xtd,vModuleName,m,hr);
        								}
        							}
        						}
        						
        						if(failcnt>0)
        						{
        							actfails=actfails+1;
        							hr.ExcelResult(xres, "FAIL");
        						}
        						else
        						{
        							hr.ExcelResult(xres, "PASS");	
        						}
        						Date t2 = new Date();		
        		        		String TCStopTime=dateFormat.format(t2);
        		        		long TCTimediff=hr.timeDiffernce(TCStartTime, TCStopTime, dateFormat);
        		        		hr.WriteTCTime(ResFilePath, TCTimediff);
        					}					
        					
        				}
        			}
        			
        		}
        		
        		Date date1 = new Date();		
        		String ProjectStopTime=dateFormat.format(date1);
        		long ProjectexectionTime=hr.timeDiffernce(ProjectStartTime, ProjectStopTime, dateFormat);
        		hr.fgCreateSummary(ResFilePath);
        		//hr.fgInsertSummary(ResFilePath, Cntflag, Cntflag-actfails,actfails, passval, failval, "2  min");
        		hr.fgInsertSummary(ResFilePath, Cntflag, passval, failval, Cntflag-actfails,actfails, ProjectexectionTime);
        	}
        }
        
        return ResFilePath;
	}

}
