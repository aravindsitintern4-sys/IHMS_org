@login @mainDash
@billEntry

Feature: Billing Feature 

Scenario: UIN input field
  And I enter "9925415896" in "UIN:" label
  And I press "Enter" key 
  Then "Patient is not registered today or not admitted" toast should be displayed

#Examples:
  #| uin         | 
  #| 9569656967  |	
  #| rtdcfcfcgccc  |
 # | -[]'./,.,/	  |	
  

Scenario: Dropdown field
	And I select "Anandhi A." from "Advised By:" label
