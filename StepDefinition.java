package stepdefination;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	   

/*Given Launch DBS Application
And Click Learnmore button 
And Click Sustainability
And Click creating Social Impact
And Click DBS Stronger Together Fund 
When Navigate to Singapore on Left panel
And Read and Write the Table in excel Sheet
And Navigate to About in header Tab 
And Navigate to Who we are Tab 
And Navigate to Our Awards and Accoldes 
Then Validate the Total number of awards 
Then Validate the Awards and Caption of the Awards name
 */
	
	public RemoteWebDriver driver;
	
	@Given("Launch DBS Application")
     public void launchDBSapp()
	{	
		System.setProperty("webdriver.chrome.driver", "./chromedriver84.exe");
           driver = new ChromeDriver();
           driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		  driver.get("https://www.dbs.com.sg/index/default.page");
          driver.manage().window().maximize();
          
	}
	
	@And ("^Click Learnmore button$")
	public void clickLearnmore() throws Throwable
	{
		driver.findElementByXPath("//a/button[text()='Learn More'][1]").click();
		
	}
	
	@And ("^Click Sustainability$")
	public void clickSustainability()
	{
		
				
		WebElement button = driver.findElementByXPath("//header[@class='navbar mega-menu']//a[text()='Sustainability']");	
		
        button.click();
		
	}
	@And ("^Click creating Social Impact$") 
	public void clickSocialImpact()
	{
		
		driver.findElementByXPath("//div//h4[text()='Creating Social Impact']").click();
	}
	@Given ("Click DBS Stronger Together Fund")
	public void clickDBSstronger()
	
	{
		
		driver.findElementByXPath("//h3//a[text()='DBS Stronger Together Fund']").click();
	}
	
	@When("Navigate to Singapore on Left panel")
	public void singaporeLeftpanel()
			{
			driver.findElementByXPath("//div[@class='list-box desktop-nav-bar']/ul//li/a[contains(text(),'Singapore')]").click();
	}
	
	@Then("Read and Write the Table in excel Sheet")
	public void excel() throws IOException
	
	{
	XSSFWorkbook wb = new XSSFWorkbook();
	XSSFSheet sh = wb.createSheet("fundername");
	int rowcount = 0;
	List<WebElement> funder = driver.findElementsByXPath("//div[@class='col-md-8 col-sm-8 left-content']//following::table//td//p");
    for( int i =0 ; i < 28 ; i++)
		 {
			 String name = funder.get(i).getText();
			 
			 XSSFRow row = sh.createRow(++rowcount);
			 XSSFCell cell = row.createCell(0);
			 cell.setCellValue(name);
			  
 }
    
    FileOutputStream fis = new FileOutputStream("./fundername.xlsx");
    wb.write(fis);

	}

	@When("Navigate to About in header Tab")
	public void Aboutheader()
	{
  driver.findElementByXPath("//div[@id='mobileSlideMenu']//following-sibling::div//header[@class='navbar mega-menu mini-menu']//a[text()='About']").click();
	}
	@When("Navigate to Who we are Tab")
	public void singapore()
	{
		driver.findElementByXPath("//div[@class='navbar-overflow-content']//div//a[text()='Who We Are']").click();
	}
	@When("Navigate to Our Awards and Accoldes")
	public void awardsandaccoldes()
	{
		driver.findElementByXPath("//div[@class='container mTop-0']//a[text()='Our Awards & Accolades']").click();

	}
	@Then("Validate the Total number of awards")
	public void totalawards()
	{
		List <WebElement> noofawards = driver.findElementsByXPath("//table[@class ='tbl-primary']//tbody//tr//td//p//strong");
            if (noofawards.size() == 40 )
            {
            	
            	System.out.print("The Total number of awards is validated Successfully");
            }
	}
	
	@Then("Validate the Awards and Caption of the Awards name")
	public void awardandcaptionname(DataTable data)
		{
		
		for (int k = 0 ; k < 2 ; k++)
		{
	     List<List<String>> obj = data.asLists();
		 String Award = obj.get(k).get(0);
		List <WebElement> noofawardss = driver.findElementsByXPath("//table[@class ='tbl-primary']//tbody//tr//td//p//strong");
         for(int j=0;j < noofawardss.size();j++)
         {
        	 if (noofawardss.get(j).getText().contains(Award))
        			 {
        		 System.out.println("The Award name" + Award +"is Available");
        			 }
         }
	}
		}
	
	

	
	
}
	
	

