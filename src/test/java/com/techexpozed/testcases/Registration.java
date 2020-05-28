package com.techexpozed.testcases;



import org.openqa.selenium.JavascriptExecutor;

import org.testng.annotations.Test;

import com.w2a.base.baseclass;

public class Registration extends baseclass {
	@Test
	public void registrationpage() throws InterruptedException
	{
		((JavascriptExecutor) driver).executeScript("scroll(100,500)");
	//driver.findElement(By.cssSelector(OR.getProperty("signup"))).click();
try {
	click("signup_CSS");}
catch(NullPointerException e)
{}
}
  
    // driver.findElement(By.cssSelector(OR.getProperty("name_ID"))).sendKeys("reshmajoseph713@gmail.com");

	
		//driver.findElement(By.xpath("logo")).isDisplayed();
	   
   // WebElement d= driver.findElement(By.cssSelector(OR.getProperty("logo")));
    //d.isDisplayed();
  //driver.findElement(By.cssSelector(OR.getProperty("loginbutton"))).click();
  
    //Set <String> allWindows = driver.getWindowHandles();
   // for(String handle : allWindows)
   // {
   // driver.switchTo().window(handle);
   // } 
   // String expectedURL = "https://techexpozed.co.nz/login/";
    //String actualURL = driver.getCurrentUrl();
    //System.out.println(actualURL);
   // Assert.assertEquals(actualURL, expectedURL);

    
    

}
