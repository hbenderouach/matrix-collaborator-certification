Feature: CollaboratorCertification End Point
  Background:
    * url 'http://localhost:8085'
    * header Accept = 'application/json'

  Scenario: Testing valid GET endpoint
    Given path 'collaborator/all'
    When method GET
    Then status 200
    * def first = response[0]
    And match first contains {mailAdresse:"adangote@sqli.com"}

  Scenario: Testing OK reponse GET Certifications Collaborator by email
    Given  path '/collaborator/certifications'
    And param email = 'yelouardi@sqli.com'
    When method GET
    Then status 200
    * def first = response[1]
    And match first contains {certificationTitle:"Sql server"}

  Scenario: Testing error response GET Certifications Collaborator by email
    Given  path '/collaborator/certifications'
    And param email = 'ko@sqli.com'
    When method GET
    Then status 404

  Scenario: Add new Collaborator KO response
    Given  path 'collaborator'
    And request { mailAdresse: 'yelouardi@sqli.com' }
    When method POST
    Then status 302
    And match $ == "This User is Founded"