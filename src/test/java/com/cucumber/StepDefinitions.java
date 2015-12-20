package com.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	public WebDriver driver;
	public static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
			.format(Calendar.getInstance().getTime());

	@Before("@chrome")
	public void setUpChrome() {
		// System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")
		// + "\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",
				"D:\\demoCucumber-masterssss\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Before("@firefox")
	public void setUpFirefox() {
		driver = new FirefoxDriver();
	}

	@Before("@ie")
	public void setupIe() {
		System.setProperty("webdriver.ie.driver",
				System.getProperty("user.dir") + "\\IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities
				.internetExplorer();
		ieCapabilities
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		driver = new InternetExplorerDriver(ieCapabilities);
	}

	@Given("^I go to \"([^\"]*)\" browser$")
	public void chooseBrowser(String browser1) {
		String arg = browser1;
		System.out.println("Browser: " + browser1);
	}

	@When("^I fill in \"([^\"]*)\" with \"([^\"]*)\" using \"([^\"]*)\"$")
	public void I_fill_in_with_using(String elementIdentifier, String text,
			Integer type) throws Throwable {
		WebElement element = null;
		switch (type) {
		case 1:
			element = driver.findElement(By.name(elementIdentifier));
			break;
		case 2:
			element = driver.findElement(By.className(elementIdentifier));
			break;
		case 3:
			element = driver.findElement(By.id(elementIdentifier));
			break;
		case 4:
			element = driver.findElement(By.cssSelector(elementIdentifier));
			break;
		case 5:
			element = driver.findElement(By.xpath(elementIdentifier));
			break;
		}
		element.clear();
		element.sendKeys(text);
	}

	@When("^I click \"([^\"]*)\" using \"([^\"]*)\"$")
	public void I_click(String elementIdentifier, Integer type)
			throws Throwable {
		WebElement element = null;
		switch (type) {
		case 1:
			element = driver.findElement(By.name(elementIdentifier));
			break;
		case 2:
			element = driver.findElement(By.className(elementIdentifier));
			break;
		case 3:
			element = driver.findElement(By.id(elementIdentifier));
			break;
		case 4:
			element = driver.findElement(By.cssSelector(elementIdentifier));
			break;
		case 5:
			element = driver.findElement(By.xpath(elementIdentifier));
			break;
		case 6:
			element = driver.findElement(By.linkText(elementIdentifier));
			break;
		}
		element.click();
		Thread.sleep(2000);
	}

	@Then("^switch to \"([^\"]*)\"$")
	public void switch_to(String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				return;

			}

		}
	}

	@Then("^I close popup window$")
	public void closePopup() {

		try {
			WebElement findpopup = driver.findElement(By.xpath("//w-div/span"));
			findpopup.click();
			System.out.println("popup found");
		} catch (NoSuchElementException e) {
			System.out.println("popup not found");
		}

	}

	@Then("^I should see \"([^\"]*)\"$")
	public void I_should_see(String text) throws Throwable {
		String element = driver.getPageSource();
		assertTrue(element.contains(StringEscapeUtils.escapeHtml4(text)
				.toString()));
	}

	@Then("^I take a screenshot$")
	public void i_take_a_screenshot() throws IOException {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
				+ "\\Screenshots\\ScreenShot" + timeStamp + ".jpg"));
	}

	@Given("^I wait for two seconds$")
	public void I_wait_for_two_seconds() throws Throwable {
		Thread.sleep(2000);
	}

	@Given("^I pressed enter key$")
	public void I_pressed_Enter_key() throws Throwable {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	@Given("^I pressed Down key$")
	public void I_pressed_Down_key() throws Throwable {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.DOWN).build().perform();
	}

	@Then("^I should see \"([^\"]*)\" in \"([^\"]*)\" element using \"([^\"]*)\"$")
	public void I_should_see_in_element(String text, String elementIdentifier,
			Integer type) throws Throwable {
		WebElement element = null;
		switch (type) {
		case 1:
			element = driver.findElement(By.name(elementIdentifier));
			break;
		case 2:
			element = driver.findElement(By.className(elementIdentifier));
			break;
		case 3:
			element = driver.findElement(By.id(elementIdentifier));
			break;
		case 4:
			element = driver.findElement(By.cssSelector(elementIdentifier));
			break;
		case 5:
			element = driver.findElement(By.xpath(elementIdentifier));
			break;
		}
		assertTrue(element.getText().contains(
				StringEscapeUtils.escapeHtml4(text).toString()));

	}

	@Then("^I should see image \"([^\"]*)\" in \"([^\"]*)\" element using \"([^\"]*)\"$")
	public void I_should_see_image_in_element(String text,
			String elementIdentifier, Integer type) throws Throwable {
		WebElement element = null;
		switch (type) {
		case 1:
			element = driver.findElement(By.name(elementIdentifier));
			break;
		case 2:
			element = driver.findElement(By.className(elementIdentifier));
			break;
		case 3:
			element = driver.findElement(By.id(elementIdentifier));
			break;
		case 4:
			element = driver.findElement(By.cssSelector(elementIdentifier));
			break;
		case 5:
			element = driver.findElement(By.xpath(elementIdentifier));
			break;
		}

		assertTrue(element.getAttribute("src").contains(
				StringEscapeUtils.escapeHtml4(text).toString()));

	}

	@Then("^I should be on \"([^\"]*)\"$")
	public void I_should_be_on(String text) throws Throwable {
		String url = driver.getCurrentUrl();
		assertEquals(text, url);
	}

	@When("^I select \"([^\"]*)\" from \"([^\"]*)\" using \"([^\"]*)\"$")
	public void I_select_from_using(String text, String elementIdentifier,
			int type) throws Throwable {
		WebElement element = null;
		switch (type) {
		case 1:
			element = driver.findElement(By.name(elementIdentifier));
			break;
		case 2:
			element = driver.findElement(By.className(elementIdentifier));
			break;
		case 3:
			element = driver.findElement(By.id(elementIdentifier));
			break;
		case 4:
			element = driver.findElement(By.cssSelector(elementIdentifier));
			break;
		case 5:
			element = driver.findElement(By.xpath(elementIdentifier));
			break;
		}
		Select select = new Select(element);
		// select.selectByValue(text);
		select.selectByVisibleText(text);
	}

	@When("^I Mousehover \"([^\"]*)\" using \"([^\"]*)\"$")
	public void I_Mousehover(String elementIdentifier, Integer type)
			throws Throwable {
		Actions builder = new Actions(driver);
		// builder.moveToElement(clickElement).click().perform();
		WebElement element = null;
		switch (type) {
		case 1:
			element = driver.findElement(By.name(elementIdentifier));
			break;
		case 2:
			element = driver.findElement(By.className(elementIdentifier));
			break;
		case 3:
			element = driver.findElement(By.id(elementIdentifier));
			break;
		case 4:
			element = driver.findElement(By.cssSelector(elementIdentifier));
			break;
		case 5:
			element = driver.findElement(By.xpath(elementIdentifier));
			break;
		}
		builder.moveToElement(element).perform();

		// Thread.sleep(2000);
	}

	@Given("^I wait for ten seconds$")
	public void I_wait_for_ten_seconds() throws Throwable {
		Thread.sleep(5000);
	}

	@Then("^I should see \"([^\"]*)\" element using \"([^\"]*)\"$")
	public void I_should_see(String elementIdentifier, Integer type)
			throws Throwable {
		WebElement element = null;
		switch (type) {
		case 1:
			element = driver.findElement(By.name(elementIdentifier));
			break;
		case 2:
			element = driver.findElement(By.className(elementIdentifier));
			break;
		case 3:
			element = driver.findElement(By.id(elementIdentifier));
			break;
		case 4:
			element = driver.findElement(By.cssSelector(elementIdentifier));
			break;
		case 5:
			element = driver.findElement(By.xpath(elementIdentifier));
			break;
		}
		assertTrue(!element.getText().isEmpty());
	}

	@After
	public void embedScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				byte[] screenshot = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots
						.getMessage());
			}
		}

	}

	public void takeScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		String fileName = UUID.randomUUID().toString();
		File targetFile = new File("target/screenshots/" + fileName + ".jpg");
		FileUtils.copyFile(scrFile, targetFile);
	}
}
