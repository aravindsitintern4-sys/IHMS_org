@login @mainDash
@billEntry

Feature: Billing Feature 

Scenario: UIN input field
  And I enter "0024425837" in "UIN:" label
  And I press "Enter" key 
  #Then "Patient is not registered today or not admitted" toast should be displayed

#Examples:
  #| uin         | 
  #| 9569656967  |	
  #| rtdcfcfccc  |
 # | -[]'./,.,/	 |	
  
Scenario: MRN FIELD
	Then popup "Pin Verification" should be displayed
	And I enter "370" in "Enter 3 Digit Pin" label
    And I press "Enter" key

Scenario: Patient demography display
	Then Patient demography is displayed

#Scenario: Dropdown field
	#And I select "Others" from "Patient Type:" label
	#Then "Sorry, you are not allowed to change the patient type" toast should be displayed
	
Scenario: Advised by dropdown field
	And I select "Anandhi A." from "Advised By:" label
	
Scenario: Test table contents
	And I select investigation "ACCESSIBLE PRP"
	And I select eye "BE"
	And I select location "RETINA CLINIC"
	And I press "Enter" key
	And I select investigation "90D LENS EXAMINATION"
	And I select location "GENERAL CLINIC"
	And I press "Enter" key
	
Scenario: Patient Category
	And I select "FULL PAYMENT" from "Patient Category:" label
	
Scenario: Payment Type
	And I select "CREDIT CARD" from "Payment Type:" label
	Then popup "Card Details" should be displayed
	
#Scenario: Credit card selection
	#And I select "AXIS - Credit Card" from "Bank Name" label
	#And I enter "3701" in "Card Number" label
	#And I enter "asdfgh" in "Card Holder Name" label
	#And I click "Submit" button

Scenario: NEFT payment selection
	And I select "NEFT PAYMENT" from "Payment Type:" label
	Then popup "NEFT Details" should be displayed
	And I enter "37015754" in "NEFT Ref. No" label
	And I enter "asdfgh" in "Bank Name" label
	And I enter "18-05-2026" in "Transaction Date" label
	And I click "Submit" button 

#Scenario: Patient Sub Category
	#And I select "Subsidy" from "Patient Sub Category:" label
	#And I click "Cancel" button
	
#Scenario: Fees field
	#And I enter "1" on input field
	#And I press "Enter" key

	





	
