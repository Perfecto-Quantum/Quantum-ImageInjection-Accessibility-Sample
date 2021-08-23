@Web
Feature: Google Search

  @WebSearchAccessibilityTesting
  Scenario: Search Quantum
    Given I am on Google Search Page
    And I perform an audit of the accessibility on tag application screen "Google Home Page"
    When I search for "quantum perfecto"
    Then it should have "Quantum" in search results
    And I perform an audit of the accessibility on tag application screen "Google Search Results Page"
    Then I am on Google Search Page
