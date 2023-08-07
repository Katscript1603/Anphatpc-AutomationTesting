package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bases.BaseSetup;
import bases.ExcelHelper;
import bases.ValidateHelper;
import pages.LoginPage;

public class LoginTest extends BaseSetup {

	private WebDriver driver;
	private LoginPage loginPage;
	private ValidateHelper validateHelper;
	private ExcelHelper excel;

	@BeforeClass
	public void setUp() {
		driver = getDriver();
		loginPage = new LoginPage(driver);
		validateHelper = new ValidateHelper(driver);
		excel = new ExcelHelper();
	}

	@Parameters({ "appURL" })
	@BeforeMethod
	public void setUpBeforeTest(String appURL) {
		driver.get(appURL + "/dang-nhap");
	}

	@Test()
	public void TC_Login() throws Exception {
		// Setup đường dẫn của file excel
		excel.setExcelFile("src/test/resources/TestDataLogin.xlsx", "Sheet1");

		for (int i = 1; i <= 2; i++) {
			loginPage.login(excel.getCellData("email", i), excel.getCellData("password", i));
			if (excel.getCellData("result", i).equals("success")) {
				validateHelper.verifyMsg("Tài khoản đăng nhập không chính xác");
			}
			else {
				loginPage.verifyLoginBtnNotDisplayed();
			}
			Thread.sleep(1000);
		}
	}
}
