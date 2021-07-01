import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 */
public class PCAData {
	public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/siddharthaadelkar/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "http://censusindia.gov.in/pca/cdb_pca_census/cd_block.html";
     

        ArrayList<String> urls = getUrls(driver,url);
        for(String s:urls){
        	List<String> list= getUrls(driver, s);
        	for(String link: list){
        		System.out.println("wget \""+link+"\"");
        	}
        }
        driver.close();
    }

	public static ArrayList<String> getUrls(WebDriver driver, String s) {
		driver.get(s);
		ArrayList<String> urls = new ArrayList();
		List<WebElement> gridviwalternateitem = driver.findElements(By.className("gridviwalternateitem"));
		for (WebElement tr : gridviwalternateitem) {
			List<WebElement> links = tr.findElements(By.tagName("a"));
			for (WebElement link : links) {
				String url = link.getAttribute("href").trim();
				urls.add(url);
			}
		}
		return urls;
	}

}
