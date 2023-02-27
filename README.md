# Selenium-Java (for UI) - GitHub Action for CI overview
This is an project to demonstrate QA Test how to work with Selenium cucumber and REST API for Java

[![Selenium & REST-API with Maven](https://github.com/actions/toolkit/workflows/Main%20workflow/badge.svg)](https://github.com/psubrambe/e2e-framwork/actions/workflows/maven.yml?query=Java+CI+with+Maven)
#### Installation (pre-requisites)
* JDK 1.8+ (make sure Java class path is set) 
* Maven (make sure .m2 class path is set)
* IntelIJ
* IntelIJ Plugins for
      * [Gherkin](https://plugins.jetbrains.com/plugin/9164-gherkin)
      * [Cucumber for Java](https://plugins.jetbrains.com/plugin/7212-cucumber-for-java)


#### Primary IDE used : IntelliJ IDEA

* Download and install your IDE using [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) or etc.
* Open your IDE that installed
* Open the Cloned project :
    * Click Open and navigate to the folder where project is cloned
    * Select the pom.xml and [open as project](https://prnt.sc/1hkm7pt)
    * It will automatically download the respective maven dependencies 
    * Any error in loading dependencies navigate to preferences and go to
      * [IntelliJ]() --> [Preferences]() --> [Build,Execution,Deployment]() --> [Build Tools]() --> [Maven]() --> [Repositories]()
      make sure there is no "[ERROR]()" else click update button
    * Loaded Maven Project should be like [this](https://prnt.sc/1hknens)

#### Execution of Test in Local Machine: IntelliJ IDEA

* Navigate to runner folder and execute any test runner file [Runner](https://prnt.sc/1hkovoo)
* We can execute test cases from testng.xml as well [testng.xml](https://prnt.sc/1hkovoo)

#### Execution of Test in Local Machine Using Command prompt:
* Navigate to Project folder 
* Run below command in command Prompt
            `mvn clean install` or `mvn test` or `mvn verify`

#### Execution of Test in GitHub Actions
* Navigate to [GITHUB ACTIONS](https://github.com/psubrambe/e2e-framwork/actions)
* To View the latest Test Cases executed in [pipeline](https://github.com/psubrambe/e2e-framwork/actions/runs/1079241205)

#### Execution of Test Jenkins
* Project has been configured with the Jenkins file were all respective stage has been cleared coded.
* Need to establish a connection between github or any repository and Jenkins 
* Clone the Repo from Jenkins
* Configure build stages to execute the test cases

#### Task Analysis and Execution

* I have recorded the Analysis of task and how i have approaced to complete the task [Analysis and Execution](./CaseStudy.txt) 

