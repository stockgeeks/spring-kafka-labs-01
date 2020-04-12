# Console Producer and Consumer

In this lab we will use the built in console Kafka Consumer and Kafka Producer to send and receive simple messages from Kafka. 

Start the Kafka Broker and Zookeeper from from the command line: `docker-compose up -d` just like we have explained in the `Running Kafka for local development` tutorials and once you have them running you can continue with this lab exercise.

Open a new terminal window and check the running docker containers: `docker ps`, you should see Kafka and Zookeeper running and the container names. 

On this terminal window enter the running Kafka container: `docker exec -it kafka bash` the terminal window will enter the running docker container most likely as root, so you should see: `root@kafka:/#` . From the running Kafka container terminal you have now access to the command line tools from Kafka including a Kafka Producer and Kafka Consumer built in Kafka, we're going to use this tools in this lab to: 

- Create a Kafka topic
- List Topics
- Create a simple console Kafka Producer to produce messages to a Kafka Topic.
- Create a simple console Kafka Consumer to receive messages from a Kafka Topic.