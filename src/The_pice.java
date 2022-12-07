import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class The_pice {
public WebDriver driver;
public int Num_of_Item=10;
public int Chick_of_Item=4;
SoftAssert softassertprocess=new SoftAssert();
@BeforeTest()

public void Log_in() {
	WebDriverManager.chromedriver().setup();
	
	driver=new ChromeDriver();
	
	
	driver.get("https://smartbuy-me.com/smartbuystore/");
	driver.manage().window().maximize();
	driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();
	JavascriptExecutor js = (JavascriptExecutor) driver;	
	js.executeScript("window.scrollBy(0,600)");

}
@Test()
	public void The_price_Of_cart() {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
for(int i=0;i<Num_of_Item;i++) {



	driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[3]/div[1]/div/div/form[1]/div[1]/button")).click();
	
String msg=driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/div[1]")).getText();
	
	if(msg.contains("Sorry")) {
		Num_of_Item=i;
		 driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[1]")).click();
		 
	}
	else {
		driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();

	}




}

	}

@Test()
public void Total_of_Price() {
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.navigate().back();
String Chick_of_Price=driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]")).getText();
String [] Update_of_The_price=Chick_of_Price.split("JOD");
String The_price=Update_of_The_price[0].replace(",", ".");
 double val = Double.parseDouble(The_price);
 
 String Before_discount=driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[2]")).getText();
String [] Update_before_discount=Before_discount.split("JOD");
String Reset_Price=Update_before_discount[0].replace(",",".");

double Theprice_before_Discount = Double.parseDouble(Reset_Price);
System.out.println(Theprice_before_Discount);
 String Discount = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[1]")).getText();
String Update_discount=Discount.replace("%", "");
double value=Double.parseDouble(Update_discount);
double Chick_discount=(value/100*Theprice_before_Discount);
double Chick_after_Discount=Theprice_before_Discount-Chick_discount;

softassertprocess.assertEquals(Chick_after_Discount,val);
softassertprocess.assertAll();
}}






