# PracticeQuest 
# Solution with Java, BDD cucumber, restassured, Junit, Maven , Wiremock 
# This solution will call a mocked Rest api Get endpoint(GetNaceDetail) with "order" as query parameter  and return a Json response which contains order #details. And this solution will validate the json response with expected order details.

# what is contained in this project? - Its a maven project
1. A feature file in Feature folder, contains Scenario outline written in Gerkin language.
2. Step Definition - path src/test/java/stepDefintion/GetNaceDetail.java
3. Json mocked response file - src/test/resources/__File/json/NaceDetail.json
4. Runner file in path src/test/java/runner/TestRunner.java

# What this solution does?
1. It will fulfil both the ask in coding problem challenge.
2. Rest endpoint is mocked using Wiremock.

# How to run this code?
1. This code is compatible with Eclipse.
2. Import this project in Eclipse IDE.
3. Download dependencies added in pom.xml
4. run the TestRunner.java file with Junit or run pom.xml using maven build. 
