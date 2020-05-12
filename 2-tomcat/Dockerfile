FROM tomcat:9.0-alpine
ADD sample.war /usr/local/tomcat/webapps/
EXPOSE 8080
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
CMD ["catalina.sh", "run"]
