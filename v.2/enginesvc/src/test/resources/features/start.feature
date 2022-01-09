Feature: Start Engine
  Scenario: client makes call to POST /engine using fueltype PETROL
  	Given engine application is up
    When the client calls POST /engine with fueltype PETROL
    Then the client receives status code of 200 