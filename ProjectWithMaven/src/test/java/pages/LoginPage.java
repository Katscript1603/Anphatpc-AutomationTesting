package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bases.ValidateHelper;

public class LoginPage {
	private WebDriver driver;
	private ValidateHelper validateHelper;
	
	private By emailInput = By.xpath("//input[@id='email']");
	private By passwordInput = By.xpath("//input[@id='password']");
	private By loginBtn = By.xpath("//input[@value='Đăng nhập']");
	
	// Khởi tạo class khi được gọi với driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		validateHelper = new ValidateHelper(driver);
	}

	public void login(String email, String password) throws Exception {
		enterEmail(email);
		enterPassword(password);
		TimeUnit.SECONDS.sleep(1);
		clickLoginBtn();
	}

	public void enterEmail(String email) {
		WebElement emailTxtBox = driver.findElement(emailInput);
		validateHelper.setText(emailTxtBox, email);
	}
	
	public void enterPassword(String password) {
		WebElement passwordTxtBox = driver.findElement(passwordInput);
		validateHelper.setText(passwordTxtBox, password);
	}

	public void clickLoginBtn() {
		WebElement login = driver.findElement(loginBtn);
		validateHelper.clickElement(login);
	}
	
	public void verifyLoginBtnNotDisplayed() {
		WebElement element = driver.findElement(loginBtn);
		Assert.assertFalse(element.isDisplayed(), element.toString() + " is not displayed");
	}
	
}
