package testcases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bases.BaseSetup;
import bases.ValidateHelper;
import pages.CartManagementPage;
import pages.HomePage;
import pages.ProductDetailPage;
import pages.RegistrationPage;

public class CartManagementTest extends BaseSetup {

	private WebDriver driver;
	private HomePage homePage;
	private ProductDetailPage productDetailPage;
	private CartManagementPage cartManagementPage;
	private ValidateHelper validateHelper;
	
	@Parameters({ "appURL" })
	@BeforeClass
	public void setUp(String appURL) {
		driver = getDriver();
		homePage = new HomePage(driver);
		driver.get(appURL + "/pcap-aqua-black.html");
		productDetailPage = new ProductDetailPage(driver);
		validateHelper = new ValidateHelper(driver);
	}
	
	@Test()
	public void TC_02_01() throws Exception
	{
		productDetailPage.clickAddToCartBtn();
		validateHelper.verifyMsg("Sản phẩm đã được thêm vào giỏ hàng !");
	}
	
	@Test()
	public void TC_02_02() throws Exception
	{
		productDetailPage.clickAddToCartBtn();
		validateHelper.verifyMsg("Sản phẩm đã được thêm vào giỏ hàng !");
	}
	
	@Test()
	public void TC_02_03() throws Exception
	{
		driver = homePage.openCartManagementPage();
		cartManagementPage = new CartManagementPage(driver);
		cartManagementPage.clickIncreaseBtn();
		cartManagementPage.verifyQuantityText("2");
	}
	
	@Test()
	public void TC_02_04() throws Exception
	{
		cartManagementPage.clickDecreaseBtn();
		cartManagementPage.verifyQuantityText("1");
	}
	
	@Test()
	public void TC_02_05() throws Exception
	{
		cartManagementPage.clickDeleteProductBtn();
		cartManagementPage.verifySuccessMsg("Có 0 sản phẩm trong giỏ hàng");
	}
}
