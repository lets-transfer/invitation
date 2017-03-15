FROM tomcat:8.0.20-jre8
RUN rm -rf /usr/local/tomcat/webapps/ROOT

#COPY war
COPY /build/libs/exploded/invitation-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
