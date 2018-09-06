FROM websphere-liberty:javaee8
Add target/den-service-0.0.1-SNAPSHOT.war /config/dropins/den-service.war
ENV LICENSE accept