Feature: End to End Test for Products

Scenario Outline: As a user I can add new data //Scenario Outline is used to run the same scenario with different data sets
    Given A list of item are available
    When I add item to list
    Then The item is available

    Examples:
    |payload  |
    |addItem  |
    |addItem2 |
