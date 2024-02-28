package RahulshettyAcademy.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RahulshettyAcademy.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException 
	{
		
		String ProductName="adidas original";
		
		 WebDriverManager.chromedriver().setup();
		 WebDriver driver=new ChromeDriver();
		 driver.get("https://rahulshettyacademy.com/client");
		 LandingPage landingPage=new LandingPage(driver);
		 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		 driver.findElement(By.id("userEmail")).sendKeys("upadhyayajay32@gmail.com");
		 driver.findElement(By.id("userPassword")).sendKeys("Sephora@123456");
		 driver.findElement(By.id("login")).click();
		 driver.manage().window().maximize();
		 
	    List<WebElement> products = driver.findElements(By.cssSelector(".col-sm-10"));
	     System.out.println(products.size());
	     
	   WebElement prod=  products.stream().filter(p-> 
	     p.findElement(By.tagName("b")).getText().equalsIgnoreCase(ProductName)).findFirst().orElse(null);
	   
	   prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	   
	   WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	   
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	   
	   //ng animating
	   
	   wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	   
	   
	   driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	   
	  List<WebElement> cartProducts =driver.findElements(By.cssSelector(".cartSection h3"));
	  
	Boolean match=  cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
	
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions a=new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	   
	driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	
	JavascriptExecutor js = (JavascriptExecutor)driver;  
	js.executeScript("window.scrollBy(0,500)");
	Thread.sleep(5000);
	
	
	
	driver.findElement(By.cssSelector(".action__submit")).click();
	js.executeScript("scroll(250, 0)");
	Thread.sleep(5000);
	
	String str=driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	Assert.assertEquals(str,"THANKYOU FOR THE ORDER.");
	
	driver.close();
	
	   

}
}
