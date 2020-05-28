package com.rough;

import java.util.Date;

public class timestamp {
	public static void main (String[] args)
	{
		
	Date d=new Date();
	String screenshoName=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
	System.out.println(screenshoName);
		System.out.println(d);
		
		
	}

}
