package RahulshettyAcademy.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulshettyAcademy.TestComponent.BaseTest;
import RahulshettyAcademy.TestComponent.Retry;
import RahulshettyAcademy.pageObjects.CartPage;
import RahulshettyAcademy.pageObjects.ProductCataloge;

public class ErrorValidationTest extends BaseTest{

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException 
	{
		 String productName="adidas original";
	     ProductCataloge productCatalogue =landingPage.loginApplication("upadhyayajay3@gmail.com",  "Sephora@12345");
	     
	    //  Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	      
	      Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	        
	      }
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException 
	{
		 String productName="adidas original";
	     ProductCataloge productCatalogue =landingPage.loginApplication("upadhyayajay@gmail.com",  "Sephora@1234567");
		 List<WebElement> products=productCatalogue.getProductList();
		 productCatalogue.addProductToCart(productName);
	     CartPage cartPage=productCatalogue.goToCartPage();
		 Boolean match=cartPage.VerifyProductDisplay("adidas original33");
		 Assert.assertFalse(match);
		 
	        
	      
	
	   
	}
}

	

