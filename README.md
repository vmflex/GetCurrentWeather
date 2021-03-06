# Get Current Weather

### Description

- This application is to get real-time weather for a specified city. All the weather information is obtained from third-party Weather API called [apixu]. And parse it then display it friendly on a web page. Once the application is getting deployed, you can see below screen by visiting http://localhost:8080. The city dropdown box can be configured and several cities will be initialized during the application start.

  ![55](https://user-images.githubusercontent.com/17881238/54087907-f36ffe00-4392-11e9-95ea-a011a6ec1a90.png)

### Design

##### １　Framework Design

- SpringBoot with embedded SpringMVC/Spring/Tomcat

- H2 Database to store city information. The H2 is embedded exclusively within the Application and start/stop along with the System. 

- Alibaba Druid is JDBC Connection Tools. And it is integrated to the System to improve performance while connecting to DB.

- Instead of using JDBC directly to touch DB, the application is using MyBatis to improve development efficiency.

- The application is using Swagger to generate Restful Document.

- The application is designed as two parts. One is Java based back-end using DAO->Service->Controller Pattern. And the other part is front-end using JavaScript / Css/ Html. There are no dependency for the two parts. and the two parts is interact with each other by Restful services. and the transfer data format during each interaction is JSON.

- For Test Part, it is using SpringBoot internal Test API.  And as the H2 DB is chosen and is embedded within the application automatically, all test cases for implemented Restful APIs can be run seamlessly without taking care of more details of DB. 

- For  Code Test Coverage,  it is using [JaCoCo] as a Maven plugin. Run below command to generate Code Coverage Report:

  -   mvn clean jacoco:prepare-agent install jacoco:report

  - Alternatively, when packaging/installing the application, then [JaCoCo] will be run automatically.

  - In Report location: target/site/jacoco/, click the index.html, the Coverage Report will be displayed as a Html page as below screen shot shown.

    ![77](https://user-images.githubusercontent.com/17881238/54416830-769aa680-473b-11e9-9dfe-cd9a7fc74b8d.png)

##### 2　API Design

- Get Current Weather Info By City Code

  - URL:  /weatherinfo/{code}
  - Parameter:  code / String / City Code
  - Method: GET
  - In order to follow on the third-party Weather API. Currently City code and City name are both same. For instance, City name is Paris then City code is also Paris.
  - City can be created dynamically by invoking below Restful API.<br/>[/city/save] Create a City<br/>And it need to provide City Code and City Name parameter. and the method is [POST].

- All the available Restful APIs are generated by Swagger. You can use follow URL to check it on-line directly.<br/>http://localhost:8000/swagger-ui.html

  ![66](https://user-images.githubusercontent.com/17881238/54087908-f36ffe00-4392-11e9-92f7-0a2b7fe8fdb9.png)

### Deployment
- As it is a Maven based SpringBoot Application. It can be packaged / installed by using maven commands like [mvn clean package] or [mvn clean install]. A jar file [GetCurrentWeather-0.0.1-SNAPSHOT.jar] would be generated and once inputing [java -jar GetCurrentWeather-0.0.1-SNAPSHOT.jar] command, the application would be run directly.

- Deployment by Docker:

  - In CentOS, create [gcw] folder and upload [GetCurrentWeather-0.0.1-SNAPSHOT.jar] to this folder.

  - Create a Dockerfile 

    ```
    FROM openjdk:8
    MAINTAINER vmflex
    LABEL app="GCW" version="0.0.1" by="vmflex"
    COPY ./GetCurrentWeather-0.0.1-SNAPSHOT.jar gcw.jar
    CMD java -jar gcw.jar
    ```

  - Execute [**docker build -t gcw-0.0.1 .** ] to create docker image.

    ![123](https://user-images.githubusercontent.com/17881238/54420391-d47fbc00-4744-11e9-8815-9c91e5f741c7.png)

  - Execute [**docker run --name gcw -p 8000:8000 -d --name gcw gcw-0.0.1**] to run the application

  - curl http://localhost:8000

### CI/CD

Once changed source code is committed into Github, the Application will be automatically built / tested /packaged  and deployed into Cloud. Below is the URL to access to the Application.

<http://ec2-18-220-200-176.us-east-2.compute.amazonaws.com:8000/>

- Jenkins Pipeline 

  - Checkout: check out source code from Github [<https://github.com/vmflex/GetCurrentWeather>]

  - Test: Application Junit Test and generate Test Coverage Report.

  - Package: Package the Application as a Jar file.

  - Publish: Publish the jar file and docker build file to remote AWS C2. And build docker image and run the image.

![777](https://user-images.githubusercontent.com/17881238/54689419-76931000-4b5a-11e9-8838-e44f3d14ebc7.png)

​    








