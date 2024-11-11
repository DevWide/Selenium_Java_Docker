# Automated Testing Project With Selenium Java and Docker
This project is an automated testing suite for a sample application using Java, Selenium, Maven, and GitHub Actions for CI/CD. It follows SOLID and CQRS principles to ensure a modular, scalable, and maintainable test code structure.

## Table of Contents
* Overview
* SOLID Principles
* CQRS in Testing
* Project Structure
* Setup and Execution
* GitHub Actions CI/CD

## Overview
This automated testing project is built to validate key functionality in a sample application, including:

* User login
* Adding items to a shopping cart
* Navigating between pages

By following SOLID principles and CQRS (Command Query Responsibility Segregation), the project promotes best practices in test automation, leading to a codebase that is modular, maintainable, and easy to extend as requirements evolve.

## SOLID Principles
In this project, SOLID principles have been applied to organize the code structure and enhance its maintainability. Here’s how each principle has been implemented:

### 1. Single Responsibility Principle (SRP)
Each class has a single, well-defined responsibility:

* ``LoginPage``: Handles only login actions and elements on the login page.
* ``InventoryPage``: Manages interactions with the inventory page, such as adding items to the cart.
* ``CartPage``: Focuses on verifying items in the shopping cart.

Each page object is responsible for interacting with a single page, ensuring modularity and simplicity.

### 2. Open-Closed Principle (OCP)
Classes are open for extension but closed for modification:

* New functionality can be added by creating new methods or classes without modifying existing ones. For example, if a new page is added, a new Page Object class can be created without changing the existing page classes.
* SelectorsLoader centralizes selector management. If selector sources change, only the SelectorsLoader class needs modification, without affecting the main test logic.

### 3. Liskov Substitution Principle (LSP)
Page classes adhere to a predictable interface; for instance, all page classes provide specific methods for interacting with that page. In this context, each page class operates independently and can be reused in different test scenarios.

### 4. Interface Segregation Principle (ISP)
Only relevant methods are provided for each page class, ensuring that test code interacts only with the methods it actually needs. Each Page Object exposes only the specific interactions and verifications for that page.

### 5. Dependency Inversion Principle (DIP)
SelectorsLoader abstracts the source of element selectors, reducing dependency on hard-coded values in page classes. This allows selectors to be managed independently, enabling configuration changes without modifying the main test classes.

## CQRS in Testing
CQRS (Command Query Responsibility Segregation) is applied to separate actions that change the state from those that verify it:

### Commands
Commands in this project are methods that modify the application state, such as:

* ```loginAs``` in ```LoginPage```
* ```addBackpackToCart``` in ```InventoryPage```
* ```openCart``` in ```InventoryPage```

### Queries
Queries are methods that check the state without changing it:

* ```isDisplayed``` in ```InventoryPage``` confirms that the page loaded successfully.
* ```isBackpackInCart``` in ```CartPage``` verifies if the item is in the cart.

By separating these actions, the test code is more readable and better organized, making it easier to understand each test's intent.

## Project Structure
The project structure reflects both SOLID and CQRS principles:

````
├── src
│   └── main
│       └── java
│           └── com.exemplo
│               ├── pages
│               │   ├── LoginPage.java       # Manages login actions
│               │   ├── InventoryPage.java   # Manages inventory actions
│               │   └── CartPage.java        # Manages cart actions
│               ├── tests
│               │   └── LoginTest.java       # Main test file for login and cart actions
│               └── utils
│                   └── ConfigLoader.java    # Loads configurations
│                   └── SelectorsLoader.java # Loads selectors from selectors.json
└── .github
    └── workflows
        └── test.yml                         # GitHub Actions workflow file
````

## Setup and Execution
### Prerequisites
* Java 11 or higher
* Maven for dependency management
* Geckodriver and Firefox for running Selenium tests

### Running Tests Locally
1. Install dependencies:
```
mvn clean install
```

2. Run tests:
```
mvn test
```
Note: The test configuration automatically adjusts paths for geckodriver based on environment variables, making it compatible with both local and CI environments.

## GitHub Actions CI/CD
The GitHub Actions workflow (.github/workflows/test.yml) automates the testing process:

1. On every push or pull request, the workflow builds and tests the application in a Docker container.

2. Steps:
* Checkout: Pulls the code from the repository.
* Cache Maven dependencies: Speeds up the build by caching dependencies.
* Build Docker Image: Creates a Docker image with Firefox and Geckodriver.
* Run Tests: Executes the tests in the Docker container and outputs results.

This setup provides a seamless CI/CD process, allowing for reliable, repeatable testing on each code change.

