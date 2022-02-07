package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LogOutPage {
	WebDriver driver;
	public LogOutPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath="//a[@id='welcome']")
	WebElement clickelement;
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement clicklogout;
	public void performlogout()throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(clickelement).click().perform();
		Thread.sleep(3000);
		ac.moveToElement(clicklogout).click().perform();
				
	}	

}
