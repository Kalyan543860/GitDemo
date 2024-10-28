package rahulShettyAcademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulShettyAcademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	  WebDriver driver;
	  
	  public LandingPage (WebDriver driver) {
		  super(driver);
		  this.driver = driver;
		  PageFactory.initElements(driver, this);
	  }

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	  
	  @FindBy(id="userEmail")
	  WebElement userEmail;
	  
	  @FindBy(id="userPassword")
	  WebElement password;
	  
	  @FindBy(id="login")
	  WebElement submit;
	  
	  @FindBy(css="[class*='flyInOut']")
	  WebElement errorMessage;
	  
	  
			  
	  
	  public ProductCatalogue loginIntoApplication(String email, String passkey ) {
		  userEmail.sendKeys(email);
		  password.sendKeys(passkey);
		  submit.click();
		  ProductCatalogue catalogue = new ProductCatalogue(driver);
		  return catalogue;
	  }
	  
	  public String getErrorMessage() {
		  waitForWebElementToBeAppear(errorMessage);
		 return errorMessage.getText();
		 
	  }
	  
	  public void goToUrl() {
		  driver.get("https://rahulshettyacademy.com/client");
	  }
}
