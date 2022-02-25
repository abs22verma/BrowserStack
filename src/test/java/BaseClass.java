import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseClass {

	public WebElement findById(WebDriver driver, String id) {
		return driver.findElement(By.id(id));
	}
	
	public WebElement findByXpath(WebDriver driver, String xpath) {
		//scrollToElement(driver, driver.findElement(By.xpath(xpath)));
		return driver.findElement(By.xpath(xpath));
	}
	
	public void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
		
	}
}
