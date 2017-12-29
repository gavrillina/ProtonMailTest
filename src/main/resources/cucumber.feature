Feature: ProtonMailCucumberRunner

@all
Scenario Outline: Creating and Sending an Email Message
Given user navigates to ProtonMail home page
When  click Login button
And  user creates new draft "<sender>" and "<subject>" and "<body>"
Then  user searches sent "<sender>" and "<subject>"

Examples:
|sender|subject|body|
|tani455@mail.ru|Tatyana|Some text|