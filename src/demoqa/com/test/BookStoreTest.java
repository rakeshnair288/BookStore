package demoqa.com.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import TestComponents.ReUsableMethods;

public class BookStoreTest extends ReUsableMethods {

	@Test

	public void brokenLinkVerification() throws IOException

	{

		// asserting if any links below title is broken
		List<WebElement> links = driver.findElements(By.cssSelector("a[href*='/books']"));
		System.out.println("Total links equals" + " " + links.size());

		SoftAssert a = new SoftAssert();

		for (WebElement e : links) {
			String url = e.getAttribute("href");
			HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();
			httpUrlConnection.connect();
			int responseCode = httpUrlConnection.getResponseCode();
			System.out.println(responseCode);

			a.assertTrue(responseCode < 400, "The link with text" + e.getText() + "is broken with code" + responseCode);

		}

		a.assertAll();

	}

	@Test

	// Just change index of cssselector to 2,3 and 4 and replace Title(2), Author(3)
	// and Publisher(4) on click
	// on click on title step to assert sorting of Title, Author and Publisher

	public void webtableSortTitleVerification() {

		// click on Title
		driver.findElement(By.xpath("//div[contains(text(),'Title')]")).click();

		// capture all the webelements of titles into a list
		List<WebElement> bookTitleList = driver.findElements(By.cssSelector("div[role='rowgroup'] div:nth-child(2)"));

		// capture text of webelements of titles into a new list

		List<String> bookTitleTextList = bookTitleList.stream().map(x -> x.getText()).collect(Collectors.toList());

		// Collect all non empty strings of booktextlist into a new list
		List<String> nonEmptybookTitleTextList = new ArrayList<>();

		for (String str : bookTitleTextList) {
			String st = str.trim();
			if (!st.isEmpty()) {
				nonEmptybookTitleTextList.add(str);
			}
		}
		System.out.println(nonEmptybookTitleTextList);

		// Apply sorting of this bookTitleTextList and store in new list

		List<String> sortedBookTextlist = bookTitleTextList.stream().sorted().collect(Collectors.toList());

		// Collect all non empty strings of sortedBookTextlist into a new list
		List<String> nonEmptysortedbooklist = new ArrayList<>();

		for (String str : sortedBookTextlist) {
			String st = str.trim();
			if (!st.isEmpty()) {
				nonEmptysortedbooklist.add(str);
			}
		}

		System.out.println(nonEmptysortedbooklist);
		Assert.assertTrue(nonEmptysortedbooklist.equals(nonEmptybookTitleTextList));
		;

	}

}
