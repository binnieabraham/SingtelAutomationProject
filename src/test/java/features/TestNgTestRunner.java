package features;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepDefinitions"}, tags = "@Sanity")
       //plugin = "json:target/cucumber-reports/CucumberTestReport.json")
public class TestNgTestRunner extends AbstractTestNGCucumberTests{

}
