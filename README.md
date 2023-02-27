# Selenium-Java (for UI)
This is an project to demonstrate QA Test how to work with Selenium cucumber for Java


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

* Navigate to UserLogin.feature file and execute it by clicking the run command right clicking on the Scenario.

#### Execution of Test in Local Machine Using Command prompt:
* Navigate to Project folder 
* Run below command in command Prompt
            `mvn clean install` or `mvn test` or `mvn verify`

#### Execution of Test in GitHub Actions
* Navigate to [GITHUB ACTIONS](https://github.com/Arunkumar-Nagaraj/TradingView)




