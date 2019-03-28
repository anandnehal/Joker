package Runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import repo.Helper;


public class runner extends Helper{
	//WebDriver driver;
	WebDriverWait wait;

	@Before
	public void befores() {
		Helper.before();
		wait = new WebDriverWait(driver,30);

	}

	@After
	public void after() {
		driver.quit();
	}

	static File jsonFile = new File("./Joker/src/test/java/repo/globalConfig.json");

	@Given("^I launch \"(.*)\" webpage$")
	public void user_is_on_homepage(String url) throws Throwable {

		System.out.println(url);
		//WebDriver dirver = new HTMLUnitDriver();
		String myURL= Helper.openUrl(url);

		driver.get(myURL);

		System.out.println("congratulation!! URL launched");

		//	driver.navigate().refresh();
		Thread.sleep(3000);

	}

	@When("^I click on \"(.*)\" button$")
	public void I_click_on_button(String button) throws Throwable {

		WebElement obj = Helper.getElement(button);

		obj = wait.until(ExpectedConditions.elementToBeClickable(obj));

		String textOnElement = obj.getText();

		obj.click();

		System.out.println("button clicked:"+textOnElement);
	}

	@And("^I enter \"(.*)\" in \"(.*)\" text box$")
	public void enter_text_in_text_box(String text,String textBox) throws IOException, ParseException {

		WebElement obj = Helper.getElement(textBox);

		obj = wait.until(ExpectedConditions.elementToBeClickable(obj));

		obj.clear();

		obj.sendKeys(text);

		System.out.println("text entered  is "+text);
	}

	@Then("^I verify text display on \"(.*)\" to be \"(.*)\"$")
	public void text_validation(String webElement,String text) throws IOException, ParseException {

		WebElement obj = Helper.getElement(webElement);

		obj = wait.until(ExpectedConditions.visibilityOf(obj));

		if(obj.getText().equals(text)) {
			System.out.println("Correct text displayed:PASS");
		}
		else {
			System.out.println("incorrect text dispalyed:Fail and displayed text is:"+obj.getText());
		}


	}

	@Then("^I verify web element \"(.*)\" is enable$")
	public void check_Enable(String webElement) throws IOException, ParseException {
		WebElement obj = Helper.getElement(webElement);

		if(obj.isEnabled()) {
			System.out.println(obj.getText()+ " is enabled");
		}
		else {
			System.out.println(obj.getText() +" is disabled");
		}
	}


	@Then("^I verify web element \"(.*)\" is diplayed$")
	public void check_disable(String webElement) throws IOException, ParseException {
		WebElement obj = Helper.getElement(webElement);

		if(obj.isDisplayed()) {
			System.out.println(obj.getText()+ " is displayed");
		}
		else {
			System.out.println(obj.getText() +" is not displayed");
		}
	}

	@And("^I verify title of webpage to be \"(.*)\"$")
	public void title_validation(String expected) {
		String actualTitle = driver.getTitle();

		System.out.println("actual title is"+actualTitle);

		Assert.assertEquals(expected, actualTitle);


	}
	@And("^I accept alert$")
	public void accept_Alert() {
		Alert alert =driver.switchTo().alert();
		alert.accept();
	}

	@And("^I dismiss alert$")
	public void dismiss_Alert() {

		Alert alert =driver.switchTo().alert();

		alert.dismiss();
	}
	@And("I enter \"(.*)\" in alert box")
	public void enterTxt(String text) {

		Alert alert =driver.switchTo().alert();

		alert.sendKeys(text);
	}
	@And("I switch to frame \"(.*)\"")
	public void switch_frame(String id) {

		driver.switchTo().frame(0);

		//driver.switchTo().frame(id);
	}

	@And("^I check off \"(.*)\" checkBox$")
	public void checkoff(String button) throws IOException, ParseException {
		WebElement obj = Helper.getElement(button);

		obj = wait.until(ExpectedConditions.elementToBeClickable(obj));

		String textOnElement = obj.getText();

		obj.click();

		System.out.println("button clicked:"+textOnElement);
	}

	@And("^I select \"(.*)\" radio button$")
	public void select_radio_button(String button) throws IOException, ParseException {
		WebElement obj = Helper.getElement(button);

		obj = wait.until(ExpectedConditions.elementToBeClickable(obj));

		String textOnElement = obj.getText();

		obj.click();

		System.out.println("button clicked:"+textOnElement);
	}

	@And("^I select \"(.*)\" from dropdown \"(.*)\"$")
	public void select_from_dropdown(String text, String object) throws IOException, ParseException {
		Select obj = new Select(Helper.getElement(object));
		obj.selectByValue(text);
		System.out.println(text + " selected from dropdown");
	}

	@Then("^I click on \"(.*)\" image$")
	public void click_on_image(String image) throws IOException, ParseException {
		WebElement obj = Helper.getElement(image);
		obj = wait.until(ExpectedConditions.elementToBeClickable(obj));
		obj.click();
	}

	@And("^I right click on \"(.*)\"$")
	public void rightClick(String webElement) throws IOException, ParseException {
		WebElement obj = Helper.getElement(webElement);
		obj = wait.until(ExpectedConditions.visibilityOf(obj));
		Actions action = new Actions(driver);
		action.contextClick(obj).perform();
		System.out.println("right click done");

	}
	
	@And("^I double click on \"(.*)\"$")
	public void doubleClick(String webElement) throws IOException, ParseException, InterruptedException {
		WebElement obj = Helper.getElement(webElement);
		obj = wait.until(ExpectedConditions.visibilityOf(obj));
		Actions action = new Actions(driver);
		action.doubleClick(obj).perform();
		Thread.sleep(2000);
		System.out.println("double click done");
	}
	
	@And("^I mouse hover on \"(.*)\"$")
	public void mousehover(String webElement) throws IOException, ParseException, InterruptedException {
		WebElement obj = Helper.getElement(webElement);
		obj = wait.until(ExpectedConditions.visibilityOf(obj));
		Actions action = new Actions(driver);
		action.moveToElement(obj).perform();
		Thread.sleep(2000);
		System.out.println("mouse hover done");
	}
	
	@And("^I upload file \"(.*)\" to \"(.*)\"$")
	public void uploadFile(String file, String location) throws IOException, ParseException, InterruptedException {
		WebElement obj = Helper.getElement(location);
		obj = wait.until(ExpectedConditions.elementToBeClickable(obj));
		obj.sendKeys(file);
		Thread.sleep(2000);
		System.out.println("File uploaded successfully");
	}
	
	@Then("^I verify tool tip of \"(.*)\" to be \"(.*)\"$")
	public void toolTip(String webElement, String text) throws IOException, ParseException {
		WebElement obj = Helper.getElement(webElement);
		obj = wait.until(ExpectedConditions.visibilityOf(obj));
		String toolTip =obj.getAttribute("title");
		if(toolTip.equals(text)) {
			System.out.println("correct tool tip:PASS");
		}
		else {
			System.out.println("actual tool is:"+toolTip);
			System.out.println("test failed");
		}
	}
	
	@Then("^I verify broken link of webpage$")
	public void brokenLink() {
		List <WebElement> links =driver.findElements(By.tagName("a"));
		HttpURLConnection huc = null;
		int respCode = 200;
		String url ="";
		Iterator<WebElement> it = links.iterator();
		while(it.hasNext()){
            
            url = it.next().getAttribute("href");
            
            System.out.println(url);
        
            if(url == null || url.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
           
            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println(url+" is a broken link");
                }
                else{
                    System.out.println(url+" is a valid link");
                }
                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
	
	@And("^I scroll to the \"(.*)\" webElement$")
	public void scrollToWebElement(String webElement) throws IOException, ParseException, InterruptedException {
		WebElement obj = Helper.getElement(webElement);
		obj = wait.until(ExpectedConditions.visibilityOf(obj));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", obj);
		Thread.sleep(3000);
	}
	
	@Then("^I take screenshot and save it to \"(.*)\"$")
	public void screenshot(String path) throws IOException {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(path);
		FileUtils.copyFile(SrcFile, DestFile);
	}
	
	@And("^I wait for page to load for \"(.*)\" second$")
	public void pageLoading(int moment) throws InterruptedException {
		int time =moment*1000;
		Thread.sleep(time);
	}
	
	@Then("^I click on \"(.*)\" link$")
	public void click_on_link(String image) throws IOException, ParseException {
		WebElement obj = Helper.getElement(image);
		obj = wait.until(ExpectedConditions.elementToBeClickable(obj));
		obj.click();
	}
	
}




