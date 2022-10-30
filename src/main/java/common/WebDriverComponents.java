package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverComponents {

    public void clickWebElement(By webElement) {
        WebDriverVariable.driver.findElement(webElement).click();
    }

    public void clickUsingJavaScript(By webElement) throws Exception {
        WebElement element = WebDriverVariable.driver.findElement(webElement);
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverVariable.driver;
        executor.executeScript("arguments[0].click();", element);

    }

    public WebElement getWebElement(By webElement) {
        WebElement element = WebDriverVariable.driver.findElement(webElement);
        return element;
    }

    public String getText(By webElement) {
        String text = WebDriverVariable.driver.findElement(webElement).getText();
        return text;
    }

    public int getElementCount(By webElement) {
        int size = WebDriverVariable.driver.findElements(webElement).size();
        return size;
    }


    public boolean checkObjectExist(By webElement) {
        boolean X = false;
        try {
            X = WebDriverVariable.driver.findElement(webElement).isDisplayed();
        } catch (Exception e) {
        }
        return X;
    }


    public void mouseHover(By webElement) throws InterruptedException {
        Actions actions = new Actions(WebDriverVariable.driver);
        WebElement hoverElement = WebDriverVariable.driver.findElement(webElement);
        actions.moveToElement(hoverElement).build().perform();
        Thread.sleep(20000);
    }


}
