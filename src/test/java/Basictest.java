import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pageobjects.Homepage;
import static common.WebDriverVariable.driver;
import common.WebDriverComponents;
import java.util.Arrays;
import java.util.List;

public class Basictest  {

    public static void main( String args[])
    {
        WebDriverComponents webDriverComponents = new WebDriverComponents();
        Homepage homepage;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://todomvc.com/examples/vue");
        homepage = new Homepage();
        List<String> str = Arrays.asList("Buy groceries", "make chicken", "buy frying pan","shopping");
        for(String s:str){
            homepage.addItem(s);
        }
        List<String> listOfTasks = Arrays.asList( "make chicken","shopping");

        String text = "shopping";
        List<WebElement> list =  homepage.getPendingTodoList();
        for(WebElement elemenet : list){
            if(elemenet.getText().equalsIgnoreCase(text)){
                By checkBtn = By.xpath("//div//label[contains(.,'" + text + "')]//preceding-sibling::input");

                webDriverComponents.clickWebElement(checkBtn);
            }
        }

        //Actions action = new Actions(driver);

        /*WebElement e =driver.findElement(By.xpath("//ul/li/div/label[text()=\"shopping\"]"));
        action.doubleClick(e).keyDown(Keys.COMMAND).sendKeys("A").keyUp(Keys.COMMAND)
                //.sendKeys(Keys.chord(Keys.SHIFT,"a"))
                .sendKeys(Keys.DELETE)
                .sendKeys(" for binnie").sendKeys(Keys.ENTER).perform();
        //e =driver.findElement(By.xpath("//ul/li/div/label[text()=\"shopping\"]"));
        //action.sendKeys(e,"binnie");
        //e.sendKeys("binnie");*/


        //List<WebElement> elements =driver.findElements(By.cssSelector("ul[cla
        /*List<WebElement> elements =driver.findElements(By.xpath("//ul[@class='todo-list']//li//label"));
        for (int i=1; i<=elements.size();i++){
            String text = driver.findElement(By.xpath("(//ul[@class='todo-list']//li//label)["+i+"]")).getText();

            if(text.equalsIgnoreCase("shopping")){

                JavascriptExecutor executor = (JavascriptExecutor) driver;
                //action.moveToElement(driver.findElement(By.xpath("//div//label[contains(.," + text + ")]")));
                WebElement e = driver.findElement(By.xpath("//div//label[contains(.,'"+text+"')]//following-sibling::button"));
                executor.executeScript("arguments[0].click();", e);
            }
        }*/




        /*for(WebElement elem : elements)
        {
            String text = "shopping";
            if(elem.getText().equalsIgnoreCase(text)) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                //action.moveToElement(driver.findElement(By.xpath("//div//label[contains(.," + text + ")]")));
                WebElement e = driver.findElement(By.xpath("//div//label[contains(.," + text + ")]//following-sibling::button"));
                executor.executeScript("arguments[0].click();", e);
                //e.click();
            }*/



            //mark an item complete



        /*List<WebElement> entirelist = driver.findElements(By.cssSelector("ul li div"));
        for(WebElement element : entirelist){
            System.out.println(element.getText());
            if(element.getText().equalsIgnoreCase("shopping")){
                System.out.println("binnie");
                action.doubleClick(element).perform()
                        sendKeys("Binnie");
                //WebElement e = driver.findElement(By.cssSelector("li[class=\"todo editing\"]"));
                element.sendKeys("Playing");
            }
        }*/


    }



}
