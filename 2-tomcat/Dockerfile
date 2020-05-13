FROM tomcat:9.0-alpine
ADD sample.war /usr/local/tomcat/webapps/
EXPOSE 8001
COPY server.xml /usr/local/tomcat/conf/server.xml
COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
CMD ["catalina.sh", "run"]
