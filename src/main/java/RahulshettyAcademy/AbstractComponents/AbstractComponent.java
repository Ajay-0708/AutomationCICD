package RahulshettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulshettyAcademy.pageObjects.CartPage;
import RahulshettyAcademy.pageObjects.OrderPage;

public class AbstractComponent 
{
	 WebDriver driver;

public AbstractComponent(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver,this);
}

@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
WebElement cartHeader;

@FindBy(css="[routerlink*='myorders']")
WebElement OrderHeader;


public void waitForElementToAppear(By findBy)
{
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated((findBy)));
	
    
}

public void waitForWebElementToAppear(WebElement findBy)
{
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf((findBy)));
	
    
}

public void waitForElementToDisAppear(WebElement ele) 
{ 
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(ele));
	
	
}

public CartPage goToCartPage() 
{
	
	cartHeader.click();	
	CartPage cartPage=new CartPage(driver);
	return cartPage;
}

public OrderPage goToOrdersPage() 
{
	
	OrderHeader.click();	
	OrderPage orderPage=new OrderPage(driver);
	return orderPage;
}

}
