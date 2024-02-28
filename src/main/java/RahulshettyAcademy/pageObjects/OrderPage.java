package RahulshettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulshettyAcademy.AbstractComponents.AbstractComponent;



public class OrderPage extends AbstractComponent
{
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	 List<WebElement> productNames;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	//page factory
	
	public Boolean VerifyOrderDisplay(String ProductName) 
	{
		Boolean match=  productNames.stream().anyMatch(product-> product.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	
	
	
	
	


	

	
	
}
