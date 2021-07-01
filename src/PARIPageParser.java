import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class PARIPageParser {
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		System.setProperty("webdriver.chrome.driver", "/Users/siddharthaadelkar/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		String s = "https://ruralindiaonline.org/archive/2014/12/";
		driver.get(s);
		System.out.print(s + "\t");
		WebElement maincontent = driver.findElement(By.id("main_content"));
		WebElement section = maincontent.findElement(By.tagName("section"));
		List<WebElement> elements = section.findElements(By.tagName("li"));
		for (WebElement e : elements) {
			List<WebElement> divs = e.findElements(By.tagName("div"));
			for(WebElement div:divs){
				List<WebElement> as = div.findElements(By.tagName("a"));
				for(WebElement a: as){
					System.out.println(a.getAttribute("href"));
				}
			}
		}

		driver.close();

	}

}
