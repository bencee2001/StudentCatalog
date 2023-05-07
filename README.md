# StudentCatalog

[Dokumentáció](https://github.com/bencee2001/StudentCatalog/blob/main/docs/StudentCatalog_Doc.pdf)

[Swagger UI link](http://localhost:8080/swagger-ui/index.html#/)

Liquibase:

1. mvn spring-boot:run 
2. mvn liquibase:diff -Ddiff.version=1.0
    - version szám növekedjen ízlés szerint
3. verzió választáshoz master.xml fáljba a megfelelő changelog file xml fálj kerüljön
