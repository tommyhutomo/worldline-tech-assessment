Feature: Stop Engine
  Scenario: client makes call to POST /engine/stop using fueltype PETROL
  	Given engine application is up
    When the client calls POST /engine/stop with fueltype PETROL
    Then the client receives status code of 200  