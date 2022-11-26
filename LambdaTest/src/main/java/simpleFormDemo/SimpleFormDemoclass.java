package simpleFormDemo;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleFormDemoclass {

	private RemoteWebDriver driver;
	private String Status = "passed";

	@BeforeMethod
	public void setup(Method m, ITestContext ctx) throws MalformedURLException {
		String username = System.getenv("LT_USERNAME") == null ? "thumarbhavin.786" : System.getenv("LT_USERNAME");
		String authkey = System.getenv("LT_ACCESS_KEY") == null ? "TBcrP05QkFLjiomKmAgX9y4fRdgikSsx7CsAFN5pRFoy8Iu2fj"
				: System.getenv("LT_ACCESS_KEY");
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

	public void TestScenario1() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		WebElement SimpleFormDemoLink = driver
				.findElement(By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/simple-form-demo']"));
		SimpleFormDemoLink.click();

		String Expectedurl = driver.getCurrentUrl();
		String Actualurl = "simple-form-demo";

		if (Expectedurl.contains(Actualurl)) {
			System.out.println("URL matched");
		} else {
			System.out.println("URL does not matched!");
		}

		String message = "Welcome to LambdaTest.";
		WebElement mess_send = driver
				.findElement(By.xpath("//div/input[@class='border border-gray-550 w-full h-35 rounded px-10']"));
		Thread.sleep(1000);
		mess_send.sendKeys(message);

		Thread.sleep(1000);
		WebElement button = driver.findElement(By.xpath(
				"//div/button[@class='mt-20 mb-10 bg-black text-white rounded px-15 py-5 hover:bg-lambda-900 focus:outline-none']"));
		button.click();

		WebElement your_mess = driver
				.findElement(By.xpath("//div[@class='w-4/12 smtablet:w-full rigth-input']/div/p[1]"));
		String print_mess = your_mess.getText();

		if (print_mess.contains(message)) {
			System.out.println("Message is matched");
		} else {
			System.out.println("Message is not matched!");
		}

	}

	@AfterMethod
	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}

}

