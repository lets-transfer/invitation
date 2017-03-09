FROM ubuntu:16.04
MAINTAINER ksbang39@gmail.com

# configuration
RUN apt-get update \
		&& apt-get upgrade -y  \
		&& apt-get install -y curl unzip

#Install JDK8

RUN (curl -L -k -b "oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u92-b14/server-jre-8u92-linux-x64.tar.gz | gunzip -c | tar x) \
		&& mv /jdk1.8.0_92 /jdk

ENV JAVA_HOME /jdk
#ENV PATH $PATH:$JAVA_HOME/bin

#Install Tomcat8

ENV TOMCAT_VERSION 8.0.41

RUN (curl -L http://www.us.apache.org/dist/tomcat/tomcat-8/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz | gunzip -c | tar x) \
		&& mv /apache-tomcat-$TOMCAT_VERSION /apache-tomcat \
		&& rm -fR /apache-tomcat/webapps/*

ENV TOMCAT_HOME /apache-tomcat
ENV PATH $PATH:$JAVA_HOME/bin:$TOMCAT_HOME/bin

#ENV PATH $PATH:$TOMCAT_HOME/bin
