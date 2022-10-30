package stepDefinitions;

import static common.WebDriverVariable.driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Hooks {
    @Before()
    public void preSetup() throws IOException {
        initializeDriver();
    }
    @After()
    public void tearDown() {
        driver.close();
    }

    private void initializeDriver() throws IOException {
        Properties prop = new Properties();
        prop.load(Hooks.class.getClassLoader().getResourceAsStream("GlobalData.properties"));
        String browserName = (String) prop.get("browser");

        if(browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else if(browserName.equalsIgnoreCase("safari"))
        {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

}
