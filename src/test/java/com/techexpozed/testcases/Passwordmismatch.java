package com.techexpozed.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utilities.TestUtil;
import com.w2a.base.baseclass;

public class Passwordmismatch extends baseclass{
	
	

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")

	public void passwordmismatch(String Email,String password,String confirmpassword,String fname,String lname,String mobile,String country) throws InterruptedException 
	{   
	
		driver.findElement(By.cssSelector("#email")).clear();
	    driver.findElement(By.cssSelector("#password")).clear();
	    driver.findElement(By.cssSelector("#c_password")).clear();
	    driver.findElement(By.cssSelector("#first_name")).clear();
	    driver.findElement(By.cssSelector("#last_name")).clear();
	   driver.findElement(By.cssSelector("#mobile")).clear();
	    try {
	    	
	    	driver.findElement(By.cssSelector("#email")).sendKeys(Email);
	    	
	    	driver.findElement(By.cssSelector("#password")).sendKeys(password);
	    	
	    	driver.findElement(By.cssSelector("#c_password")).sendKeys(confirmpassword);
		
			driver.findElement(By.cssSelector("#first_name")).sendKeys(fname);
		
			driver.findElement(By.cssSelector("#last_name")).sendKeys(lname);
			
			//driver.findElement(By.cssSelector("#mobile")).sendKeys(mobile);
	    	//Thread.sleep(14000);
	    	driver.findElement(By.cssSelector("#mobile")).sendKeys(mobile);
			select("country_CSS",country);
	    	//select("country_CSS",country);
			//Thread.sleep(8000);	
			
			
			
	    } 
	    catch (InvalidSelectorException e) {
	    }
	 
	    driver.findElement(By.cssSelector("#register")).click();
	    String expectedErrorMsg = "There is password mismatch";
	    WebElement exp = driver.findElement(By.cssSelector("#swal2-content"));
	   String actualErrorMsg = exp.getText();
	    Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	    Thread.sleep(29000);
	   
	  
	}

}




