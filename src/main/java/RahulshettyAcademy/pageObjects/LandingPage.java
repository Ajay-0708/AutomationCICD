package RahulshettyAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulshettyAcademy.AbstractComponents.AbstractComponent;



public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	//page factory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='toast-message']")
	WebElement errorMessage;
	
	public void goTO() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCataloge loginApplication(String email, String password) 
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		
		ProductCataloge productCatalogue=new ProductCataloge(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() 
	{
		
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	
	
}
