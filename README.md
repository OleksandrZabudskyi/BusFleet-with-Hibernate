# BusFleet
Java Training Final Project

## Project Description
System Auto fleet. Drivers and Administrators can enter the system.
In the fleet there are buses, routes. 
The administrator can assign free buses to the routes, to the buses of free drivers, and also to release them, making them free. 
The driver can see his place of work, and also he must confirm his new appointment.

## Developer
Zabudskyi Oleksandr

## Mentor
Lyashenko Maxym

## Installation instruction
1. Open MySQL Server and crete connection with parameters: url = jdbc:mysql://localhost:3306/BusFleet, user = root, password = ad12min99 
or create your own connection just editing connection parameters in property file src/main/resources/application.properties
2. Run SQL script "create_db.sql" from root project dir which create "BusFleet" scheme and populating with data. 
(Note: Default encrypted password for Drivers and Admins "12345" in db schema)

## Deployment instruction
1. Install maven https://maven.apache.org/install.html
2. In project directory open command line interfaces such as the Bash(Linux) or PowerShell(Windows)
3. Enter command "mvn clean install tomcat7:run"
4. Open browser and follow the link http://localhost:8888/app

