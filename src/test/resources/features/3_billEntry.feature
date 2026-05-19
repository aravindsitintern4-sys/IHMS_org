@login @mainDash
@billEntry

Feature: Billing Feature 

Scenario: UIN input field
  And I enter "0024425810" in "UIN:" label
  And I press "Enter" key 
  #Then "Patient is not registered today or not admitted" toast should be displayed

#Examples:
  #| uin         | 
  #| 9569656967  |	
  #| rtdcfcfcgccc  |
 # | -[]'./,.,/	  |	
  
Scenario: MRN FIELD
	Then popup "Pin Verification" should be displayed
	And I enter "488" in "Enter 3 Digit Pin" label
    And I press "Enter" key

Scenario: Dropdown field
	And I select "Others" from "Patient Type:" label
	Then "Sorry, you are not allowed to change the patient type" toast should be displayed
	
Scenario: Advised by dropdown field
	And I select "Anandhi A." from "Advised By:" label
	
Scenario: Test table contents
	And I select investigation "ACCESSIBLE PRP"
	And I select eye "BE"
	And I select location "RETINA CLINIC"
	And I press "Enter" key
	
Scenario: Patient Category
	And I select "FULL PAYMENT" from "Patient Category:" label

	
