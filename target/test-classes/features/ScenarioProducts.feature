Feature: End to End Test for Products

Scenario: As a user I can add new data
    Given A list of item are available
    When I add item to list
    Then The item is available

Scenario Outline: As a user I can add new data with different payload
    Given A list of item are available
    When I add item to list "<payload>"
    Then The item is available "<payload>"

    Examples:
    |payload   |
    |addItem   |
    |addItem2  |
