package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber-Testng,junit
@CucumberOptions(features="src/test/java/cucumber",glue="rahulacademy.stepDefinition",
monochrome=true,tags="@Regression",plugin= {"html:target/cucumber.html"})
public class TestngTestRunner extends AbstractTestNGCucumberTests
{
	

}
