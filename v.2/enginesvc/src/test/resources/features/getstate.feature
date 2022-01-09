Feature: Stop Engine
  Scenario: client makes call to GET /engine using fueltype PETROL
  	Given engine application is up
    When the client calls GET /engine with fueltype PETROL
    Then the client receives status code of 200
    And  the client receives response is contain NOT RUNNING word  