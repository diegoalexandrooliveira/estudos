FROM payara/micro:latest

COPY target/*.war /opt/payara/deployments

COPY postgresql-42.2.8.jar /opt/payara

ENTRYPOINT java -jar /opt/payara/payara-micro.jar --addJars /opt/payara/postgresql-42.2.8.jar --deploy /opt/payara/deployments/*.war