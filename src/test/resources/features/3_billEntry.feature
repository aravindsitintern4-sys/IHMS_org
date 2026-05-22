@login @mainDash
@billEntry

Feature: Billing Feature 


	
# OTHER PATIENT TYPE
Scenario: Patient type Others 
	And I select "Others" from "Patient Type:" label
	And I verify "UIN:" field is readonly
	And I enter "ABCD" in "Patient Name:" label
	And I select "AARTHY EYE HOSPITAL" from "Referred By:" label
	And I enter "29" in "Age:" label
	And I select "Female" from "Gender:" label
	And I enter "8785961235" in "Mobile No:" label
	And I select investigation "ACCESSIBLE PRP"
	And I select eye "BE"
	And I select location "RETINA CLINIC"
	And I press "Enter" key
	And I click "Cancel" button
	And I click "Yes" button
	
# STAFF PATIENT TYPE
Scenario: Patient type Others 
	And I select "Staff" from "Patient Type:" label
	And I verify "UIN:" field is readonly
	And I enter "BCDE" in "Patient Name:" label
	And I select "Anandhi A." from "Advised By:" label
	And I enter "27" in "Age:" label
	And I select "Female" from "Gender:" label
	And I enter "9548761235" in "Mobile No:" label
	And I enter "29740" in "Emp No:" label
	And I select investigation "ACCESSIBLE PRP"
	And I select eye "BE"
	And I select location "RETINA CLINIC"
	And I press "Enter" key
# Fill others and fill staff without give cancel this below wrning will be shown
	#Then "Already ACCESSIBLE PRP has been in the list" toast should be displayed
	And I click "Cancel" button
	And I click "Yes" button
	
	
# NEGATIVE SCENARIOS

Scenario: OTHERS Patient type Require field validation
	And I select "Others" from "Patient Type:" label
	And I verify "UIN:" field is readonly
	And I enter "AB" in "Patient Name:" label
	And I clear "Patient Name:" input field
	Then I should see "This field is required." validation message
	And I enter "2" in "Age:" label
	And I clear "Age:" input field
	Then I should see "This field is required." validation message
	And I enter "8785" in "Mobile No:" label
	And I clear "Mobile No:" input field
	Then I should see "This field is required." validation message
	And I click "Cancel" button
	And I click "Yes" button
	
Scenario: STAFFS Patient type Require field validation
	And I select "Staff" from "Patient Type:" label
	And I verify "UIN:" field is readonly
	And I enter "AB" in "Patient Name:" label
	And I clear "Patient Name:" input field
	Then I should see "This field is required." validation message
	And I enter "2" in "Age:" label
	And I clear "Age:" input field
	Then I should see "This field is required." validation message
	And I enter "8785" in "Mobile No:" label
	And I clear "Mobile No:" input field
	Then I should see "This field is required." validation message
	And I enter "8785" in "Emp No:" label
	And I clear "Emp No:" input field
	Then I should see "This field is required." validation message
	And I click "Cancel" button
	And I click "Yes" button
	
Scenario: Character required validation in UIN field
	And I enter "002442" in "UIN:" label
	And I should see "10 characters required." validation message

Scenario: INVALID PIN
	And I enter "0024425837" in "UIN:" label
    And I press "Enter" key 
    Then popup "Pin Verification" should be displayed
	And I enter "485" in "Enter 3 Digit Pin" label
    And I press "Enter" key
	Then "Invalid PIN" toast should be displayed

Scenario: UIN empty --> Try to fill Tests ---> (Staffs and Others only allow for it)
    And I select "Out Patient" from "Patient Type:" label
    And I select "Anandhi A." from "Advised By:" label
    And I select investigation "ACCESSIBLE PRP"
	And I select eye "BE"
	And I select location "RETINA CLINIC"
	And I press "Enter" key
	Then "Please load patient details or choose patient type (OTHERS or STAFF)" toast should be displayed
	And I click "Cancel" button
	And I click "Yes" button

Scenario: UIN Required alert
	And I enter "002" in "UIN:" label	
	And I clear "UIN:" input field
	Then I should see "This field is required." validation message

Scenario: Invalid UIN input field
  And I enter "558679665" in "UIN:" label
  And I press "Enter" key

Scenario: UIN input field
  And I enter "0024425942" in "UIN:" label
  And I press "Enter" key 
  #Then "Patient is not registered today or not admitted" toast should be displayed

#Examples:
  #| uin         | 
  #| 9569656967  |	
  #| rtdcfcfccc  |
 # | -[]'./,.,/	 |	
  
Scenario: MRN FIELD
	Then popup "Pin Verification" should be displayed
	And I enter "458" in "Enter 3 Digit Pin" label
    And I press "Enter" key

Scenario: Patient demography display
	Then Patient demography is displayed

Scenario: Dropdown field
	And I select "Others" from "Patient Type:" label
	Then "Sorry, you are not allowed to change the patient type" toast should be displayed
	And I select "In Patient" from "Patient Type:" label
	
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

#CORPORATE 
Scenario: Corporate details
	And I select "CORPORATE" from "Patient Category:" label
	Then popup "Corporate Details" should be displayed
	And I select "AEF TRAUMA FUND" from "Corporate Name" label
	And I enter "515678" in "Document Ref.No" label
	And I select "Employee" from "Employee Grade" label
	And I enter "51665655654" in "Employee Code" label
	And I enter "51235646565" in "Claim ID" label
	And I enter "Corporate remarks entered" in textarea for "Remarks / Registration No" label
	And I click "Submit" button
	And I click delete icon for "Corporate Credit"
	And I click "Yes" button
	
#CREDIT CARD		
Scenario: Credit card selection
	And I select "FULL PAYMENT" from "Patient Category:" label
	And I select "CREDIT CARD" from "Payment Type:" label
	Then popup "Card Details" should be displayed
	And I select "AXIS - Credit Card" from "Bank Name" label
	And I enter "3701" in "Card Number" label
	And I enter "asdfgh" in "Card Holder Name" label
	And I click "Submit" button
	And I click delete icon for "CREDIT CARD"
	And I click "Yes" button

Scenario: Fill test for subsidy
	And I click delete icon for "90D LENS EXAMINATION"
	And I click "Yes" button

#SUBSIDY
Scenario: CASH payment with Subsidy selection
	And I select "FULL PAYMENT" from "Patient Category:" label
	And I select "Subsidy" from "Patient Sub Category:" label
	Then popup "Subsidy Details" should be displayed
	And I select dropdown value as "AEH-Thirumangalam" from "Subsidy Approved By" label
	And I enter "20" in "% Subsidy granted" label
	And I select dropdown value as "CAMP SUBSIDY" from "Reason" label
	And I enter "Subsidy remarks are entered" in "Remarks" label
	And I click "Save" button
	And I click delete icon for "Subsidy Given"
	And I click "Yes" button
	
#CONCESSION
Scenario: CASH payment with Concession selection
	And I select "FULL PAYMENT" from "Patient Category:" label
	And I select "Concession" from "Patient Sub Category:" label
	Then popup "Concession Details" should be displayed
	And I select dropdown value as "AEH-Melur" from "Concession Approved By" label
	And I enter "400" in "% Concession granted" label
	And I select dropdown value as "Clinical Research" from "Reason" label
	And I enter "Concession remarks are entered" in "Remarks" label
	And I click "Save" button
	And I click delete icon for "Concession Given"
	And I click "Yes" button
	
#CASH 
Scenario: CASH payment selection
	And I select "FULL PAYMENT" from "Patient Category:" label
	And I select "CASH" from "Payment Type:" label
	And I clear "Fees:" input field
	And I enter "100" on input field "Fees:" 
	And I press "Enter" key
	And I click delete icon for "CASH"
	And I click "Yes" button

#UPI PAYMENT	
Scenario: UPI payment selection
	And I select "FULL PAYMENT" from "Patient Category:" label
	And I select "UPI PAYMENT" from "Payment Type:" label
	Then popup "UPI Details" should be displayed
	And I select "Manual ICICI - QR" from "Mode of UPI" label
	And I enter "4515121212" in "UPI Ref. No" label
	And I enter "9856471235" in "Mobile No" label
	And I click "Submit" button
	And I click delete icon for "UPI PAYMENT"
	And I click "Yes" button

#NEFT PAYMENT
Scenario: NEFT payment selection 
	And I select "FULL PAYMENT" from "Patient Category:" label
	And I select "NEFT PAYMENT" from "Payment Type:" label
	Then popup "NEFT Details" should be displayed
	And I enter "37015754" in "NEFT Ref. No" label
	And I enter "asdfgh" in "Bank Name" label
	And I enter "18-05-2026" in "Transaction Date" label
	And I click "Submit" button 
	And I click delete icon for "NEFT PAYMENT"
	And I click "Yes" button
	And I click "Cancel" button
	And I click "Yes" button
	
Scenario: valid Test data for bill entry with cash
    And I enter "0024425837" in "UIN:" label
    And I press "Enter" key 
    Then popup "Pin Verification" should be displayed
	And I enter "370" in "Enter 3 Digit Pin" label
    And I press "Enter" key
    Then Patient demography is displayed
	And I select "Anandhi A." from "Advised By:" label   
	And I select investigation "ACCESSIBLE PRP"
	And I select eye "BE"
	And I select location "RETINA CLINIC"
	And I press "Enter" key
	And I select investigation "90D LENS EXAMINATION"
	And I select location "GENERAL CLINIC"                               
	And I press "Enter" key
	And I select "FULL PAYMENT" from "Patient Category:" label
	And I select "CASH" from "Payment Type:" label
	And I clear "Fees:" input field
	And I enter "100" on input field "Fees:" 
	And I press "Enter" key
	And I click "Cancel" button
	And I click "Yes" button


   	





	
