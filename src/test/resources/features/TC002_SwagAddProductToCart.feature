Feature: Add Product to the swag application
  As a registered user
  I want to add product into the cart
  So that I can perform transaction

  @SwagAddProductToCart
  Scenario: Product add to cart successfully and perform transaction.
    Given user select the prodcut
    When product available add the product to cart
    And click on add to cart button
    Then Checkout the Product
    Then Enter Your Checkout Information
    Then Continue to the Checkout
    And  Finish the Checkout Process
    Then Back to Home