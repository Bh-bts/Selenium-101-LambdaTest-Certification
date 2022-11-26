package inputFormSubmit;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InputFormSubmitClass {

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

	public void TestScenario3() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		WebElement InputFormLink = driver
				.findElement(By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/input-form-demo']"));
		InputFormLink.click();

		WebElement submit = driver.findElement(By.xpath("//div[@class='text-right mt-20']/button"));
		submit.click();

		Thread.sleep(1000);

		WebElement name = driver.findElement(By.xpath(
				"//div[@class='form-group w-4/12 smtablet:w-full text-section pr-20 smtablet:pr-0']/input[@type='text']"));
		String Expected_validation = name.getAttribute("validationMessage");
		String Actual_validation = "Please fill out this field.";
		Assert.assertEquals(Actual_validation, Expected_validation);

		if (Expected_validation.equals(Actual_validation)) {
			System.out.println("Validation is properly appear.");
		} else {
			System.out.println("Validation is not properly appear.");
		}

		name.sendKeys("TestName");

		WebElement email = driver.findElement(By.xpath(
				"//div[@class='form-group w-4/12 smtablet:w-full text-section pr-20 smtablet:pr-0']/input[@type='email']"));
		email.sendKeys("Test123@gmail.com");

		WebElement password = driver
				.findElement(By.xpath("//div[@class='form-group w-4/12 smtablet:w-full']/input[@type='password']"));
		password.sendKeys("Test@1234");

		WebElement company = driver.findElement(By.xpath("//*[@id=\"company\"]"));
		company.sendKeys("TestCompany");

		WebElement website = driver
				.findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id=\"websitename\"]"));
		website.sendKeys("Testdomain.com");

		WebElement country = driver.findElement(By.xpath(
				"//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/select[@name='country']"));
		Select select = new Select(country);
		select.selectByVisibleText("United States");

		WebElement city = driver
				.findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputCity']"));
		city.sendKeys("TestCity");

		WebElement address1 = driver.findElement(By.xpath(
				"//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/input[@id='inputAddress1']"));
		address1.sendKeys("TestAddress1");

		WebElement address2 = driver
				.findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputAddress2']"));
		address2.sendKeys("TestAddress2");

		WebElement state = driver.findElement(By.xpath(
				"//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/input[@id='inputState']"));
		state.sendKeys("TestState");

		WebElement zipcode = driver
				.findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputZip']"));
		zipcode.sendKeys("360002");

		submit.click();

		Thread.sleep(2000);

		WebElement successmessage = driver.findElement(
				By.xpath("//div[@class='loginform border border-gray-90 mt-20 p-20']/p[@class='success-msg hidden']"));
		String Actualmessage = successmessage.getText();
		String Expectedmessage = "Thanks for contacting us, we will get back to you shortly.";

		if (Actualmessage.equals(Expectedmessage)) {
			System.out.println("Success message is properly appear.");
		} else {
			System.out.println("Success message is not properly appear.");
		}

	}

	@AfterMethod

	public void tearDown() {
		driver.executeScript("lambda-status=" + Status);
		driver.quit();
	}
}

