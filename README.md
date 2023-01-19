
#Technology Used : Java8, Spring Boot, Maven, JPA, Hibernate, Junit, SQL
        
        
#RDBMS Used : MySQL


# ParkingLot-Demo
Step1: Clone ParkingLot-Demo Project.
Step2: Go to ParkingLot-Demo\src\main\resources and import parkinglot.sql in your DB.
Steps3. Import project in your IDE and Run.

                ############################################### API-List ####################################################
1.localhost:8090/assignCar?colour=blue
2.localhost:8090/getAllCarByColour?colour=blue
3.localhost:8090/getSlotByRegNo?regNo=1673942992957
4.localhost:8090/vacateCar?slotId=5

                ############################################### Server-Details ####################################################
server.port=8090
spring.datasource.url=jdbc:mysql://localhost:3306/parkinglot
spring.datasource.username=root
spring.datasource.password=root
