package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import bases.ValidateHelper;

public class CartManagementPage {
	private WebDriver driver;
	private ValidateHelper validateHelper;

	private By increaseQntBtn = By.xpath("//a[@class='quantity-change amount-up plus']");
	private By decreaseQntBtn = By.xpath("//a[@class='quantity-change amount-down minus']");
	private By deleteProductBtn = By.xpath("//a[@class='delete-from-cart']");
	private By quantityText = By.xpath("//input[@class='buy-quantity quantity-change']");
	private By message = By.xpath("//p[@class='font-weight-bold text-24']");
		
	// Khởi tạo class khi được gọi với driver
	public CartManagementPage(WebDriver driver) {
		this.driver = driver;
		validateHelper = new ValidateHelper(driver);
	}

	public void clickIncreaseBtn() {
		WebElement increase = driver.findElement(increaseQntBtn);
		validateHelper.clickElement2(increase);
	}
	
	public void clickDecreaseBtn() {
		WebElement decrease = driver.findElement(decreaseQntBtn);
		validateHelper.clickElement2(decrease);
	}
	
	public void verifyQuantityText(String expectedText) {
		WebElement quantity = driver.findElement(quantityText);
		validateHelper.verifyElementDisplayed(quantity);
		String actualText = quantity.getAttribute("value");
		Assert.assertTrue(actualText.contains(expectedText), expectedText + " is not displayed");
	}
	
	public void clickDeleteProductBtn() {
		WebElement deleteProduct = driver.findElement(deleteProductBtn);
		validateHelper.clickElement2(deleteProduct);
	}

	public void verifySuccessMsg(String expectedMsg) {
		WebElement success = driver.findElement(message);
		validateHelper.verifyElementDisplayed(success);
		String actualMsg = success.getText();
		Assert.assertTrue(actualMsg.contains(expectedMsg), expectedMsg + " is not displayed");
	}
}
