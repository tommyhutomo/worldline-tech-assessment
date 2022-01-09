Feature: Filling fuel to Engine
  Scenario: client makes call to POST /engine/fill using fueltype PETROL
  	Given engine application is up
    When client calls POST to fill with fueltype PETROL with fuelLevel is 100
    Then the client receives status code of 200  