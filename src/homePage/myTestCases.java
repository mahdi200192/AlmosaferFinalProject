package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {
	WebDriver driver = new ChromeDriver();
	String almosaferURL = "https://www.almosafer.com/en";
	String ExpectedDefaultLang = "en";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

		driver.get(almosaferURL);

		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();

	}

	@Test(priority = 1)
	public void CheckTheDefaultLang() {
		String ActualDefaultLang = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualDefaultLang, ExpectedDefaultLang);
	}

	@Test(priority = 2)
	public void CheckDefaultCurrency() {
		String ExpectedDefaultCurrency = "SAR";

		WebElement Currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		String ActualDefaultCurrency = Currency.getText();
		Assert.assertEquals(ActualDefaultCurrency, ExpectedDefaultCurrency);
	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String ExpectedNumber = "+966554400000";
		WebElement ContactNumber = driver.findElement(By.tagName("strong"));
		String ActualContactNumber = ContactNumber.getText();
		Assert.assertEquals(ActualContactNumber, ExpectedNumber);
	}

	@Test(priority = 4)
	public void CheckTheQitafLogo() {
		boolean ExpectedResultForTheLogo = true;
//		WebElement TheFooter = driver.findElement(By.tagName("footer"));
		WebElement TheDiv = driver.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"));
		WebElement TheLogo = TheDiv.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		boolean ActualResultForTheLogo = TheLogo.isDisplayed();
		Assert.assertEquals(ActualResultForTheLogo, ExpectedResultForTheLogo);
	}

	@Test(priority = 5)
	public void TestHotels() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActuaValue = HotelTab.getAttribute("aria-selected");

		String ExpectedValue = "false";

		Assert.assertEquals(ActuaValue, ExpectedValue);

	}

	@Test(priority = 6)
	public void TestDepartureDate() {
		LocalDate TodayDate = LocalDate.now();

		int tommorow = TodayDate.plusDays(1).getDayOfMonth();
		int TheDayAfterTommorow = TodayDate.plusDays(2).getDayOfMonth();

		List<WebElement> AdaptureAndArrival = driver.findElements(By.className("LiroG"));
		String ActualAdapture = AdaptureAndArrival.get(0).getText();
		String ActualArrival = AdaptureAndArrival.get(1).getText();

		int ActualAdaptureAsInt = Integer.parseInt(ActualAdapture);
		int ActuaActualArrivalAsInt = Integer.parseInt(ActualArrival);

		Assert.assertEquals(ActualAdaptureAsInt, tommorow);
		Assert.assertEquals(ActuaActualArrivalAsInt, TheDayAfterTommorow);

	}

	@Test(priority = 7)
	public void CheckLanguage() {

		String[] URLS = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		int RandomURL = rand.nextInt(URLS.length);

		driver.get(URLS[RandomURL]);
	}

	@Test(priority = 8)

	public void FillHotelTab() throws InterruptedException {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		HotelTab.click();
		WebElement SearchHotelInputField = driver.findElement(By.xpath("//input[@data-testid='AutoCompleteInput']"));

		String WebsiteURL = driver.getCurrentUrl();

		String[] EnglishCities = { "Jeddah", "Riyadh", "Dubai" };
		int randomEnglishCity = rand.nextInt(EnglishCities.length);
		String[] ArabicCities = { "دبي", "جدة" };
		int randomArabicCities = rand.nextInt(ArabicCities.length);

		if (WebsiteURL.contains("ar")) {

			SearchHotelInputField.sendKeys(ArabicCities[randomArabicCities]);
		} else {
			SearchHotelInputField.sendKeys(EnglishCities[randomEnglishCity]);

		}
		
		Thread.sleep(2000);

		WebElement ListOfLocations = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

		WebElement firstResult = ListOfLocations.findElements(By.tagName("li")).get(1);
		firstResult.click();

	}
	
	@Test(priority = 9 )

	public void RandomlySelectTheNumberOfVistor() {

		WebElement SelectorofTheVistor = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		Select select = new Select(SelectorofTheVistor);

		// By index

		int randomIndex = rand.nextInt(2);
		select.selectByIndex(randomIndex);

		// By value
//		select.selectByValue("B"); 

		// by visibleText
//		if(driver.getCurrentUrl().contains("ar")) {
//			select.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال"); 
//
//		}else {
//			select.selectByValue("1 Room, 1 Adult, 0 Children"); 
//		}
//		

		WebElement SearchHotelButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchHotelButton.click();
	}
	
	@Test(priority = 10)
	public void CheckThePageFullyLoaded() throws InterruptedException {
		Thread.sleep(20000);
		boolean ExpectedResults = true;
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
		
		boolean finished = results.contains("وجدنا") || results.contains("found");
		
		Assert.assertEquals(finished, ExpectedResults);
	}
	
	@Test(priority = 11, description = "wallah jayen nshof eza asghar s3er aqal ")

	public void SortItemsLowestToHighestPrice() {

		boolean expectedResults = true;
		WebElement LowestPriceSortButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));

		LowestPriceSortButton.click();

		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> AllPrices = PricesContainer.findElements(By.className("Price__Value"));

//		List<WebElement> thePrices = driver.findElements(By.cssSelector(".Price__Wrapper.PriceDisplay__FinalRate.sc-dRCTWM.GFIG"));
//		
//		
		String LowestPrice = AllPrices.get(0).getText();
		String HighestPrice = AllPrices.get(AllPrices.size() - 1).getText();

		System.out.println(LowestPrice);
		System.out.println(HighestPrice);

//		

		int LowestPriceAsInt = Integer.parseInt(LowestPrice);
		int HighestPriceAsInt = Integer.parseInt(HighestPrice);

		boolean ActualResults = LowestPriceAsInt < HighestPriceAsInt;

		Assert.assertEquals(ActualResults, expectedResults);

	}
	
}
