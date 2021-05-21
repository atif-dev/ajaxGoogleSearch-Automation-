package com.ajaxgooglesearch.testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AjaxGoogleSearch {

	@Test
	public static void main() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.google.com");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// Enter "techlistic" in google search box
	    driver.findElement(By.name("q")).sendKeys("techlistic");
	    //driver.findElement(By.name("q")).sendKeys("techlist");//This gives bigger list in Google search
	    
	    // Wait for suggestions box to be appear for 20 seconds
	    WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("UUbT9")));
	    
	    
	    /*If we don't stop here then test may fail sometimes for keyword techlistic.
	    This is because AJAX append HTML tags in run time quickly and our code execution sometimes slow down
	    so we will not able to find element and will get following error
	    "org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document"*/
	    Thread.sleep(3000);
	   
	    
	    WebElement list = driver.findElement(By.className("erkvQe"));//get access to suggestion box <ul>(unordered list)
	    List<WebElement> rows = list.findElements(By.tagName("li"));//Using list get further access to <li>
	    
	    // Iterate over suggestions box and print suggestions one by one
	    for (WebElement elem : rows) {
	        System.out.println(elem.getText());
	    }
	    
	}
	
}
