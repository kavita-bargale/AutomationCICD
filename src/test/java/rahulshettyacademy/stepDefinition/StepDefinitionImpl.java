package rahulshettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatelogue productCatelogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		productCatelogue = landingPage.loginApplication(username,password);		
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addProductToCart(productName);
	}
    
	@When("^Checkout (.+) and submit the order$")
    public void checkout_product_and_submit_order(String productName)
    {
		cartPage= productCatelogue.goToCartPage();			
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();	
    }
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed__on_confirmation_page(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		tearDown();
	}
	
	@Then( "{string} message is displayed")
	public void error_message_is_displayed(String string)
	{
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		tearDown();
	}
	
}
