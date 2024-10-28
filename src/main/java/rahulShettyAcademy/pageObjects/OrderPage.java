package rahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> orderedProducts;
	
        public OrderPage(WebDriver driver) {
		super(driver);
		driver = this.driver;
		PageFactory.initElements(driver, this);
	}

		public Boolean verifyOrderDisplay(String productName) {
			Boolean match = orderedProducts.stream().anyMatch(orderedProduct -> orderedProduct.getText().equalsIgnoreCase(productName));
			return match;
		}
        
}
