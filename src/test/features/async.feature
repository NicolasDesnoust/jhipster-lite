Feature: Spring boot async

  Scenario: Should add spring boot async
    When I apply "springboot-async" module to default project
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
    Then I should have files in "src/main/java/tech/jhipster/chips/technical/infrastructure/secondary/async"
      | AsyncConfiguration.java |
    And I should have history entry for "springboot-async"
