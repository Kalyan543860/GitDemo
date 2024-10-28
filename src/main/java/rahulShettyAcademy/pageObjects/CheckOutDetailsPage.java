package rahulShettyAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CheckOutDetailsPage extends AbstractComponent {
          WebDriver  driver;
          
	public CheckOutDetailsPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	By shippingInformationText = By.xpath("//*[text()=' Shipping Information ']");
	
	@FindBy (css="input[placeholder*='Select Country']")
	WebElement sendingCountryName;
	
	@FindBy (xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountryName2;
	
	@FindBy (css="a[class*='btnn action']")
	WebElement submitButton;
	
	By waitForSearchResults = (By.cssSelector("[class*='ta-re']"));
	
	
	public void selectCountry (String countryName) {
		
		Actions actions = new Actions(driver);
		actions.sendKeys(sendingCountryName, countryName).build().perform();
		waitElementToBeAppear(shippingInformationText);
		waitElementToBeAppear(waitForSearchResults);
		selectCountryName2.click();
	}
	
	public ConfirmationPage submitOrder() {
		submitButton.click();
		ConfirmationPage cp = new ConfirmationPage(driver);
		return cp;
	}


	
}
