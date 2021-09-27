package _PepperFry.PepperFry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortElements {
	WebDriver driver;
	List<String> titleValue=new ArrayList<String>();
	
	
	@BeforeClass
	@Parameters({"Pepperfry"})
	public void init(String weblink) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
 driver=new ChromeDriver(capabilities); // is there any other updated way of doing it?
		
		driver.manage().window().maximize();
		driver.get(weblink);
	}
	
	@Test
		public void checkTitle() {
		Assert.assertTrue(driver.getTitle().contains("Pepperfry"));
		System.out.println("title is validated");
		
	}
	
	@Parameters({"word"})
	@Test
	public void checkelemetsdisplayed(String input) {
		driver.findElement(By.id("search")).sendKeys(input +"\n");
		Assert.assertTrue(driver.findElement(By.id("search")).getAttribute("value").contains(input));
		popClose(driver);
	}
		
		@Test
		public void getelements() {
			List<WebElement> products=driver.findElements(By.xpath("//div[@class='clip-crd-10x11 pf-white srch-rslt-bxwrpr']"));
			System.out.println(products.size());
			Assert.assertTrue(products.size()==47);
			
			List <WebElement>titles=driver.findElements(By.xpath("//a[@class='clip-prd-dtl']"));
			
			for(int i=0;i<titles.size();i++) {
				titleValue.add(titles.get(i).getText());
				System.out.println(i+1 + " : " + titles.get(i).getText());
			}
			popClose(driver);
			
		}
		
		public void popClose(WebDriver driver) {
			List<WebElement> popup=driver.findElements(By.id("regPopUp"));
			if(popup.size()>0) {
				driver.findElement(By.xpath("//a[@class='popup-close']")).click();
			}
			}
		
				
			@Test
			public void sortInAscendingOrder() {
				Collections.sort(titleValue);
				System.out.println("***********Ascending order*******");
				for(String each:titleValue) {
					System.out.println(each);
				Reporter.log((each));
				}
			}
				
				@Test
				public void sortInDescendingOrder() {
					Collections.sort(titleValue,Collections.reverseOrder());
					System.out.println("***********Descending order*******");
					for(String each:titleValue) {
						System.out.println(each);
						Reporter.log(each);
					}
		
	}

}
