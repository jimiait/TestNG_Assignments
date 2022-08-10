package Assignment3;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValueFromDropDown {
	WebDriver driver;
	Select sel;
	String browser;
	String dashboardHeader = "Dashboard";
	String addContact = "Add Contact";

	By USER_NAME_FIELD = By.xpath("//input[@id='username']");
	By PASSWORD_FIELD = By.xpath("//input[@id='password']");
	By LOGIN_FIELD = By.xpath("//button[@name='login']");
	By DASHBOARD_HEADER_FIELD = By.xpath("//div[@class='col-lg-12']/h2");
	By CUSTOMERS_BUTTON_FIELD = By.xpath("//ul[@id='side-menu']/li[3]/descendant::span");
	By ADD_CUSTOMERS_BUTTON_FIELD = By.xpath("//a[text()='Add Customer']");
	By ADD_CONTACT_HEADER_FIELD = By.xpath("//h5[text()='Add Contact']");
	By COMPANY_DROPDOWN_LIST_FIELD = By.xpath("//select[@id='cid']");

	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver4.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.techfios.com/billing/?ng=admin/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void dropDownFullList() {
		driver.findElement(USER_NAME_FIELD).sendKeys("demo@techfios.com");
		driver.findElement(PASSWORD_FIELD).sendKeys("abc123");
		driver.findElement(LOGIN_FIELD).click();

		Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), dashboardHeader, "Page not found");

		driver.findElement(CUSTOMERS_BUTTON_FIELD).click();
		driver.findElement(ADD_CUSTOMERS_BUTTON_FIELD).click();

		Assert.assertEquals(driver.findElement(ADD_CONTACT_HEADER_FIELD).getText(), addContact, "Page not found");

		sel = new Select(driver.findElement(COMPANY_DROPDOWN_LIST_FIELD));
		List<WebElement> fullList = sel.getOptions();
		for (WebElement i : fullList) {
			System.out.println(i.getText());
		}

	}

	// @AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();

	}

}
