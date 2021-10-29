# imran_farid_teladoc_challenge

* This repo is used as test automation for teladoc challenge



### Prerequisites

This project should be running on local environments after fulfilling the following Prerequisites

```
1. Latest version of JDK 8. [TJDK 8]
2. Latest version of maven. [maven] (https://maven.apache.org/download.cgi)
3. IDE of your choice. [Recommended : Intellij]


Note: maven pom.xml should take care of required version of the following:

1. Cucumber
2. Selenium Webdriver
3. Junit
4. Maven
```

### Installing

1. After having all Prerequisites then cloning the project, everything should work smoothly.
2. Make sure to include unnecessary files to .gitignore file

## Structure

    imran_farid_teladoc_challenge
          |
          |_src/test/java/
          |	  |_base              Base Test Class with Configuration and Browser Initilization
          |   |_pages             Application pages implemented using PageObjectFactory
          |	  |_stepdefinitions   Step Implementations defined in the feature files   
          |   |_utils             Helper Utility
          |
          |_src/test/resources
          |	  |_features           Cucumber Test Scripts in BDD style

# Install the Project to get all the dependencies
```bash

mvn clean install -DskitpTests=true

```
# Run the Test
```bash

mvn clean install

```

# Test Reports, open in any browsers
```bash

  ~/target/cucumber-reports/cucumber.html/index.html

```


## Authors

* **Imran Fraid** - *Done By work* -

