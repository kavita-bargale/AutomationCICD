package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		//initialization driver
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css= "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public Boolean verifyOrderDisplay(String productNm)
	{
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productNm));
		return match;
	}

}
