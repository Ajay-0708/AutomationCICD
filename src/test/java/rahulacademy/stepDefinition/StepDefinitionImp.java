package rahulacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RahulshettyAcademy.TestComponent.BaseTest;
import RahulshettyAcademy.pageObjects.CartPage;
import RahulshettyAcademy.pageObjects.CheckoutPage;
import RahulshettyAcademy.pageObjects.ConfirmationPage;
import RahulshettyAcademy.pageObjects.LandingPage;
import RahulshettyAcademy.pageObjects.ProductCataloge;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest
{

	public LandingPage landingPage; 
	public ProductCataloge productCatalogue;
	public ConfirmationPage confirmationPage;
	
	
	@Given("I landed on Ecommerce Page")
	public void I_Landed_On_Ecommerce_Page() throws IOException 
	{
		//code
		landingPage=launchApplication();

	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_password(String username,String password) 
	{
		productCatalogue =landingPage.loginApplication(username,password);

	}
	
	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException 
	{
		List<WebElement> products=productCatalogue.getProductList();
		 productCatalogue.addProductToCart(productName);

	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String product) throws InterruptedException 
	{
		CartPage cartPage=productCatalogue.goToCartPage();
		 Boolean match=cartPage.VerifyProductDisplay(product);
		 Assert.assertTrue(match);
		 CheckoutPage checkoutPage=cartPage.goToCheckout();
	      checkoutPage.selectCountry("India");
	      confirmationPage=checkoutPage.submitOrder();
	}
	
	@Then("I verify the {string} message is displayed on confirmationPage")
	public void message_displayed_confirmation_page(String string) 
	{
		 String confirmMessage=confirmationPage.getConfirmaionMessage();
         Assert.assertEquals(confirmMessage,string);
         driver.close();

	}
	
	
	@Then("{string} message is displayed")
	public void Error_message_is_displayed(String string) 
	{
		Assert.assertEquals(string,landingPage.getErrorMessage());
		driver.close();

	}
	
}
