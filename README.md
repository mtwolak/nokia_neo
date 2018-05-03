# Example project with mariadb and spring in dockers connected with docker composer

## Requirements:
* docker
* docker-composer
* gradle
* java 8

For local development add -Dspring.profiles.active=localhost to VM options

## Executing
- With local build: **./run.sh**
- Without local build: **docker-compose up -d --build**

## Running service
* service running on http://localhost:8080
* swagger available on http://localhost:8080/swagger-ui.html

### Continent operations
- curl -X GET "http://localhost:8080/continent" -H  "accept: application/json;charset=UTF-8"
- curl -X POST "http://localhost:8080/continent?continentName=Europe" -H  "accept: */*"
- curl -X DELETE "http://localhost:8080/continent?continentNames=Europe&continentNames=NA" -H  "accept: */*"

### Country operations
- curl -X GET "http://localhost:8080/country-by-continent-or-country-name" -H  "accept: */*"
- curl -X GET "http://localhost:8080/country-by-continent-or-country-name?continentName=Europe&countryName=France" -H  "accept: */*"
- curl -X POST "http://localhost:8080/country?countryName=France&continentName=Europe" -H  "accept: */*"
- curl -X DELETE "http://localhost:8080/country?countryName=France&continentName=Europe" -H  "accept: */*"

### City operations
- curl -X GET "http://localhost:8080/city?continentName=Europe&countryName=France" -H  "accept: */*"
- curl -X POST "http://localhost:8080/city?continentName=Europe&countryName=France&cityName=Paris" -H  "accept: */*"
- curl -X DELETE "http://localhost:8080/city-continent?continentName=Europe" -H  "accept: */*"
- curl -X DELETE "http://localhost:8080/city-country?countryName=France" -H  "accept: */*"
- curl -X DELETE "http://localhost:8080/city-name?cityName=Paris" -H  "accept: */*"
