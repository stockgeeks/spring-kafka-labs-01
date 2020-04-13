# Lab: Run a Console Producer and Consumer - Solution


### Goal 

In this lab we will use the built in console Kafka Consumer and Kafka Producer to send and receive simple messages from Kafka. 

### Exercise

## Task - Start Local Kafka

Start the docker-compose configuration for this exercise: `docker-compose up -d`

![Solution - Task image](imgs/solution_task_start_local_kafka_docker_compose.png)

## Task - Check Local Kafka

List the running containers: `docker ps`

![Solution - Task image](imgs/solution_task_check_local_kafka_docker_compose.png)

## Task - Enter terminal Kafka running Docker container

Enter Kafka running docker container: `docker exec -it kafka bash`

![Solution - Task  image](imgs/solution_task_enter_kafka_docker_container_terminal.png)

## Task - Create a Kafka Topic

Create a Kafka topic: 

```
kafka-topics --create --bootstrap-server kafka:29092 --replication-factor 1 --partitions 1 --topic stock-quotes
```

![Solution - Create Topic](imgs/solution_task_create_topic.png)

Check the topics: 

```
kafka-topics --list --bootstrap-server kafka:29092
```

![Solution - List Topics](imgs/solution_task_list_topics.png)

## Task - Create a Console Consumer

- Create a simple console Kafka Consumer.

```
kafka-console-consumer --bootstrap-server kafka:29092 --topic stock-quotes
```

![Solution - Create console Consumer](imgs/solution_task_create_console_consumer.png)

## Task - Create a Console Producer

- Create a Simple console Kafka Producer.

```
kafka-console-producer --broker-list kafka:29092 --topic stock-quotes
```

![Solution - Create console Producer](imgs/solution_task_create_console_producer.png)

Sending some messages: 

![Solution - Send some messages](imgs/solution_task_send_some_messages.png)