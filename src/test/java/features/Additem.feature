
Feature: Adding an item in todo List

  Background :
  Given User lands on todo list page

  @Sanity
  Scenario Outline: Adding an item to todo list
    Given User is on todo list page
    When User enters <item> in todotestbox
    Then a new <item> gets added in the list
  Examples:
    | item                  |
    | Buy groceries         |

  @Sanity
  Scenario: Adding few items and marking an item as completed in todo list
    Given User is on todo list page
    When User adds few items
    | make dinner | buy frying pan | shopping | go to church |
    And Marks following items completed
    | make dinner | shopping |
    Then Following items should show up as completed
    | make dinner | shopping |

  @Sanity
  Scenario: Comparing the display of number of items left with pending items in todo list
    Given User is on todo list page
    When User adds few items
      | make dinner | buy frying pan | shopping | go to church |
    And User clicks on active tab
    Then the number of items matches the displayed number of items

  @Sanity
  Scenario Outline: Comparing the display of number of items left with pending items in todo list
    Given User is on todo list page
    When User adds few items
      | make dinner | buy frying pan | shopping | go to church |
    And User edits olditem with newitem
      | olditem             | newitem               |
      | make dinner         | make chicken dinner   |
    Then newitem appears in todolist
      | newitem               |
      | make chicken dinner   |

  @Sanity
  Scenario Outline: Deleting a single item from todo list
    Given User is on todo list page
    When User adds few items
      | make dinner | buy frying pan | shopping | go to church |
    And User clicks on delete button of <item>
    Then <item> gets deleted from todolist

    Examples:
      |  item             |
      | shopping          |

  @Regression
  Scenario Outline: Deleting multiple items from todo list
    Given User is on todo list page
    When User adds few items
      | make dinner | buy frying pan | shopping | go to church |
    And User clicks on the delete button of multiple items
      |  item             |
      | shopping          |
      | go to church      |
    Then Multiple items get deleted from the todolist
      |  item             |
      | shopping          |
      | go to church      |




