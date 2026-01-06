# 🛒 Full-Stack Hybrid Test Automation Framework (OpenCart)

![Java](https://img.shields.io/badge/Language-Java_21%2B-orange)
![Selenium](https://img.shields.io/badge/Tool-Selenium_4-green)
![RestAssured](https://img.shields.io/badge/API-RestAssured-blue)
![Jenkins](https://img.shields.io/badge/CI%2FCD-Jenkins-red)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)

## 📄 Executive Summary
This is an enterprise-grade **Hybrid Test Automation Framework** designed to validate the **OpenCart E-commerce Platform**. 

Unlike traditional UI-only frameworks, this project implements a **"Shift-Left" testing strategy** by combining:
1.  **API Automation (RestAssured)** for backend validation and rapid test data creation.
2.  **Database Testing (JDBC)** for transactional verification ("Source of Truth").
3.  **UI Automation (Selenium)** for end-user workflows.
4.  **CI/CD Integration (Jenkins)** for automated headless execution.


## 🛠️ Tech Stack & Tools

| Component | Technology | Usage |
| :--- | :--- | :--- |
| **Language** | Java (JDK 21) | Core Logic |
| **UI Automation** | Selenium WebDriver 4.x | Browser Interactions |
| **API Automation** | RestAssured | Backend Validation & Data Setup |
| **Database** | MySQL (via JDBC) | Data Integrity Verification |
| **Test Runner** | TestNG | Test Management & Assertions |
| **Build Tool** | Maven | Dependency Management |
| **CI/CD** | Jenkins | Automated Pipeline & Scheduling |
| **Logging** | Log4j2 | Execution Logs |
| **Design Pattern** | Page Object Model (POM) | Code Maintainability |


##  Framework Architecture

The framework is structured into three distinct layers to ensure stability and speed:

1.  **The API Layer:** Used for **Test Data Management**. Instead of creating users via the slow UI, the framework hits the API (`POST /api/register`) to create fresh users in milliseconds before the UI test starts.
2.  **The UI Layer:** Follows **Page Object Model (POM)**. Each web page (Login, Register, Dashboard) has a corresponding Java class.
3.  **The Data Layer:** Connects directly to the **MySQL Database** to verify that frontend actions (like Registration) successfully committed data to the `oc_customer` table.


## 🚀 Key Features & Scenarios

The suite currently covers **14+ critical business scenarios**:

* **Hybrid Authentication:** Creates a user via API, then logs in via Selenium UI (Validates Sync).
* **E-Commerce Flows:** Guest Checkout, Add to Wishlist, Product Comparison.
* **Database Integrity:** Verifies customer records exist in DB after registration.
* **Self-Healing Data:** Automatically generates unique email addresses for every run to prevent "User Already Exists" errors.
* **Robust Headless Execution:** Configured for stability on CI servers.

## ⚙️ Installation & Setup

### Prerequisites
* Java JDK 21+
* Maven 3.x
* Local OpenCart Installation (via XAMPP/Apache) running on `http://localhost/opencartsite/`

### Step 1: Clone the Repository
##  Step 2: Configure Properties

Navigate to src/test/resources/config.properties and update your environment details:

```properties
# Application Configs
url=http://localhost/opencartsite/
browser=chrome
headless=true

# Database Configs
dbUrl=jdbc:mysql://localhost:3306/opencartsite
dbUser=root
dbPassword=
```


##  Step 3: Run Tests

Execute the entire suite using Maven:

```bash
mvn clean test
```


## 🤖 CI/CD Integration (Jenkins)

This project is optimized for Jenkins CI/CD pipelines.

### Headless Configuration

To avoid "Element Not Interactable" issues in Headless CI environments, ChromeOptions are hardcoded in BasePage.java:

```java
options.addArguments("--headless=new");
options.addArguments("--window-size=1920,1080"); // Critical for responsive layouts
options.addArguments("--disable-gpu");
options.addArguments("user-agent=Mozilla/5.0...");
```


## Challenges Solved

### 1. Ghost File Database Corruption

Issue:
```text
Table 'oc_session' doesn't exist
```

Cause:
```text
MySQL/XAMPP corruption during rapid automated writes
```

Solution:
```text
- Manually purged orphaned .ibd tablespace files
- Switched oc_session table engine to MyISAM
```



### 2. Headless Responsive Layout Issue

Issue:
```text
Tests passed locally but failed in Jenkins due to default 800x600 headless resolution
```

Solution:
```java
options.addArguments("--window-size=1920,1080");
```

Result:
```text
Consistent DOM structure across local and CI environments
```

## 📂 Project Structure

```plaintext
Automating-opencart
│
├── src/main/java
│   ├── com.opencart.base
│   │   └── BasePage.java                # WebDriver setup & common methods
│   │
│   ├── com.opencart.pages               # Page Object Model (POM)
│   │   ├── HomePage.java
│   │   ├── LoginPage.java
│   │   ├── RegisterPage.java
│   │   ├── ProductPage.java
│   │   ├── OrderHistoryPage.java
│   │   ├── WishlistPage.java
│   │   ├── ComparisonPage.java
│   │   ├── CheckoutPage.java
│   │   └── ChangePasswordPage.java
│   │
│   └── com.opencart.utils
│       ├── DBUtil.java                  # Database utilities
│       └── TestUtil.java                # Reusable helper methods
│
├── src/main/resources
│   └── (Application resources)
│
├── src/test/java
│   ├── com.opencart.api.base
│   │   └── BaseApiTest.java             # API base configuration
│   │
│   ├── com.opencart.api.tests           # API Test Cases
│   │   ├── LoginApiTest.java
│   │   ├── RegisterApiTest.java
│   │   ├── CartApiTest.java
│   │   ├── CurrencyApiTest.java
│   │   ├── EditCartApiTest.java
│   │   └── HybridLoginTest.java
│   │
│   ├── com.opencart.tests               # UI Test Cases
│   │   ├── LoginTest.java
│   │   ├── RegisterTest.java
│   │   ├── GuestCheckoutTest.java
│   │   ├── OrderHistoryTest.java
│   │   ├── ProductCompareTest.java
│   │   ├── WishlistTest.java
│   │   ├── ChangePasswordTest.java
│   │   └── DatabaseValidationTest.java
│   │
│   └── com.opencart.utilities
│       ├── ExtentManager.java           # Extent report setup
│       └── TestListener.java            # TestNG listeners
│
├── src/test/resources
│   ├── config.properties                # Environment configuration
│   └── log4j2.xml                       # Logging configuration
│
├── pom.xml                              # Maven dependencies
├── testng.xml                           # TestNG suite runner
├── target/                              # Build output
└── test-output/                         # TestNG reports
```


## Author

```text
Saroopya Garapati
```

```bash
git clone [https://github.com/sarupyagarapati/Opencart-Fullstack-Automation-Testing.git](https://github.com/sarupyagarapati/Opencart-Fullstack-Automation-Testing.git)
cd Opencart-Fullstack-Automation-Testing
