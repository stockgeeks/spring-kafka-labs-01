# Create a Spring Boot Application

In this lab you will create a Spring Boot Application wich will be used in other exercises as a starting point for tasks and hands on exercises. 

## Goal 

The goal of this lab is to have a running Spring Boot Application with `Spring for Apache Kafka and Spring Web` configured.


## Task - Create a new Spring Boot Application

Navigate to [https://start.spring.io](https://start.spring.io)

Fill in the form and click Generate:

![Spring Initialzr Web Site](./imgs/spring-initializr-web-site.png)

## Task - Extract the generated Application to working directory

We added the extracted content to a folder in this trainning repo, check the VSCode overview in the image below:

![Solution - Extract generated App](imgs/solution_task_extract_generated_springboot_app_to_working_dir.png)

## Task - Build and run the project

 Build the project using Maven, same directory where the `pom.xml` is: `mvn clean package`

![Solution - Build Project](imgs/solution_task_build_project.png)

 Run it: `mvn spring-boot:run` 

![Solution - Run Project](imgs/solution_task_run_project.png)