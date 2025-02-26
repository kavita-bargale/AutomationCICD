package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;

public class SubmitOrderTest extends BaseTest{

	String productNm = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups="Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException 
	{
		
		//LandingPage landingPage = launchApplication(); we used BeforeMthod to call this 
		ProductCatelogue productCatelogue = landingPage.loginApplication(input.get("email"), input.get("password"));		
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addProductToCart(input.get("product"));
		CartPage cartPage= productCatelogue.goToCartPage();	
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest()
	{ 
		ProductCatelogue productCatelogue = landingPage.loginApplication("kavita.bargale@gmail.com", "Spr!ng21");
		OrderPage orderPage = productCatelogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productNm)); 	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//
		//List<HashMap<String, String>> data = getJsonDataToMap();
		
		List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}}		;

		
		//return new Object[][] {{"kavita.bargale@gmail.com", "Spr!ng21","ZARA COAT 3"},{"rahulshetty@gmail.com", "Iamking@000","ADIDAS ORIGINAL"}};
		
		/* using HASHMAP
		 * // HashMap<String,String> map = new HashMap<String,String>(); //
		 * map.put("email", "kavita.bargale@gmail.com"); // map.put("password",
		 * "Spr!ng21"); // map.put("product", "ZARA COAT 3"); // //
		 * HashMap<String,String> map1 = new HashMap<String,String>(); //
		 * map1.put("email", "rahulshetty@gmail.com"); // map1.put("password",
		 * "Iamking@000"); // map1.put("product", "ADIDAS ORIGINAL");
		 */	
	}
}
