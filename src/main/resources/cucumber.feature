Feature: ProtonMailCucumberRunner

@all
Scenario Outline: Creating and Sending an Email Message
Given user navigates to ProtonMail home page
When  click Login button
And   creates new draft "<sender>" "<subject>" "<body>"
Then  searches sent "<sender>" "<subject>"


Examples:
|sender      |subject   |body          |
|tani455@mail.ru|Tatyana|Some text|