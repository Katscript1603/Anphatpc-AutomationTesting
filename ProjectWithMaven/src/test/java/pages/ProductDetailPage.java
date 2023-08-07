package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bases.ValidateHelper;

public class ProductDetailPage {
	private WebDriver driver;
	private ValidateHelper validateHelper;
	private By addToCartBtn = By.xpath("//a[contains(@class,'btn-addCart')]");
	
	// Khởi tạo class khi được gọi với driver
	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		validateHelper = new ValidateHelper(driver);
	}
	
	public void clickAddToCartBtn() {
		WebElement addToCart = driver.findElement(addToCartBtn);
		validateHelper.clickElement(addToCart);
	}
}
