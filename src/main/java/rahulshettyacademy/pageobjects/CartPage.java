package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		//initialization driver
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	//Page Factory
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css= ".cartSection h3")
	private List<WebElement> productTitles;
	
	public Boolean verifyProductDisplay(String productNm)
	{
		Boolean match = productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productNm));
		return match;
	}

	public CheckoutPage goToCheckOut()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	

}
