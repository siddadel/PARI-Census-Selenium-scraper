import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/siddharthaadelkar/Downloads/chromedriver");
        System.out.println("Hello World!");
        String baseUrl = "http://censusindia.gov.in/pca/";
        WebDriver driver = new ChromeDriver();

        //select the index
        for (int i = 2; i < 3; i++) {
            Select statesSelect = getStatesHandle(baseUrl, driver);
            statesSelect.selectByIndex(i);

            //select the index
            for (int j = 2; j < 3; j++) {
                Select districtSelect = getDistrictHandle(driver);
                districtSelect.selectByIndex(j);
                WebElement submitButton = driver.findElement(By.id("SubmitButton1"));
                submitButton.click();
                parseTable(driver.getPageSource());
            }

        }
        driver.close();
    }
    
    private static void parseTable(String source){
    	Document doc = Jsoup.parse(source);
    	System.out.println(source);
		Element table = doc.getElementById("GridView1");
		Element header = table.getElementsByClass("GridHeader").get(0);
		Elements ths = header.getElementsByTag("th");
		Elements tds = header.getElementsByTag("td");
		for(int i=0;i<ths.size();i++){
			
		}
		
    }
    
    private static Select getDistrictHandle(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        By districtLocator = By.id("DistDropDownList1");
        By districtOptons = By.xpath("//select[@id='DistDropDownList1']/option");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(districtOptons, 1));
        WebElement districtDropDown = driver.findElement(districtLocator);

        return new Select(districtDropDown);
    }

    private static Select getStatesHandle(String baseUrl, WebDriver driver) {
        driver.get(baseUrl);
        WebElement stateDropDown = driver.findElement(By.id("StateDropDownList1"));
        Select select = new Select(stateDropDown);
        int stateSize = select.getOptions().size();
        return select;
    }
}
