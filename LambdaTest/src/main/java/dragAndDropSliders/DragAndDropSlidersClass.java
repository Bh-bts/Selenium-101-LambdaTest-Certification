package dragAndDropSliders;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropSlidersClass {

	private RemoteWebDriver driver;
	private String Status = "passed";

	@BeforeMethod
	public void setup(Method m, ITestContext ctx) throws MalformedURLException {
		String username = System.getenv("LT_USERNAME") == null ? "thumarbhavin.786" : System.getenv("LT_USERNAME");
		String authkey = System.getenv("LT_ACCESS_KEY") == null ? "TBcrP05QkFLjiomKmAgX9y4fRdgikSsx7CsAFN5pRFoy8Iu2fj" : System.getenv("LT_ACCESS_KEY");
		String hub = "@hub.lambdatest.com/wd/hub";

		DesiredCapabilities caps = new DesiredCapabilities();
		// Configure your capabilities here
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("version", "103.0");
		caps.setCapability("resolution", "1024x768");
		caps.setCapability("build", "TestNG With Java");
		caps.setCapability("name", m.getName() + this.getClass().getName());
		caps.setCapability("plugin", "git-testng");

		String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
		caps.setCapability("tags", Tags);

		driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
	}

	@Test

	public void TestScenario2() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		WebElement DrageAndDroplink = driver.findElement(
				By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo']"));
		DrageAndDroplink.click();

		Thread.sleep(1000);
		WebElement slider3 = driver.findElement(By.xpath(".//*[@id='slider3']/div/input"));
		// js.executeScript("arguments[0].scrollIntoView(true);", slider3);
		Thread.sleep(1000);
		Actions move = new Actions(driver);
		Actions action = (Actions) move.dragAndDropBy(slider3, 99, 0);
		action.perform();

		WebElement Expected_Range = driver.findElement(By.xpath(".//*[@id='slider3']/div/output"));
		String Expe_range = Expected_Range.getText();
		String Actual_Range = "95";

		if (Expe_range.contains(Actual_Range)) {
			System.out.println("Range is matched");
		} else {
			System.out.println("Range is not matched!");
		}

	}

	@AfterMethod

	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}

