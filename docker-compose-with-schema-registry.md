# Docker compose with Schema Registry

In this lab we're going to start using [Avro schemas](https://avro.apache.org/docs/current/) to define the contract for 
our Kafka messages.

The first step is to define it as infra-structure for our local development. For that we're going to use the [Confluent's 
docker image](https://hub.docker.com/r/confluentinc/cp-schema-registry) which enables us to start using the Avro schemas 
with very small effort in simple way.

Newer versions of Confluent schema registry have also added supporto to Google's protobuff and Json Schemas but for 
this exercise we're going to use the Avro Schemas as they're more common up to this date.