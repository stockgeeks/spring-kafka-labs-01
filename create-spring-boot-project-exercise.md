# Create a Spring Boot Application

In this lab you will create a Spring Boot Application wich will be used in other exercises as a starting point for tasks and hands on exercises. 

## Goal 

The goal of this lab is to have a running Spring Boot Application with `Spring for Apache Kafka and Spring Web` configured.


## Task - Create a new Spring Boot Application

Open a browser and navigate to [https://start.spring.io](https://start.spring.io), you should see this website displayed below. 

> Try to use the same versions as we recommend on the labs but don't worry too much if the version doesn't exactly match the version you see in this guide, Spring Boot, Kafka and Spring Kafka evolve fast but most likely small divergence in the versions will not break compatibility with the exercises here, we will update the exercises every time this happens. 

Spring Boot Initializer Web Site: 

![Spring Initialzr Web Site](./imgs/spring-initializr-web-site.png)

Fill in the fields with the following values, leave any fields not mentioned here with the default values: 

Project: `Maven Project`

Language: `Java`

Spring Boot: `2.2.6`

Project Metadata:

    Group: io.resona
    Artifact: simple-spring-kafka
    Options: 
        Name: spring-kafka-labs
        Description: My spring-kafka-project
        Package name: io.resona.springkafka.labs
        Packaging: Jar
        Java: 11

Dependencies: 

    Spring for Apache Kafka
    Spring Web    

Click the green `Generate` button and download the project. 

## Task - Extract the generated Application to working directory

Extract the downloaded file to your prefered working directory location where you put your studying projects. 

> Optionally import the project in your favorite IDE if you want to explore it.

## Task - Build and run the project

 - Open a command line, navigate to the place where you extracted the generated project and bulild the project using Maven: `mvn clean package`

 - Once the project finish building, run it: `mvn springboot:run` wait for the starting message.

 Congratulations you now have a Spring Boot Application Running in your local developer Machine.





