package homePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {
	WebDriver driver = new ChromeDriver();
	String almosaferURL = "https://www.almosafer.com/en";
	String ExpectedDefaultLang = "en";

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		
		driver.get(almosaferURL);
		
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
		
	}
	
	@Test
	public void CheckTheDefaultLang() {
		String ActualDefaultLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualDefaultLang, ExpectedDefaultLang);
	}
	
	@Test
	public void CheckDefaultCurrency() {
		String ExpectedDefaultCurrency = "SAR";

		WebElement Currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		String ActualDefaultCurrency = Currency.getText();
		Assert.assertEquals(ActualDefaultCurrency, ExpectedDefaultCurrency);
	}
	
	@Test
	public void CheckContactNumber() {
		String ExpectedNumber = "+966554400000";
		WebElement ContactNumber = driver.findElement(By.tagName("strong"));
		String ActualContactNumber = ContactNumber.getText();
		Assert.assertEquals(ActualContactNumber, ExpectedNumber);
	}
	@Test
	public void CheckTheQitafLogo() {
		boolean ExpectedResultForTheLogo = true;
//		WebElement TheFooter = driver.findElement(By.tagName("footer"));
		WebElement TheDiv = driver.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"));
		WebElement TheLogo = TheDiv.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		boolean ActualResultForTheLogo = TheLogo.isDisplayed();
		Assert.assertEquals(ActualResultForTheLogo, ExpectedResultForTheLogo);
	}
	
}
