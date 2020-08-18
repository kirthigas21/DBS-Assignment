package Runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features={"src/main/resources/feature/dbslaunch.feature"},
		glue = {"stepdefination"},
		plugin={"html:target/cucumber"})

public class execution extends AbstractTestNGCucumberTests{
	
	
	
	

}
