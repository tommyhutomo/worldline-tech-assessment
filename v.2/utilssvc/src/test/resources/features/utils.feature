Feature: Provide fuel details
  Scenario: client makes call to GET /utils using fueltype PETROL
  	Given utils application is up
    When the client calls /utils with fueltype PETROL
    Then the client receives status code of 200 
    And the client receives costPerbatch is 9.0
    And the client receives batchSize is 8
    And the client receives engineName is DefaultEngine

  Scenario: client makes call to POST /utils using fueltype WOOD
    Given utils application is up
    When the client calls /utils with fueltype WOOD
    Then the client receives status code of 200
    And the client receives costPerbatch is 4.35
    And the client receives batchSize is 2
    And the client receives engineName is StreamEngine
    
   Scenario: client makes call to POST /utils using fueltype DIESEL
    Given utils application is up
    When the client calls /utils with fueltype DIESEL
    Then the client receives status code of 200
    And the client receives costPerbatch is 12.0 
    And the client receives batchSize is 8 
    And the client receives engineName is DefaultEngine
    
   Scenario: client makes call to POST /utils using fueltype COAL
    Given utils application is up
    When the client calls /utils with fueltype COAL
    Then the client receives status code of 200
    And the client receives costPerbatch is 5.65 
    And the client receives batchSize is 2 
    And the client receives engineName is StreamEngine