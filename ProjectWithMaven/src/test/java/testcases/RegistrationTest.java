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
import pages.RegistrationPage;

public class RegistrationTest extends BaseSetup {

	private WebDriver driver;
	private RegistrationPage registrationPage;
	private ValidateHelper validateHelper;
	private LocalDateTime currentTime;
	private DateTimeFormatter formatter;
	private String formattedTime;
	
	@BeforeClass
	public void setUp() {
		driver = getDriver();
		registrationPage = new RegistrationPage(driver);
		validateHelper = new ValidateHelper(driver);
	}
	
	@Parameters({ "appURL" })
	@BeforeMethod
	public void setUpBeforeTest(String appURL) {
		driver.get(appURL + "/dang-ky");
		currentTime = LocalDateTime.now();
		formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
		formattedTime = currentTime.format(formatter);
	}

	@Test()
	public void TC_01_01() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "", "");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_02() throws Exception
	{
		registrationPage.register("", "Abc123", "Abc123", "test", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Bạn chưa nhập email");
	}
	
	@Test()
	public void TC_01_03() throws Exception
	{
		registrationPage.enterInvalidEmail("          ");
		registrationPage.verifyErrorMsg("Email không đúng");
	}
	
	@Test()
	public void TC_01_04() throws Exception
	{
		registrationPage.enterInvalidEmail("test");
		registrationPage.verifyErrorMsg("Email test không đúng");
	}
	
	@Test()
	public void TC_01_05() throws Exception
	{
		registrationPage.register(" test" + formattedTime + "@gmail.com ", "Abc123", "Abc123", "test", "", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_06() throws Exception
	{
		registrationPage.enterInvalidEmail("test 06@gmail.com");
		registrationPage.verifyErrorMsg("Email test 06@gmail.com không đúng");
	}
	
	@Test()
	public void TC_01_07() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
		registrationPage.clickRegisterLink();
		registrationPage.enterInvalidEmail("test" + formattedTime + "@gmail.com");
		registrationPage.verifyErrorMsg("Email " + "test" + formattedTime + "@gmail.com" + " đã được đăng ký ở tài khoản khác");
	}
	
	@Test()
	public void TC_01_08() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "", "Abc123", "test", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Bạn chưa nhập mật khẩu");
	}
	
	@Test()
	public void TC_01_09() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "      ", "      ", "test", "", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_10() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc12", "Abc123", "test", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Mật khẩu phải tối thiểu 6 ký tự");
	}
	
	@Test()
	public void TC_01_11() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_12() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc1234", "Abc1234", "test", "", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_13() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "", "test", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Bạn chưa nhập lại mật khẩu");
	}
	
	@Test()
	public void TC_01_14() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc1234", "test", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Mật khẩu nhập lại không chính xác");
	}
	
	@Test()
	public void TC_01_15() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Bạn chưa nhập họ tên");
	}
	
	@Test()
	public void TC_01_16() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "   ", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Bạn chưa nhập họ tên");
	}
	
	@Test()
	public void TC_01_17() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "Ab", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Họ tên phải tối thiểu 3 ký tự");
	}
	
	@Test()
	public void TC_01_18() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "Abc", "", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_19() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "Abcd", "", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_20() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "@#$", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Họ tên không hợp lệ");
	}
	
	@Test()
	public void TC_01_21() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "123", "", "", "Hà Nội", "0987654321");
		validateHelper.verifyMsg("Họ tên không hợp lệ");
	}
	
	@Test()
	public void TC_01_22() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "Nữ", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_23() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "Thái Bình", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_24() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_25() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "          ", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_26() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_27() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "         ");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_28() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "098765432");
		registrationPage.verifySuccessMsg("Điện thoại không hợp lệ");
	}
	
	@Test()
	public void TC_01_29() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "0987654321");
		registrationPage.verifySuccessMsg("Đăng ký thành công");
	}
	
	@Test()
	public void TC_01_30() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "09876543211");
		registrationPage.verifySuccessMsg("Điện thoại không hợp lệ");
	}
	
	@Test()
	public void TC_01_31() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "!@#$%^&*()");
		registrationPage.verifySuccessMsg("Điện thoại không hợp lệ");
	}
	
	@Test()
	public void TC_01_32() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "abcdefghijk");
		registrationPage.verifySuccessMsg("Điện thoại không hợp lệ");
	}
	
	@Test()
	public void TC_01_33() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "0012345678");
		registrationPage.verifySuccessMsg("Điện thoại không hợp lệ");
	}
	
	@Test()
	public void TC_01_34() throws Exception
	{
		registrationPage.register("test" + formattedTime + "@gmail.com", "Abc123", "Abc123", "test", "", "", "Hà Nội", "0000000000");
		registrationPage.verifySuccessMsg("Điện thoại không hợp lệ");
	}
}
