package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import bases.ValidateHelper;

public class RegistrationPage {
	private WebDriver driver;

	private By registerLink = By.xpath("(//a[@class='d-block'])[1]");
	private By emailInput = By.xpath("//input[@id='email']");
	private By passwordInput = By.xpath("//input[@id='password']");
	private By rePasswordInput = By.xpath("//input[@id='password1']");
	private By fullNameInput = By.xpath("//input[@id='full_name']");
	private By maleRadio = By.xpath("(//input[@name='info[gender]'])[1]");
	private By femaleRadio = By.xpath("(//input[@name='info[gender]'])[2]");
	private By provinceSelect = By.xpath("//select[@name='info[province]']");
	private By addressInput = By.xpath("//input[@id='address']");
	private By mobileInput = By.xpath("//input[@id='mobile']");
	private By registerBtn = By.xpath("//input[@value='Đăng ký']");
	private By errorMsg = By.xpath("//span[@id='check_user']");
	private By successMsg = By.xpath("//div[@class='container']//h3");
	
	private ValidateHelper validateHelper;
	
	// Khởi tạo class khi được gọi với driver
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		validateHelper = new ValidateHelper(driver);
	}

	public String getRegistrationPageTitle() {
		String pageTitle = driver.getTitle();
		return pageTitle;
	}

	public boolean verifyRegistrationPageTitle() {
		String expectedTitle = "Đăng ký tài khoản";
		return getRegistrationPageTitle().equals(expectedTitle);
	}

	public void clickRegisterLink() {
		WebElement register = driver.findElement(registerLink);
		validateHelper.clickElement(register);
	}
	
	public void register(String email, String password, String rePassword, String fullName, String gender, String province, String address, String mobile) throws Exception {
		enterEmail(email);
		enterPassword(password);
		enterRePassword(rePassword);
		enterFullName(fullName);
		selectGender(gender);
		selectProvince(province);
		enterAddress(address);
		enterMobile(mobile);
		TimeUnit.SECONDS.sleep(1);
		clickRegisterBtn();
	}

	public void enterEmail(String email) {
		WebElement emailTxtBox = driver.findElement(emailInput);
		validateHelper.setText(emailTxtBox, email);
	}
	
	public void enterInvalidEmail(String email) {
		WebElement emailTxtBox = driver.findElement(emailInput);
		validateHelper.setText(emailTxtBox, email);
	}


	public void enterPassword(String password) {
		WebElement passwordTxtBox = driver.findElement(passwordInput);
		validateHelper.setText(passwordTxtBox, password);
	}
	
	public void enterRePassword(String rePassword) {
		WebElement rePasswordTxtBox = driver.findElement(rePasswordInput);
		validateHelper.setText(rePasswordTxtBox, rePassword);
	}
	
	public void enterFullName(String fullName) {
		WebElement fullNameTxtBox = driver.findElement(fullNameInput);
		validateHelper.setText(fullNameTxtBox, fullName);
	}
	
	public void selectGender(String gender) {
		WebElement female = driver.findElement(femaleRadio);
		if (!gender.equals("")) {
			validateHelper.clickElement(female);
		}
	}
	
	public void selectProvince(String province) {
		// Create the object of the Select class
		Select se = new Select(driver.findElement(provinceSelect));
					
		// Select the option using the visible text
		if (!province.equals("")) {
			se.selectByVisibleText(province);
		}
	}
	
	public void enterAddress(String address) {
		WebElement addressTxtBox = driver.findElement(addressInput);
		validateHelper.setText(addressTxtBox, address);
	}
	
	public void enterMobile(String mobile) {
		WebElement mobileTxtBox = driver.findElement(mobileInput);
		validateHelper.setText(mobileTxtBox, mobile);
	}

	public void clickRegisterBtn() {
		WebElement register = driver.findElement(registerBtn);
		validateHelper.clickElement(register);
	}

	public void verifyErrorMsg(String expectedMsg) {
		WebElement error = driver.findElement(errorMsg);
		validateHelper.verifyElementDisplayed(error);
		String actualMsg = error.getText();
		Assert.assertTrue(actualMsg.contains(expectedMsg), expectedMsg + " is not displayed");
	}

	public void verifySuccessMsg(String expectedMsg) {
		WebElement success = driver.findElement(successMsg);
		validateHelper.verifyElementDisplayed(success);
		String actualMsg = success.getText();
		Assert.assertTrue(actualMsg.contains(expectedMsg), expectedMsg + " is not displayed");
	}
}
