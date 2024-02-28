package RahulshettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import RahulshettyAcademy.AbstractComponents.AbstractComponent;



public class ProductCataloge extends AbstractComponent
{
	WebDriver driver;
	
	public ProductCataloge(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".col-sm-10"));
	
	//page factory
	
	@FindBy(css=".col-sm-10")
	List<WebElement> products;
	
	//@FindBy(css=".ng-animating")
	//WebElement spinner;
	
	By productsBY=By.cssSelector(".col-sm-10");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	//By toastMessage=By.cssSelector("toast-container");

	public List<WebElement> getProductList() throws InterruptedException
	{
	
			waitForElementToAppear(productsBY);
		
		   return products;
	}
	
	public WebElement getProductByName(String productName) throws InterruptedException 
	{
		WebElement prod=  getProductList().stream().filter(p-> 
	     p.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException 
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		Thread.sleep(2000);
		boolean t = driver.findElement(By.id("toast-container")).isDisplayed();  
		if (t)
		{      
		System.out.println("Element is dispalyed");  
		} else 
		{        System.out.println("Element is not dispalyed");   
		}
		//waitForElementToAppear(toastMessage);
		
		//waitForElementToDisAppear(spinner);
		
		boolean t1 = driver.findElement(By.cssSelector(".ng-animating")).isDisplayed();  
		if (t1)
		{      
		System.out.println("Element is displayed and disappear");  
		} else 
		{        System.out.println("Element is not dispalyed");   
		}
	}
	
	
	
}
