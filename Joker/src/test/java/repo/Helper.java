package repo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Runner.runner;
import cucumber.api.java.Before;



public class Helper {

	public static WebDriver driver;


	public static void before() {

		System.setProperty("webdriver.chrome.driver", "E:\\cucumber-selenium jar file\\chromedriver.exe");

		driver = new ChromeDriver(); 


		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//static WebDriver driver;



	public static String openUrl(String url) throws IOException, ParseException {
		url = url.toLowerCase();

		url = url.replaceAll(" ","_");

		FileReader reader = new FileReader("D:\\Joker\\Joker\\src\\test\\java\\repo\\globalConfig.json");

		JSONParser jsonparser = new JSONParser();

		Object obj = jsonparser.parse(reader);

		JSONObject jo = (JSONObject) obj;

		String URL = (String)jo.get(url);

		System.out.println("Launching url");

		System.out.println("my url is:"+URL);

		return URL;
	}


	public static  WebElement getElement(String Element) throws IOException, ParseException {
		//	WebDriverWait  wait = new WebDriverWait(driver,30);

		Element = Element.toLowerCase();

		Element = Element.replaceAll(" ","_");

		FileReader reader = new FileReader("D:\\Joker\\Joker\\src\\test\\java\\repo\\objectrepository.json");

		JSONParser parser = new JSONParser();

		Object obj = parser.parse(reader);

		JSONObject jo = (JSONObject) obj;

		JSONObject buttonObj = (JSONObject) jo.get(Element);

		String locator;

		locator = (String) buttonObj.get("locator");

		String locatorType = (String) buttonObj.get("locatorType");

		WebElement webobj0 = null;

		switch(locatorType) {
		case "id":
			webobj0=driver.findElement(By.id(locator));

			break;
		case "linkText":
			webobj0=driver.findElement(By.linkText(locator));

			break;
		case "className":
			webobj0 = driver.findElement(By.className(locator));

			break;
		case "name":
			webobj0 = driver.findElement(By.name(locator));

			break;
		case "cssSelector":
			webobj0 = driver.findElement(By.cssSelector(locator));

			break;
		case "xpath":
			webobj0 = driver.findElement(By.xpath(locator));

			break;
		case "partialLinkText":
			webobj0 = driver.findElement(By.partialLinkText(locator));

			break;
		case "tagName":
			webobj0 = driver.findElement(By.tagName(locator));

			break;
		default:
			System.out.println("locator type invalid"+locatorType);
		}
		return webobj0;

	}







}
