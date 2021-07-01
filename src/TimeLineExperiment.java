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

public class TimeLineExperiment {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		System.setProperty("webdriver.chrome.driver", "/Users/siddharthaadelkar/Downloads/chromedriver");
	    WebDriver driver = new ChromeDriver();
	        
		FileReader fr = new FileReader("links");
		BufferedReader br = new BufferedReader(fr);
		String s = null;
		while((s=br.readLine())!=null){
			driver.get(s);
			System.out.print(s+"\t");
			List<WebElement> elements = driver.findElements(By.tagName("meta"));
			for(WebElement e:elements){
				String property = e.getAttribute("property");
				if(property!=null&&property.equals("og:image")){
					System.out.println(e.getAttribute("content"));
				}
			}
		}
		
		
		driver.close();
		
		  
	}

}
