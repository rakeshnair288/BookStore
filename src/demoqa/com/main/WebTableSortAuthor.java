package demoqa.com.main;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class WebTableSortAuthor {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Driver setup
		ChromeOptions ops = new ChromeOptions();

		ops.addArguments("--remote-allow-origins=*");
		ChromeDriver driver= new ChromeDriver(ops) ;
		
		//Invoking Browser 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.get("https://demoqa.com/login");
		
		driver.findElement(By.xpath("//input[@id='userName']")).sendKeys("sas");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Rideca@1");
		
		//run javascript code
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,400)");
		
		driver.findElement(By.xpath("//button[@id='login']")).click();
		Thread.sleep(3000);
		JavascriptExecutor jse1 = (JavascriptExecutor)driver;
		jse1.executeScript("window.scrollBy(0,600)");
		
		driver.findElement(By.xpath("//button[@id='gotoStore']")).click();
		
		//click on Title
		driver.findElement(By.xpath("//div[contains(text(),'Author')]")).click();
		
		//capture all the webelements of titles into a list
		List<WebElement> authorlist = driver.findElements(By.cssSelector("div[role='rowgroup'] div:nth-child(3)"));
		
		//capture text of webelements of titles into a new list
		
		List<String> authortextlist = authorlist.stream().map(x ->x.getText()).collect(Collectors.toList());
		
		//Apply sorting of this list and store in new list
		
		List<String> sortedauthorlist =  authortextlist.stream().sorted().collect(Collectors.toList()); 
		System.out.println(authortextlist);
		System.out.println(sortedauthorlist);
		Assert.assertTrue(authortextlist.equals(sortedauthorlist));
		
	}

}
