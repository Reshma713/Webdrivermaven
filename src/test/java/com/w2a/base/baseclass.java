package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.utilities.ExcelReader;
import com.utilities.ExtentManager;
import com.utilities.TestUtil;




//import junit.framework.Assert;

public class baseclass {

	public static ChromeDriver driver;
	public static Properties config=new Properties();
	public static Properties OR=new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel= new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\test.xlsx");
    public static WebDriverWait wait;
    public ExtentReports rep=ExtentManager.getInstance();
    public static ExtentTest test;
    public static String browser;
	
	@BeforeSuite
	public void setUp() throws IOException {

		if(driver==null)
		{

			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis=new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(config.getProperty("browser").equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\executables\\geckodriver.exe");
				FirefoxDriver driver=new FirefoxDriver();
			log.debug("firefox launched!!");
			}
			else if(config.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				 driver=new ChromeDriver();
				log.debug("Chrome launched!!");
				
			}
			else if(config.getProperty("browser").equals("ie"))
			{
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				InternetExplorerDriver	driver=new InternetExplorerDriver();
				log.debug("ie launched!!");
				
			}
			driver.get(config.getProperty("test"));
			log.debug("Navigated to "+(config.getProperty("test")));
        	driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);//checking presence of element
           
          wait=new WebDriverWait(driver,5);//for handling alerts
			
			
		}
	
	}
	
	public void click(String locator)
	{
		if(locator.endsWith("_CSS"))
		{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		
	    }else if(locator.endsWith("_XPATH"))
	    {
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		
	    }else if(locator.endsWith("_ID"))
        {
	    driver.findElement(By.id(OR.getProperty(locator))).click();
	
        }
       test.log(LogStatus.INFO,"Clicking on: "+locator);
	}
	
	   public void type(String locator,String value)
	     {
		   if(locator.endsWith("_CSS"))
			{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);}
			
		
	     
	   else if(locator.endsWith("_XPATH"))
	    {
	    	driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);}
	    
	    
	    else if(locator.endsWith("_ID"))
	    {
	    	
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);}
	    
	   test.log(LogStatus.INFO,"typing in "+locator+"entered value as"+value);
     }
       
	   //dropdown selection
	   
	   static WebElement dropdown;
	  public void select(String locator,String value)
	  {
		  if(locator.endsWith("_CSS"))
			{
		dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}
			
		
	     
	   else if(locator.endsWith("_XPATH"))
	    {
		   dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
	    	}
	    
	    
	    else if(locator.endsWith("_ID"))
	    {
	    	
	    	dropdown=driver.findElement(By.id(OR.getProperty(locator)));
			}
		  Select select= new Select(dropdown);
		  select.selectByVisibleText(value);
	    
		  test.log(LogStatus.INFO,"Selecting from dropdown: "+locator+"value as"+value);
	  }
	   
	   
	   
	   
	   
	     
	public boolean isElementPresent(By by)
	{
		try
		{
		driver.findElement(by);
		return true;
		}
		
		catch(NoSuchElementException e)
		{
		return false;
		}
	}
	
	public static void verifyEquals(String actual,String expected) throws IOException
	{
		try {
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable t)
		{
			TestUtil.captureScreenshot();
			//ReportNG
			Reporter.log("<br>" +"Verfication failure:"+t.getMessage()+"<br>");
			Reporter.log("<a target =\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200 ></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			//extent reports
			test.log(LogStatus.FAIL, "Verification failed with exception: "+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}
	}

	@AfterSuite
	public void tearDown() 
	{
	if(driver!=null)
	{
       driver.quit();
       log.debug("Exceution completed");
	}
}}
