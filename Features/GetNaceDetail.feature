#Author: your.email@your.domain.com

@GetRequest
Feature: Validation of get method

    @Mock
  Scenario Outline: Mock an rest api endpoint and validate api response
    Given wiremock server is started and Json reponse is mocked
    When user send a get request to the URL to get Nace details with order
    Then validate the status code
    And validate the response include the following "<Order>","<Level>","<Code>","<Parent>","<Description>","<This item includes>","<This item also includes>","<Rulings>","<This item excludes>", "<Reference to ISIC Rev. 4>" 
    And shutdown the wiremock server 
    
 
   Examples: 
   | Sr no| Order  |Level|Code|Parent|Description                      |This item also includes|Rulings|This item excludes|Reference to ISIC Rev. 4 |This item includes|
   | 1    | 398481 | 1   |A   |      |AGRICULTURE, FORESTRY AND FISHING|                       |       |                  |A|                       This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.||
   
  