package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.Homepage;

import java.util.List;
import java.util.Map;

public class StepDefinition {

    public Homepage homepage;
    public int countOfItems;

    @Given("User lands on todo list page")
    public void User_lands_on_todo_list_page() throws Exception {

        System.out.println("This Given under Background is not working ");
    }

    @Given("User is on todo list page")
    public void User_is_on_todo_list_page() throws Exception {

        homepage = new Homepage();
        homepage.goTo("http://todomvc.com/examples/vue");

    }

    @When("^User enters (.+) in todotestbox$")
    public void User_enters_item_in_todotestbox(String item) {
        System.out.println("When is getting executed");
        homepage.addItem(item);
    }

    @Then("^a new (.+) gets added in the list$")
    public void a_new_item_gets_added_in_the_list(String item) throws Exception {
        System.out.println("Executing Then a new item gets added in the list");
        List<WebElement> listOfElements = homepage.getPendingTodoList();
        String text = "";
        for (WebElement element : listOfElements) {
            if (element.getText().equalsIgnoreCase(item)) {
                text = element.getText();
            }
        }
        Assert.assertEquals(text, item);
    }

    @When("User adds few items")
    public void user_adds_few_items(DataTable dataTable) throws Throwable {

        List<List<String>> itemlist = dataTable.asLists();
        for (String item : itemlist.get(0)) {
            homepage.addItem(item);
        }
    }

    @When("Marks following items completed")
    public void marks_following_items_completed(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> itemlist = dataTable.asLists();
        for (String item : itemlist.get(0)) {
            homepage.markItemComplete(item);
        }
    }

    @Then("Following items should show up as completed")
    public void following_items_should_show_up_as_completed(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> itemlist = dataTable.asLists();
        List<WebElement> listOfElements = homepage.getCompletedTodoList();
        for (WebElement elem : listOfElements) {
            Assert.assertTrue(itemlist.get(0).contains(elem.getText()));
        }
    }

    @When("User clicks on active tab")
    public void user_clicks_on_active_tab() {
        WebElement actvBtn = homepage.getActiveButton();
        actvBtn.click();

    }

    @Then("the number of items matches the displayed number of items")
    public void the_number_of_items_matches_the_displayed_number_of_items() {

        List<WebElement> elems = homepage.getPendingTodoList();
        int NoOfElements = elems.size();
        int noOfItems = homepage.getNoOfItems();
        Assert.assertEquals(NoOfElements, noOfItems);
    }


    @When("^User edits olditem with newitem$")
    public void user_edits_olditem_with_newitem(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> responseKeyValue : rows) {
            homepage.editItem(responseKeyValue.get("olditem"), responseKeyValue.get("newitem"));
        }


    }

    @Then("^newitem appears in todolist$")
    public void newitem_appears_in_todolist(DataTable table) {

        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> responseKey : rows) {
            homepage.getPresenceOfItem(responseKey.get("olditem"));
        }

    }

    @When("^User clicks on delete button of (.+)$")
    public void user_clicks_on_delete_button_of_deleteitem(String deleteitemname) throws Exception {
        homepage.deleteItem(deleteitemname);
    }

    @Then("^(.+) gets deleted from todolist$")
    public void deleteditem_gets_deleted_from_todolist(String deleteitem) {
        Assert.assertFalse(homepage.getPresenceOfItem(deleteitem));
    }

    @When("^User clicks on the delete button of multiple items$")
    public void user_clicks_on_delete_button_of_items(DataTable table) throws Exception {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> deleteItemName : rows) {
            homepage.deleteItem(deleteItemName.get("item"));
        }
    }

    @Then("^Multiple items get deleted from the todolist$")
    public void items_gets_deleted_from_todolist(DataTable table) throws Exception{
            List<Map<String, String>> rows = table.asMaps(String.class, String.class);
            for (Map<String, String> deleteItemName : rows) {
                Assert.assertFalse(homepage.getPresenceOfItem(deleteItemName.get("item")));
        }

    }
}
