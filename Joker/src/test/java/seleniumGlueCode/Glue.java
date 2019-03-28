package seleniumGlueCode;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "src/test/java/features"
,glue= {"seleniumGlueCode"},
plugin = {"pretty", "html:target/cucumber-report/cucumber.html"},
monochrome = true


)
public class Glue {


}
