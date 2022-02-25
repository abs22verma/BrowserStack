
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
class TestClass1 implements Runnable {
	public void run() {
		Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
		capsHashtable.put("browser", "chrome");
		capsHashtable.put("browser_version", "latest");
		capsHashtable.put("os", "Windows");
		capsHashtable.put("os_version", "7");
    	capsHashtable.put("build", "browserstack-build-1");
		capsHashtable.put("name", "Thread 1");
		TestBrowserStack r1 = new TestBrowserStack();
		r1.executeTest(capsHashtable);
    }
}
class TestClass2 implements Runnable {
	public void run() {
		Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
		capsHashtable.put("browser", "firefox");
		capsHashtable.put("browser_version", "latest");
		capsHashtable.put("os", "Windows");
		capsHashtable.put("os_version", "10");
		capsHashtable.put("build", "browserstack-build-1");
		capsHashtable.put("name", "Thread 2");
		TestBrowserStack r2 = new TestBrowserStack();
    	r2.executeTest(capsHashtable);
  	}
}
class TestClass3 implements Runnable {
	public void run() {
		Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
		capsHashtable.put("browser", "safari");
		capsHashtable.put("browser_version", "latest");
		capsHashtable.put("os", "OS X");
		capsHashtable.put("os_version", "Big Sur");
		capsHashtable.put("build", "browserstack-build-1");
		capsHashtable.put("name", "Thread 3");
		TestBrowserStack r3 = new TestBrowserStack();
    	r3.executeTest(capsHashtable);
  	}
}

public class TestBrowserStack {
	public static final String USERNAME = "";
	public static final String AUTOMATE_KEY = "";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
//	@Test
//	public void Test() {
	public static void main(String[] args) throws Exception {
		Thread object1 = new Thread(new TestClass1());
		object1.start();
		Thread object2 = new Thread(new TestClass2());
		object2.start();
		Thread object3 = new Thread(new TestClass3());
		object3.start();
  	}
	public void executeTest(Hashtable<String, String> capsHashtable) {
		String key;
		DesiredCapabilities caps = new DesiredCapabilities();
		// Iterate over the hashtable and set the capabilities
		Set<String> keys = capsHashtable.keySet();
		Iterator<String> itr = keys.iterator();
		while (itr.hasNext()) {
       		key = itr.next();
       		caps.setCapability(key, capsHashtable.get(key));
    	}
    	WebDriver driver;
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
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
			
			Thread.sleep(5000);

			Action action1 = builder.moveToElement(base.findByXpath(driver, "//*[@aria-label='Sort by:']"))
					.click(base.findByXpath(driver, "//*[@aria-label='Sort by:']")).build();
			action1.perform();
			Thread.sleep(5000);
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
			
			
			
	    	driver.quit();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}