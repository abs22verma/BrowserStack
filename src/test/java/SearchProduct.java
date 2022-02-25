
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SearchProduct {
	
	public WebDriver driver = null;
	
	@BeforeSuite	
	public void openBrowser() {
		
		System.setProperty("webdriver.gecko.driver", "F:\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
	}

	@Test
	public void TC01() throws InterruptedException {
		BaseClass base = new BaseClass();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		base.findById(driver, "twotabsearchtextbox").sendKeys("OnePlus 9");
		base.findById(driver, "nav-search-submit-button").submit();
		Thread.sleep(4000);
		Actions builder = new Actions(driver);
		Action action = builder.moveToElement(base.findByXpath(driver, "//*[@aria-label='Sort by:']"))
				.click(base.findByXpath(driver, "//*[@aria-label='Sort by:']")).build();
		action.perform();
		
		Thread.sleep(4000);
		builder.moveToElement(base.findByXpath(driver, "//a[text()='Avg. Customer Review']")).click().perform();
		
		Thread.sleep(4000);
		base.findByXpath(driver, "//*[@aria-label='OnePlus']//child::div").click();
		Thread.sleep(4000);
		base.scrollToElement(driver, base.findByXpath(driver, "//*[@aria-label='128 GB']//child::div"));
		 base.findByXpath(driver, "//*[@aria-label='128 GB']//child::div").click();
		
		Thread.sleep(4000);

		Action action1 = builder.moveToElement(base.findByXpath(driver, "//*[@aria-label='Sort by:']"))
				.click(base.findByXpath(driver, "//*[@aria-label='Sort by:']")).build();
		action1.perform();
		Thread.sleep(4000);
		//
		builder.moveToElement(base.findByXpath(driver, "//a[text()='Price: Low to High']")).click().perform();
		
		
		List<WebElement> list = new ArrayList<WebElement>();
		
		list = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
		System.out.println(list.size());
		
		for(int i=1;i<list.size(); i++) {
			System.out.println(list.get(i).getText());
			System.out.println(driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small']//child::h2/a)["+i+"]")).getAttribute("href"));
			System.out.println(list.get(i).findElement(By.xpath("(//div[@class='a-row a-size-base a-color-base']//span//span[@class='a-price-whole'])["+i+"]")).getText());
			
		}
		
		
		
	}
}
