package selenium.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalanderDateSelection {

	
	public static void main(String[] args) throws Exception{
	
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Shivpratap\\Downloads\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://in.via.com");
		Thread.sleep(10 * 1000);
		String dateString = "25 Mar 2018";
		driver.findElement(By.id("departure")).click();
		List<WebElement> months ;
		WebElement forwardArrow = driver.findElement(By.xpath(".//span[contains(@class,'js-next-month')]"));
		boolean status = true;
		while(status){
			Thread.sleep(5 * 1000);
			months = driver.findElements(By.className("vc-month-box-container"));
			for (WebElement month : months) {
				WebElement headerElement = month.findElement(By.xpath(".//span[@class='vc-month-box-head-cell ']"));
				System.out.println(headerElement.getText());
				if (headerElement.getText().contains("Feb 2018")) {
					/*List<WebElement> dates = month.findElements(By.xpath(".//div[@class='vc-month-box']//div[@class='vc-cell']"));
					for (WebElement date : dates) {
						if (date.getAttribute("data-date").contains("2")) {
							date.click();
						}
					}*/
					WebElement date = month.findElement(By.xpath("//div[@data-date='2']"));
					date.click();
					status = false;
					break;
				}
			}
			if (status) {
				forwardArrow.click();
			}
		}
	}

}
