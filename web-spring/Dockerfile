FROM java:8
WORKDIR /
ADD target/c24-thriftshop.jar c24-thriftshop.jar
EXPOSE 80
CMD sleep 10 && java -Dspring.datasource.url="jdbc:mysql://thriftshop-db:3306/thriftshop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin" -jar c24-thriftshop.jar
