package rahulShettyAcademy.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.pageObjects.CartPage;
import rahulShettyAcademy.pageObjects.CheckOutDetailsPage;
import rahulShettyAcademy.pageObjects.ConfirmationPage;
import rahulShettyAcademy.pageObjects.LandingPage;
import rahulShettyAcademy.pageObjects.OrderPage;
import rahulShettyAcademy.pageObjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups={"purchase"})
	public void SubmitOrder(HashMap<String,String> map) throws IOException, InterruptedException {
		
		ProductCatalogue catalogue = landingPage.loginIntoApplication(map.get("email"), map.get("password"));
		List<WebElement> Products = catalogue.getTheProductList();
		catalogue.addProductToCart(map.get("productName"));
		CartPage cart = catalogue.goToCartPage();
		Boolean match = cart.verifyProductDisplay(map.get("productName"));
		Assert.assertTrue(match);
		CheckOutDetailsPage cod = cart.goTocheckOutPage();
		cod.selectCountry("Indi");
		ConfirmationPage cp = cod.submitOrder();
		String orderConformation = cp.getOrderConformationMessage();
		Assert.assertTrue(orderConformation.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dependsOnMethods={"SubmitOrder"})
	public void verifyOrderIsDisplayedOrNot() {
		ProductCatalogue catalogue = landingPage.loginIntoApplication("kalyankumar135999@gmail.com", "Password@(123)");
		OrderPage orderpage = catalogue.goToOrderPage();  
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName)); 
	}
	
	public String getScreenShot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir"+"//reports//"+testCaseName+".png"));
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
    @DataProvider
	public Object[][] getData() throws IOException {
		
    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulShettyAcademy\\data\\PurchaseOrder.json");
		Object[][] info =  new Object[][] {{data.get(0)},{data.get(1)}};
		return info;
	}
    
    
 
    

    
}   
    
    
    
    
    
    /*
	 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
	 * "kalyankumar135999@gmail.com"); map.put("password", "Password@(123)");
	 * map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
	 * "kalyanrgmmech@gmail.com"); map1.put("password", "Password@(123)");
	 * map1.put("productName", "ADIDAS ORIGINAL");
	 */

