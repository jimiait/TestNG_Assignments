package Assignment3;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContact {

	WebDriver driver;
	Select sel;
	Actions action;
	String browser;
	String url;
	String userName;
	String password;
	String dashboardHeader;
	String addContact;
	String fullName;
	String email;
	String phoneNumber;
	String address;
	String city;
	String state;
	String zipcode;
	String popupAddNewGroup;
	String newPassword;
	String confirmNewPassword;

	By USER_NAME_FIELD = By.xpath("//input[@id='username']");
	By PASSWORD_FIELD = By.xpath("//input[@id='password']");
	By LOGIN_FIELD = By.xpath("//button[@name='login']");
	By DASHBOARD_HEADER_FIELD = By.xpath("//div[@class='col-lg-12']/h2");
	By CUSTOMERS_BUTTON_FIELD = By.xpath("//ul[@id='side-menu']/li[3]/descendant::span");
	By ADD_CUSTOMERS_BUTTON_FIELD = By.xpath("//a[text()='Add Customer']");

	By ADD_CONTACT_HEADER_FIELD = By.xpath("//h5[text()='Add Contact']");
	By FULL_NAME_FIELD = By.xpath("//input[@id='account']");

	By COMPANY_DROP_DOWN_FIELD = By.xpath("//select[@id='cid']");
	By EMAIL_FIELD = By.xpath("//input[@id='email']");
	By PHONE_NUMBER_FIELD = By.xpath("//input[@id='phone']");
	By ADDRESS_FIELD = By.xpath("//input[@id='address']");
	By CITY_FIELD = By.xpath("//input[@id='city']");
	By STATE_OR_REGION_FIELD = By.xpath("//input[@id='state']");
	By ZIP_OR_POSTAL_CODE_FIELD = By.xpath("//input[@id='zip']");
	By COUNTRY_DROPDOWN_FIELD = By.xpath("//select[@id='country']");
	By TAG_FIELD = By.xpath("//select[@id='tags']");
	By CURRENCY_DROPDOWN_FIELD = By.xpath("//select[@id='currency']");
	By GROUP_ID_DROPDOWN_FIELD = By.xpath("//select[@id='group']");
	By ADD_NEW_GROUP_FIELD = By.xpath("//a[text()='Add New Group']");
	By ALERT_POPUP_GROUP_FIELD = By.xpath("//input[@id='group_name']"); // questions popup
	By SAVE_NEW_GROUP_FIELD = By.xpath("//button[text()='Save']"); // questions save popup
	By CUSTOMER_PASSWORD_FIELD = By.xpath("//input[@id='password']");
	By CONFIRM_CUSTOMER_PASSWORD_FIELD = By.xpath("//input[@id='cpassword']");
	By WELCOME_EMAIL_FIELD = By.xpath("//div[@class='toggle-group']/label[2]");
	By SAVE_INFORMATION_FIELD = By.xpath("//button[@id='submit']");

	By LSIT_OF_CUSTOMERS_FIELD = By.xpath("//ul[@id='side-menu']/li[3]/ul/li[2]/a");
	By CUSTOMER_SEARCH_BAR_FIELD = By.xpath("//input[@id='foo_filter']");
	By SEARCH_BUTTON_FIELD = By.xpath("//span[@class='fa fa-search']");

	@BeforeClass
	public void config() {
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
			userName = prop.getProperty("userName");
			password = prop.getProperty("password");
			dashboardHeader = prop.getProperty("dashboardHeader");
			addContact = prop.getProperty("addContact");
			fullName = prop.getProperty("fullName");
			email = prop.getProperty("email");
			phoneNumber = prop.getProperty("phoneNumber");
			address = prop.getProperty("address");
			city = prop.getProperty("city");
			state = prop.getProperty("state");
			zipcode = prop.getProperty("zipcode");
			popupAddNewGroup = prop.getProperty("popupAddNewGroup");
			newPassword = prop.getProperty("newPassword");
			confirmNewPassword = prop.getProperty("confirmNewPassword");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver4.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(url);
	};

	@Test
	public void login() throws InterruptedException {

		driver.findElement(USER_NAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(LOGIN_FIELD).click();

		Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), dashboardHeader, "Page not found");

		driver.findElement(CUSTOMERS_BUTTON_FIELD).click();
		driver.findElement(ADD_CUSTOMERS_BUTTON_FIELD).click();

		Assert.assertEquals(driver.findElement(ADD_CONTACT_HEADER_FIELD).getText(), addContact, "Page not found");

		driver.findElement(FULL_NAME_FIELD).sendKeys(fullName);
		sel = new Select(driver.findElement(COMPANY_DROP_DOWN_FIELD));
		sel.selectByVisibleText("Amazon");

		driver.findElement(EMAIL_FIELD).sendKeys(email);
		driver.findElement(PHONE_NUMBER_FIELD).sendKeys(phoneNumber);
		driver.findElement(ADDRESS_FIELD).sendKeys(address);
		driver.findElement(CITY_FIELD).sendKeys(city);
		driver.findElement(STATE_OR_REGION_FIELD).sendKeys(state);
		driver.findElement(ZIP_OR_POSTAL_CODE_FIELD).sendKeys(zipcode);

		sel = new Select(driver.findElement(COUNTRY_DROPDOWN_FIELD));
		sel.selectByVisibleText("Bangladesh");

		sel = new Select(driver.findElement(TAG_FIELD));
		sel.selectByVisibleText("IT Training");

		sel = new Select(driver.findElement(CURRENCY_DROPDOWN_FIELD));
		sel.selectByVisibleText("USD");

		sel = new Select(driver.findElement(GROUP_ID_DROPDOWN_FIELD));
		sel.selectByVisibleText("Selenium");

//		driver.findElement(ADD_NEW_GROUP_FIELD).click(); QUESTIONS POP UP

//		driver.switchTo().alert().sendKeys("CNS"); //QUESTIONS POP UP ALERT
//		driver.switchTo().alert().accept(); //QUESTIONS POP UP ALERT
//		driver.findElement(ALERT_POPUP_GROUP_FIELD).sendKeys(popupAddNewGroup);
//		driver.findElement(ADD_NEW_GROUP_FIELD).click();

		driver.findElement(CUSTOMER_PASSWORD_FIELD).sendKeys(newPassword);
		driver.findElement(CONFIRM_CUSTOMER_PASSWORD_FIELD).sendKeys(confirmNewPassword);
		driver.findElement(WELCOME_EMAIL_FIELD).click(); // yes to no
		driver.findElement(SAVE_INFORMATION_FIELD).click();

		// driver.switchTo().parentFrame().findElement(CUSTOMERS_BUTTON_FIELD).click();
		driver.findElement(LSIT_OF_CUSTOMERS_FIELD).click();
		driver.findElement(CUSTOMER_SEARCH_BAR_FIELD).sendKeys("Pream");
//		action = new Actions(driver);
//		action.sendKeys(Keys.ENTER).build().perform();
		driver.findElement(SEARCH_BUTTON_FIELD).click();
		Thread.sleep(4000);

	}

	// @AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
