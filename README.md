## Run the app
For running the app you need to have docker and docker-compose installed on your machine. 
Then you can run the following command in the root directory of the project:
```Bash 
docker-compose -f ./docker-compose/docker-compose.yml up
```
After that, the app could be run locally and will be available on `localhost:8080`.

## MISC

For the sake of simplicity, the entity structures are not normalized, multiple envs and flyway are not used.
