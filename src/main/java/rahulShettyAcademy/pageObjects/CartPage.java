package rahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProductsName;
	
	@FindBy(xpath="//*[text()='Checkout']")
	WebElement checkOutButtonClick;
	
     public CartPage(WebDriver driver) { 
    	super(driver);  
		this.driver = driver;
		PageFactory.initElements(driver, this);		
		
		
		
	}

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProductsName.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutDetailsPage goTocheckOutPage () {
		checkOutButtonClick.click();
		CheckOutDetailsPage cod = new CheckOutDetailsPage(driver);
		return cod;
	}

	
}
