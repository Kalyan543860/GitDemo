package rahulShettyAcademy.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import rahulShettyAcademy.pageObjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		LandingPage landingPage = new LandingPage(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("kalyankumar135999@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@(123)");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']")));

		List<WebElement> Products = driver.findElements(By.xpath("//div[@class='card-body']"));

		WebElement prod = Products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		WebElement element = driver.findElement(By.cssSelector("[routerlink*='cart']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

		List<WebElement> cartProductsName = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProductsName.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		WebElement element4 = driver.findElement(By.xpath("//*[text()='Checkout']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element4);

		driver.findElement(By.cssSelector("input[placeholder*='Select Country']")).sendKeys("Indi");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-re']")));

		WebElement element5 = driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element5);

		WebElement element6 = driver.findElement(By.cssSelector("a[class*='btnn action']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element6);

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
}
