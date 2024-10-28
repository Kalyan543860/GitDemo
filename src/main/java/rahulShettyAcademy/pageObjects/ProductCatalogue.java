package rahulShettyAcademy.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//div[@class='card-body']")
	List<WebElement> Products;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//*[text()='Checkout']")
	WebElement checkOutProducts;
	

	By productBy = By.xpath("//div[@class='card-body']");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastContainer = By.cssSelector("#toast-container");
    
	public List<WebElement> getTheProductList() {

		waitElementToBeAppear(productBy);
		return Products;

	}

	public WebElement getProductName(String productName) {
		
		WebElement prod = getTheProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
    public void addProductToCart(String productName) throws InterruptedException {
    	
    	WebElement  prod = getProductName(productName); 
    	prod.findElement(addToCart).click();
    	waitElementToBeAppear(toastContainer);
    	
    }
    
    
   }

