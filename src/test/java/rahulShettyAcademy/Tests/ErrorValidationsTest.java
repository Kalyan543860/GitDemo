package rahulShettyAcademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.pageObjects.CartPage;
import rahulShettyAcademy.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups = {"ErrorHandling"})
	public void logInToTheApplicationErrorValidationMethod() throws IOException, InterruptedException {

		landingPage.loginIntoApplication("kalyankumar135999@gmail.com", "Password@(123)");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidationMethod() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue catalogue = landingPage.loginIntoApplication("kalyanrgmmech@gmail.com", "Password@(123)");
		List<WebElement> Products = catalogue.getTheProductList();
		catalogue.addProductToCart(productName);
		CartPage cart = catalogue.goToCartPage();
		Boolean match = cart.verifyProductDisplay("ZARA COAT 35");
		Assert.assertFalse(match);

	}

}
