@Product
Feature: This feature is to create and verify Product information

  Scenario Outline: Create a product item with a unique ID
    When I create a product with the following details:
      | product_name   | product_cpu_model   | product_price   | product_hard_disk_size   | product_year   |
      | <product_name> | <product_cpu_model> | <product_price> | <product_hard_disk_size> | <product_year> |
    Then the Product API should return status code 200
    And the response should match the expected product model

    Examples:
      | product_name         | product_cpu_model | product_price | product_hard_disk_size | product_year |
      | Apple MacBook Pro 20 | Intel Core i10    | 2500          | 1 TB                   | 2019         |
      | Apple Airpods        | Generation 5      | 260           | 500 GB                 | 2018         |


  Scenario: Retrieve list of all product items
    When I request the list of all product items
    Then the Product API should return status code 200


  Scenario: Retrieve a specific product item
    When I request a product item by its ID
    Then the Product API should return status code 200


  Scenario: Delete an existing product item
    When I create a product with the following details:
      | product_name         | product_cpu_model | product_price | product_hard_disk_size | product_year |
      | Apple MacBook Pro 20 | Intel Core i10    | 2500          | 1 TB                   | 2019         |
    When I delete a product by its ID
    Then the Product API should return status code 200


  Scenario: Retrieve a specific invalid product item
    When I retrieve the product by wrong ID
    Then the Product API should return status code 405


  Scenario: Retrieve list of all product items with invalid Request method
    When I request the list of all product items with POST request
    Then the Product API should return status code 400

