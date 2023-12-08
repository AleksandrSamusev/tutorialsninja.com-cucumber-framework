Feature: Login Data Driven with Excel

  Scenario Outline: Login Data Driven with Excel
    Given user launch browser
    And opens URL "https://tutorialsninja.com/demo/index.php?route=common/home"
    When user navigate to myAccount menu
    And click on Login
    Then check user navigates to myAccount Page by passing email and password with excel row "<row_index>"

    Examples:
      | row_index |
      | 1         |
      | 2         |
      | 3         |
      | 4         |
      | 5         |