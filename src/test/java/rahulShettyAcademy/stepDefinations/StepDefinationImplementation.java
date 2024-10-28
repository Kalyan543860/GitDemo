package rahulShettyAcademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulShettyAcademy.TestComponents.BaseTest;
import rahulShettyAcademy.pageObjects.CartPage;
import rahulShettyAcademy.pageObjects.CheckOutDetailsPage;
import rahulShettyAcademy.pageObjects.ConfirmationPage;
import rahulShettyAcademy.pageObjects.LandingPage;
import rahulShettyAcademy.pageObjects.ProductCatalogue;

public class StepDefinationImplementation extends BaseTest {
	
	public LandingPage landingPage;
	public 	ProductCatalogue catalogue;
	ConfirmationPage cp;
	
	@Given("I Landed on E-commerce Page")
	public void I_Landed_on_Ecommerce_page() throws IOException {
		  landingPage =  launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_password(String username , String password) {
		 catalogue = landingPage.loginIntoApplication( username,  password);	
	}
	
	@When("^I add product (.+) to cart$")
	public void add_product_to_the_cart(String productName) throws InterruptedException {
		List<WebElement> Products = catalogue.getTheProductList();
		catalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cart = catalogue.goToCartPage();
		Boolean match = cart.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutDetailsPage cod = cart.goTocheckOutPage();
		cod.selectCountry("Indi");
		 cp = cod.submitOrder();
	}
	
	@Then("{string} message is displayed on conformationPage")
	public void message_displayed_conformation_page(String string) {
		String orderConformation = cp.getOrderConformationMessage();
		Assert.assertTrue(orderConformation.equalsIgnoreCase(string));
	}
	
	@Then("{string} message is displayed")
	public void error_message_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());	
	}
	
	}

