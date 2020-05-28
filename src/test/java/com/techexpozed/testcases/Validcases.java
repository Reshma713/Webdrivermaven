package com.techexpozed.testcases;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.utilities.TestUtil;
import com.w2a.base.baseclass;


public class Validcases extends baseclass 
{
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")

	public void validcases(String Email,String password,String confirmpassword,String fname,String lname,String mobile,String country,String alert) throws InterruptedException 
	{   
		//String expected_title="@yahoo.com";
	    //Thread.sleep(3000);
		
		//type("email1_CSS",email1);
		//type("password_CSS", password);
		//type("confirmpassword_CSS",confirmpassword);
		//type("name1_CSS",name1);
		//type("name2_CSS",name2);
		//type("mobile_CSS",mobile);
		//select("country_CSS",country);
		Thread.sleep(3000);
	    try {
	    	
	    	driver.findElement(By.cssSelector("#email")).sendKeys(Email);
	    	Thread.sleep(29000);
	    	driver.findElement(By.cssSelector("#password")).sendKeys(password);
	    	
			driver.findElement(By.cssSelector("#c_password")).sendKeys(confirmpassword);
			Thread.sleep(29000);
			driver.findElement(By.cssSelector("#first_name")).sendKeys(fname);
	
			driver.findElement(By.cssSelector("#last_name")).sendKeys(lname);
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#mobile")).sendKeys(mobile);
			Thread.sleep(2000);
			select("country_CSS",country);
			driver.findElement(By.cssSelector("#register")).click();
			
	
		
			
	    } 
		
		catch (InvalidSelectorException e) {
	    }
	    
	    try {
		    WebDriverWait wait = new WebDriverWait(driver, 10);
		    wait.until(ExpectedConditions.alertIsPresent());
		    Alert alert1=driver.switchTo().alert();
		   // Assert.assertTrue(alert.getText().contains(alerttext)); 
		    Thread.sleep(3000);
		    alert1.accept();
		    Thread.sleep(3000);
		} 
		catch (Exception e) {
		    
		}
		//driver.findElement(By.cssSelector(OR.getProperty("password"))).sendKeys(password);
		//driver.findElement(By.cssSelector(OR.getProperty("c_password"))).sendKeys(confirmpassword);
		//driver.findElement(By.cssSelector(OR.getProperty("first_name"))).sendKeys(fname);
		//driver.findElement(By.cssSelector(OR.getProperty("last_name"))).sendKeys(lname);
		//driver.findElement(By.cssSelector(OR.getProperty("mobile"))).sendKeys(mobile);
		//driver.findElement(By.cssSelector(OR.getProperty("country"))).sendKeys(country);
		
		/*try {
		    WebDriverWait wait = new WebDriverWait(driver, 10);
		    wait.until(ExpectedConditions.alertIsPresent());
		    Alert alert=driver.switchTo().alert();
		   // Assert.assertTrue(alert.getText().contains(alerttext)); 
		    Thread.sleep(3000);
		    alert.accept();
		    Thread.sleep(3000);
		} 
		catch (Exception e) {
		    
		}*/
		
	}
}
