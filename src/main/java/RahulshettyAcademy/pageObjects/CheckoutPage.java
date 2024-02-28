package RahulshettyAcademy.pageObjects;

import java.awt.Point;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import RahulshettyAcademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent 
{
     WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By results=By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName) 
	{
		Actions a=new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		
		
	}
	
	
	public ConfirmationPage submitOrder() throws InterruptedException 
	{
		

		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		  
		 Thread.sleep(5000);
		
	     submit.click();
		    
	     
		return new ConfirmationPage(driver);
	}
	
	
	
	
	

}
