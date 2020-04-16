FROM java:8
WORKDIR /
ADD target/c24-omma.jar c24-omma.jar
EXPOSE 80
CMD sleep 10 && java -Dspring.datasource.url="jdbc:mysql://omma-db:3306/omma?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin" -jar c24-omma.jar
