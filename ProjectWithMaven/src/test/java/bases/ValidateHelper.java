package bases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ValidateHelper {

	private WebDriver driver;

	public ValidateHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void setText(WebElement element, String valueText) {
		assertTrue(element.isDisplayed() && element.isEnabled());
		element.clear();
		element.sendKeys(valueText);
	}

	public void clickElement(WebElement element) {
		assertTrue(element.isDisplayed() && element.isEnabled());
		element.click();
	}
	
	public void clickElement2(WebElement element) {
		assertTrue(element.isDisplayed() && element.isEnabled());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
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

	public void verifyMsg(String expectedMsg) {
		String actualMsg = driver.switchTo().alert().getText();
		Assert.assertTrue(actualMsg.contains(expectedMsg), expectedMsg + " is not displayed");
		driver.switchTo().alert().accept();
	}
	
	public void verifyElementDisplayed(WebElement element) {
		Assert.assertTrue(element.isDisplayed(), element.toString() + " is not displayed");
	}
	
	public void scrollToView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //This will scroll the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", element);
	}
}
