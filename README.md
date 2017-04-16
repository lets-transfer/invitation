# Invitation Project

## Development
### Requirements
- Docker Environment :   
MySQL을 Docker로 이용해서 기동하고 있으므로 Docker가 필요합니다.  
https://www.docker.com/products/docker#/mac 여기를 참고하셔서 설치하시길 바랍니다

- docker run -d -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=true --name invitation-mysql mysql
- docker exec -it invitation-mysql /bin/bash
- mysql -uroot
- create database invitation
- show databases;

- JDK 1.8

### IntelliJ 환경 구성하기
1. Git Clone후 IntelliJ 에서 Open으로 Clone 받은 프로젝트를 엽니다
1. Gradle Importer가 뜨면 설정해주시고
1. Gradle Refresh를 통해서 Refresh를 해주시기 바랍니다



## Testing
