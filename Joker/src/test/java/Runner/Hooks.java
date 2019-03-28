package Runner;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {
	public static WebDriver driver;
	 public void openBrowser() throws MalformedURLException {
	    	System.out.println("Called openBrowser");
	    	driver = new ChromeDriver();
	    	driver.manage().deleteAllCookies();
	    	driver.manage().window().maximize();
	    }
}
