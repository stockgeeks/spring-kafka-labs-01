# Lab: Run a Console Producer and Consumer


### Goal 

In this lab we will use the built in console Kafka Consumer and Kafka Producer to send and receive simple messages from Kafka. 

### Resources

- Running Kafka and Zookeeper for local development using Docker and Docker Compose.


### Exercise

## Task - Start Local Kafka

Start the Kafka Broker and Zookeeper defined in the docker-compose file, from the command line: `docker-compose up -d`, this will start Kafka and Zookeeper in docker environment running in your machine, just like we have explained in the `Running Kafka for local development` tutorials, once you have them running you can continue with this lab exercise.

## Task - Check Local Kafka

Open a new terminal window and check the running docker containers: `docker ps`, you should see Kafka and Zookeeper running and the container names. 

## Task - Enter terminal Kafka running Docker container

On the same terminal window where you checked Kafka is running, enter the Kafka container: `docker exec -it kafka bash` the terminal window will enter the running docker container most likely as root, so you should see: `root@kafka:/#` in your terminal.  

Inside the running Kafka container terminal you have now access to the command line tools from Kafka made available by the docker image we're using, these tools enable you to manage a Kafka Broker and even run client applications including a Kafka Console Producer and Kafka Console Consumer built in Kafka, we're going to use this tools in this lab. 

## Task - Create a Kafka Topic

In this step we will create a Kafka topic so we can use a producer to send messages to this topic and a Consumer to receive the messages from the topic. 

Create a Kafka topic, in the terminal in the docker container we just entered run: 

```
kafka-topics --create --bootstrap-server kafka:29092 --replication-factor 1 --partitions 1 --topic stock-quotes
```

The topic is silently created. 


## Task - Check the Created Topic
- List Topics

## Task - Create a Console Consumer

- Create a simple console Kafka Producer to produce messages to a Kafka Topic.

## Task - Create a Console Producer

- Create a simple console Kafka Consumer to receive messages from a Kafka Topic.

## Troubleshooting


#### Broker may not be available

If you see errors like: 

```
WARN [AdminClient clientId=adminclient-1] Connection to node 1001 (localhost/127.0.0.1:9092) could not be established. Broker may not be available. (org.apache.kafka.clients.NetworkClient)

```

Most likely its because of the configured internal and external advertise addresses in the docker-compose file configuration for the Kafka Broker. We're executing the kafka commands in this lab from inside the docker container running kafka so we should use the `LISTENER_DOCKER_INTERNAL` addresses.