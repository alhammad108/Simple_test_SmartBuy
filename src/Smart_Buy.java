import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Smart_Buy {
	public WebDriver driver;
public int numOfitem=3;
SoftAssert softassert=new SoftAssert();
	@BeforeTest()
	public void Before_of_The_test() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.get("https://smartbuy-me.com/smartbuystore/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		js.executeScript("window.scrollBy(0,600)");

	}

	@Test()
	public void Test_of_The_item_SAMSUNG_50_inch() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		for(int i=0;i<numOfitem;i++) {

			driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[3]/div[1]/div/div/form[1]/div[1]/button")).click();
			driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();
		}

	}

	@Test()
	
	public void Check_of_The_price() {
String The_price_Of_single_Item=	driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]")).getText();

String [] Update_of_Price=The_price_Of_single_Item.split("JOD");
String The_final_Of_the_Price = Update_of_Price[0].trim();
String Updated=The_final_Of_the_Price.replace(",", ".");

Double The_price=Double.parseDouble(Updated);

	
	System.out.println(The_price*numOfitem);

	}
	
}