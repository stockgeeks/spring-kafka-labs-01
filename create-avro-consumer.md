# spring-kafka-labs-01

This is the repository containing the initial labs for the Resona Spring Kafka Basics course. 

In the first part of these labs you will create and practice along with the training Videos and you will build or practice: 

- A docker-compose file to run Kafka locally.
- How to use the Kafka built in console consumer and producer to send and receive messages from Kafka.
- Create and run a Spring Boot project.
- Create simplest possible spring-kafka Consumer to consume the messages from a Kafka topic using spring-kafka default configurations and write a Spring Kafka Consumer Test.
- Create the simplest possible producer to send messages to Kafka using spring-kafka default configurations and write a Spring Kafka Producer Test.
- Create a simple Kafka Consumer with Test using Kafka Mocks, without Spring Kafka.
- Create a simple Kafka Producer with Test using Kafka Mocks, without Spring Kafka.
- How to install and run Kafkacat.

In the second part of the labs you will expand your initial project and create and practice: 

- A docker-compose file to run Kafa and Schema registry
- Create a Consumer using an Avro schema and use a kafka avro console consumer using spring-kafka beans.
- Create a basic Spring REST endpoint and a producer to connect to the consumer.
- Operate basic endpoints from the schema registry


# How to do the labs

Each lab step is explained and represented in a branch on this project with the name ending in `-exercise`. 

The sequence of the labs are explicit in the branch name and each exercise has a corresponding branch with the same name and `-solution` appended to the name. 

The branch names always start with a number and are sequential, to complete the exercise checkout the exercise branch and read the instructions in the README file on that branch, exercises build on each other so following the sequence is important: 

01-create-spring-boot-project-exercise
01-create-spring-boot-project-solution

02-create-simple-rest-endpoint-exercise
02-create-simple-rest-endpoint-solution

# Starting the labs

Let's start the first lab, you can use any IDE of your prefernece (Eclipse, IntelliJ, VSCode) to start the first lab follow the instructions below: 

- clone this project in your local machine, open a terminal window and execute the command to checkout the project: 
    - `git clone git@github.com:stockgeeks/spring-kafka-labs-01.git`

- checkout the first lab, open the README file and follow the instructions there.
    - `git checkout 01-create-spring-boot-project-exercise`  

Once you finish the lab you can compare your solution with our solution, for that checkout the solution branch: `git checkout 01-create-spring-boot-project-solution`

Continue to the next lab checking out the next branch: `git checkout 02- ... `


