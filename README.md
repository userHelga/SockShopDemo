# Sock Shop Testing

## Before running test 
* ### Clone the application repository
git clone https://github.com/microservices-demo/microservices-demo
cd microservices-demo
* ### Start the application via Docker Compose
docker-compose -f deploy/docker-compose/docker-compose.yml up -d
* ### Set ```baseUrl``` (localhost:80) in ```src/test/resources/config.properties``` file

## Run tests
run ```mvn clean test```
or run tests from folder ```src/test/java```
