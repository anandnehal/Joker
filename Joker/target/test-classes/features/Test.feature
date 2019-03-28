# And I accept alert
# And I dismiss alert
# And I enter "text" in alert box
# And I switch to frame "frameid"
# Then I verify text display on "register" to be "REGISTER"
# When I click on "register" button
# And I enter "Anand" in "first name" text box
# Then I verify web element "support" is enable
# Then I verify web element "register" is diplayed
# And I check off "one time" checkBox
# Given I launch "mercury" webpage
# And I verify title of webpage to be "Welcome: Mercury Tours"
# And I select "one way" radio button
# And I select "3" from dropdown "number of passenger"
# Then I click on "facebook image" image
# And I right click on "Flights"
# And I double click on "Home"
#	And I mouse hover on "user name"
# And I upload file "C:\\Users\\Anand jha\\Desktop\\glue.txt" to "choose file"
# And I verify tool tip of "google" to be "google"
#Then I verify broken link of webpage
#And I scroll to the "Linux" webElement
# Cross Browser--upcoming
# Then I take screenshot and save it to "D:\\Joker\\Joker\\screenshot\\jo.PNG"
# And I wait for page to load
#Then I click on "song" link

Feature: you are gonna automate an application 
						without any coding

  Scenario: Launch the most popular website
    Given I launch "youtube" webpage
  	And I enter "we can keep it simple baby" in "search box" text box
  	When I click on "search icon" button
  	And I wait for page to load for "8" second
  	Then I click on "song" link
  	And I wait for page to load for "40" second
    Then I take screenshot and save it to "D:\\Joker\\Joker\\screenshot\\jo.PNG"
   
    
