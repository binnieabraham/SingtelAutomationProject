package pageobjects;

import common.WebDriverComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static common.WebDriverVariable.driver;

public class Homepage {

    private WebDriverComponents webDriverComponents;

    public Homepage() {
        webDriverComponents = new WebDriverComponents();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".new-todo")
    WebElement todotextbox;

    @FindBy(css = "a[href='#/all']")
    WebElement allButton;

    @FindBy(css = "a[href='#/active']")
    WebElement activeButton;

    @FindBy(css = "a[href='#/completed']")
    WebElement completedButton;

    @FindBy(css = "button[class='clear-completed']")
    WebElement clearCompletedButton;

    @FindBy(css = ".todo-count")
    WebElement NoOfItems;
    @FindBy(css = "li[class=\"todo\"] div")
    List<WebElement> pendingTodoList;

    @FindBy(css = "li[class=\"todo completed\"] div")
    List<WebElement> completedTodoList;

    @FindBy(css = "ul[class=\"todo-list\"] li div")
    List<WebElement> entireTodoList;

    public void goTo(String url) {
        driver.get(url);
    }

    public int getNoOfItems() {
        String itemscount = NoOfItems.getText();
        int count = Integer.parseInt(itemscount.split(" ")[0].trim());
        return count;
    }

    public void addItem(String taskname) {
        todotextbox.sendKeys(taskname);
        todotextbox.sendKeys(Keys.ENTER);
    }

    public void markItemComplete(String item) {
        for (WebElement elem : getPendingTodoList()) {
            WebElement element = elem.findElement(By.cssSelector("input[type=\"checkbox\"]"));
            if (elem.getText().equalsIgnoreCase(item)) {
                element.click();
            }
        }
    }

    public void editItem(String olditem, String newitem) {
        Actions action = new Actions(driver);

        for (WebElement element : entireTodoList) {
            if (element.getText().equalsIgnoreCase(olditem)) {
                action.doubleClick(element).keyDown(Keys.COMMAND).sendKeys("A").keyUp(Keys.COMMAND).sendKeys(Keys.DELETE)
                        .sendKeys(newitem).sendKeys(Keys.ENTER).perform();
            }
        }
    }

    public void deleteItem(String deleteitemname) throws Exception {

        List<WebElement> elements = getEntireTodoList();

        for (int i = 1; i <= elements.size(); i++) {
            By genericTodoItem = By.xpath("(//ul[@class='todo-list']//li//label)[" + i + "]");
            String text = webDriverComponents.getText(genericTodoItem);

            if (text.equalsIgnoreCase(deleteitemname)) {
                By genericDeleteBtn = By.xpath("//div//label[contains(.,'" + deleteitemname + "')]//following-sibling::button");
                webDriverComponents.clickUsingJavaScript(genericDeleteBtn);
                break;
            }
        }
    }

    public boolean getPresenceOfItem(String itemname) {
        for (WebElement element : entireTodoList) {
            if (element.getText().equalsIgnoreCase(itemname)) {
                return true;
            }
        }
        return false;
    }

    public WebElement getActiveButton() {
        return activeButton;
    }

    public List<WebElement> getPendingTodoList() {
        return pendingTodoList;
    }

    public List<WebElement> getCompletedTodoList() {
        return completedTodoList;
    }

    public List<WebElement> getEntireTodoList() {
        return entireTodoList;
    }


}
