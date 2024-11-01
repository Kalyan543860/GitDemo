package rahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulShettyAcademy.pageObjects.CartPage;
import rahulShettyAcademy.pageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	PageFactory.initElements(driver, this);
	}

	        @FindBy(css="[routerlink*='cart']")
	        WebElement cartHeader;
	        
	        @FindBy(css="[routerlink='/dashboard/myorders']")
	        WebElement orderHeader;
	
	
	public void waitElementToBeAppear(By findBy) {
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToBeAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public void waitElementToBeDisappear() throws InterruptedException {
		Thread.sleep(2000);
		
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cart = new CartPage(driver);
		return cart;
	}
    
	public  OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
