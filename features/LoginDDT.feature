Feature: Login Data Driven

  Scenario Outline: Login Data Driven
    Given user launch browser
    And opens URL "https://tutorialsninja.com/demo/index.php?route=common/home"
    When user navigate to myAccount menu
    And click on Login
    And user enters email as "<email>" and password as "<password>"
    And click on Login button
    Then user navigates to myAccount page

    Examples:
      | email             | password  |
      | admin@admin.admin | admin     |
      | any@gmail.com     | anypass   |
      | 123@jjj.ro        | qwe       |
      | 456@qwq.qw        | qweqweqwe |
      | aaa@qwe.xx        | asdasd    |