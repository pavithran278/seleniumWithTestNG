package yatraBooking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;


public class yatraBookingClass {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	public void launchBrowser() {
//      declaration and instantiation of objects/variables and driver
		System.setProperty("webdriver.chrome.driver","driver\\chromedriver.exe");
    	driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,30);
	}
	
	@AfterTest                           
    public void terminateBrowser(){
        driver.close();
    }
	
	@Test
	public void yatraBooking() throws InterruptedException {
    	
//         launch Chrome driver and direct it to the Base URL
        driver.get("https://www.yatra.com/");

//      Select the Flight Tab
        WebElement FlightTab = driver.findElement(By.id("booking_engine_flights"));
        FlightTab.click();
//        Select MAA as orgin City
        WebElement OrginCity =  driver.findElement(By.id("BE_flight_origin_city"));
        OrginCity.click();
        OrginCity.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + "MAA" + "';", "BE_flight_origin_city");
        driver.findElement(By.xpath("//span[text()='(MAA)']")).click();

//        select DEL as Destination CIty
        WebElement ArrivalCity =  driver.findElement(By.id("BE_flight_arrival_city"));
        ArrivalCity.click();
        ArrivalCity.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + "DEL" + "';", "BE_flight_arrival_city");
        driver.findElement(By.xpath("//span[text()='(DEL)']")).click();
        
//        Select upcoming Sunday as travel date
        WebElement OrginDate = driver.findElement(By.xpath("//input[@id='BE_flight_origin_date']"));
        OrginDate.click();    
        WebElement FirstSunday = driver.findElement(By.xpath("//td[contains(@title,'Sunday,')]"));
        FirstSunday.click();

//        Check in for Non stop flight
        WebElement NonStopFlightCheckBox = driver.findElement(By.xpath("//input[@id='BE_flight_non_stop']/ancestor::a"));    
        NonStopFlightCheckBox.click();
        
//        Select the flight passager count
        driver.findElement(By.id("BE_flight_paxInfoBox")).click();
        driver.findElement(By.xpath("//*[@id='BE_flight_passengerBox']/div[1]/div[1]/div/div/span[2]")).click();
        Thread.sleep(200);
        WebElement element = driver.findElement(By.xpath("//div[@id='flight_class_select_child']/ul/li[3]/span"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        
//        Search for the Flight
        driver.findElement(By.xpath("//input[@id='BE_flight_flsearch_btn']")).click();

//        wait until search result loads
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@yatratrackable=\"Flights|Search|Book Type|Book Now\"]")));

//        select on the 1st result
        WebElement BookNow = driver.findElement(By.xpath("//p[@yatratrackable=\"Flights|Search|Book Type|Book Now\"]"));
        actions.moveToElement(BookNow).click().build().perform();
        
       
    }

}
