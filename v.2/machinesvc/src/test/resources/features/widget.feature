Feature: Calculate production cost
  Scenario: client makes call to POST /widget using fueltype PETROL
  	Given widget application is up
    When the client calls /widget with fueltype PETROL and quantity 10
    Then the client receives status code of 200 
    And the client receives productionCost 18.0

  Scenario: client makes call to POST /widget using fueltype WOOD
    Given widget application is up
    When the client calls /widget with fueltype WOOD and quantity 10
    Then the client receives status code of 200
    And the client receives productionCost 21.75
    
   Scenario: client makes call to POST /widget using fueltype DIESEL
    Given widget application is up
    When the client calls /widget with fueltype DIESEL and quantity 10
    Then the client receives status code of 200
    And the client receives productionCost 24.0
    
   Scenario: client makes call to POST /widget using fueltype COAL
    Given widget application is up
    When the client calls /widget with fueltype COAL and quantity 10
    Then the client receives status code of 200
    And the client receives productionCost 28.25  