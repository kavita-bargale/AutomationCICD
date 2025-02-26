package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException 
	{
		landingPage.loginApplication("kavita.bargale@gmail.com", "Spring21");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException
	{
		String productNm = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication(); we used BeforeMthod to call this 
		ProductCatelogue productCatelogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addProductToCart(productNm);
		CartPage cartPage= productCatelogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
