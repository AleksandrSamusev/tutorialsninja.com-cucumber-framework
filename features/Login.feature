Feature: Login with Valid Credentials

  @sanity @regression
  Scenario: Successful Login with Valid Credentials
    Given user launch browser
    And opens URL "https://tutorialsninja.com/demo/index.php?route=common/home"
    When user navigate to myAccount menu
    And click on Login
    And user enters email as "admin@admin.admin" and password as "admin"
    And click on Login button
    Then user navigates to myAccount page
