# list topics
docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092 && \

echo 'creating topic stock-quotes' && \

docker exec -it kafka kafka-topics --create --bootstrap-server localhost:29092 --replication-factor 1 --partitions 1 --topic stock-quotes && \

echo 'listing topics' && \

docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092

exit 0;

