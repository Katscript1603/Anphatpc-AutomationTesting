package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bases.ValidateHelper;

public class HomePage {
	private WebDriver driver;
	private ValidateHelper validateHelper;
	
	private By productLink = By.xpath("(//a[@class='p-img'])[4]");
	private By cartLink = By.xpath("//a[@title='Giỏ hàng của bạn']");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		validateHelper = new ValidateHelper(driver);
	}
	
	public WebDriver openCartManagementPage() {
		WebElement cart = driver.findElement(cartLink);
		validateHelper.clickElement(cart);
		return driver;
	}
	
	public WebDriver openProductDetailPage() {
		WebElement product = driver.findElement(productLink);
		validateHelper.scrollToView(product);
		validateHelper.clickElement(product);
		return driver;
	}
}
