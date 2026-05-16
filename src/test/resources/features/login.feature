@login
Feature: Login Feature

Background:
  Given initially on login page

# INVALID LOGIN 
#Scenario: Empty field
	  #When I enter username "" and password ""
     # And I click "LOGIN" button
  	  #Then  popup "Validation" should be displayed


#Scenario Outline: Login with invalid credentials
  #When I enter username "<username>" and password "<password>"
  #And I click "LOGIN" button
  #Then  popup "Failed" should be displayed
  #Then screenshot should be captured "failed"

#Examples:
 # | username  | password    |
 # | Dev       | wrong123    |
 # | Aravind   | test123     |
 # | abc       | abc123      |

#VALID LOGIN

Scenario: Valid Login
  When I enter username "aravindadmin" and password "aravind@123"
  And I click "LOGIN" button
  Then I navigated to ihms dashboard