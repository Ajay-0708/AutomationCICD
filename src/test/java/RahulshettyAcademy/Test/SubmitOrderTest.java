package RahulshettyAcademy.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulshettyAcademy.TestComponent.BaseTest;
import RahulshettyAcademy.pageObjects.CartPage;
import RahulshettyAcademy.pageObjects.CheckoutPage;
import RahulshettyAcademy.pageObjects.ConfirmationPage;
import RahulshettyAcademy.pageObjects.LandingPage;
import RahulshettyAcademy.pageObjects.OrderPage;
import RahulshettyAcademy.pageObjects.ProductCataloge;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{

	String productName="adidas original";
	
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException 
	{
		
	     ProductCataloge productCatalogue =landingPage.loginApplication(input.get("email"), input.get("password"));
		 List<WebElement> products=productCatalogue.getProductList();
		 productCatalogue.addProductToCart(input.get("product"));
	     CartPage cartPage=productCatalogue.goToCartPage();
		 Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		 Assert.assertTrue(match);
		 CheckoutPage checkoutPage=cartPage.goToCheckout();
	      checkoutPage.selectCountry("India");
	      ConfirmationPage confirmationPage=checkoutPage.submitOrder();
	      String confirmMessage=confirmationPage.getConfirmaionMessage();
           Assert.assertEquals(confirmMessage,"THANKYOU FOR THE ORDER.");
	        
	      
	
	   
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	
	//To Verify addidas oroginal is displaying in orders page
	
	public void OrderHistoryTest() 
	{
		ProductCataloge productCatalogue =landingPage.loginApplication("upadhyayajay32@gmail.com",  "Sephora@123456");
		OrderPage ordersPage=productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName)); 
	}
	
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		
		
		List<HashMap<String, String>> data=getJsonDataToMap("E:\\SeleniumTraining\\SeleniumFrameWorkDesign\\src\\test\\java\\rahulshettyaAcademy\\data\\PurchaseOrder.json") ;
		return new Object[][]  {{data.get(0)}, {data.get(1)},{data.get(2)}};
	}
	
}

//return new Object[][] {{"upadhyayajay32@gmail.com","Sephora@123456","adidas original"},{"testuse12@gmail.com","Pass@123","ZARA COAT 3"}};

	/*	HashMap<String,String> map=new HashMap <String,String> ();
		map.put("email", "upadhyayajay32@gmail.com");
		map.put("password", "Sephora@123456");
		map.put("product", "adidas original");
		
		HashMap<String,String> map1=new HashMap <String,String> ();
		map1.put("email", "testuse12@gmail.com");
		map1.put("password", "Pass@123");
		map1.put("product", "ZARA COAT 3");
		
		*/

	

