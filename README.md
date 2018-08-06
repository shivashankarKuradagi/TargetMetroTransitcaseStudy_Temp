# TargetMetroTransitcaseStudy
# Requirements
For building and running the application you need:
- JDK 1.8
- Maven 3

# Building the application
Once Downloaded the file extract the zip file.
Go to the Application folder (ex : */TargetMetroTransitcaseStudy) and build using maven.
```sh
    - mvn clean install -e
```
Once build is successfull, you should have a jar file 'metrotransitCaseStudy-0.0.1-SNAPSHOT.jar' available under target folder.

# Running the application
To run the application, use the below command.
```sh
    - java -jar target/metrotransitCaseStudy-0.0.1-SNAPSHOT.jar
```
# Testing the application
Go to browser and type in the url 'http://localhost:9000'

  - Enter bus route, bus stop and select direction.(Empty values will be valid)
  
    Expected result : Route, Stop and DIrection information will be shown with Waiting Time.

  - Enter Invalid bus route, bus stop and select direction.
  
    Expected result : Application will show the invalid input field
