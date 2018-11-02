Building the application:

mvn clean install

Running the application on the command prompt.

mvn spring-boot:run -Dspring-boot.run.profiles=production


Launching the application:

http://localhost:8080/index

Other setup information:

Download the certificate of the external resource  and keep the certificate in the java cert store with the name a.jks

Test:

1) Integration tests
  Integration tests are written for the ChampionShipService and ChampionShipController with out having any external dependency for it to be deterministic. Although a Unit test would suffice for the same thing loading the spring config
  heps to find any bean related issue early.


2) Unit Tests
   Unit tests written for ExternalChampionShipServiceImpl and ChampionShipController
