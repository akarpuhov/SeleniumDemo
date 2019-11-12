Feature: MailRu send email

  Scenario: Create draft email and send it
    Given I open mail.ru site
    When I login in my account with 'at.test@inbox.ru' and 'saratov01'
    Then Open home page