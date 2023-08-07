package bases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {

    private static WebDriver driver;

	static String driverPath = "resourses\\drivers\\";

	public static WebDriver getDriver() {
		return driver;
	}

	// Hàm này để tùy chọn Browser. Cho chạy trước khi gọi class này (BeforeClass)
	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		case "opera":
			driver = initOperaDriver(appURL);
			break;
		default:
			System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
			driver = initChromeDriver(appURL);
		}
	}

	// Khởi tạo cấu hình của các Browser để đưa vào Switch Case

	private WebDriver initChromeDriver(String appURL) {
		System.out.println("Launching Chrome browser...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	private WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser...");
//		System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	
	private WebDriver initOperaDriver(String appURL) {
		System.out.println("Launching Opera browser...");
		WebDriverManager.operadriver().setup();
		driver = new OperaDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	// Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			// Khởi tạo driver và browser
			setDriver(browserType, appURL);
			waitForPageLoaded();
		} catch (Exception e) {
			System.out.println("Error..." + e.getStackTrace());
		}
	}
	
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}


	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}
}
