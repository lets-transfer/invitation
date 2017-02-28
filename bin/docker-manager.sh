#!/bin/bash
cwd=$(pwd)

run_mysql() {
  docker run \
    --name invitation-mysql \
    -v $cwd/mysql_data:/var/lib/mysql \
    -e MYSQL_ALLOW_EMPTY_PASSWORD=1 \
    -p 3306:3306 \
    -d \
  	mysql
}

start_mysql() {
  docker start invitation-mysql
}

stop_mysql() {
  docker stop invitation-mysql
}

remove_mysql() {
  docker rm invitation-mysql
}

COMMAND=$1

if [ -z $COMMAND ] ; then
	echo "Please Enter command. Availaibe commands are following:"
	echo "run_mysql, stop_mysql, start_mysql, remove_mysql"
	exit
fi

case "${COMMAND}" in
	run_mysql)
		echo "Creating Mysql Docker and Run!"
		run_mysql
	;;
	stop_mysql)
		echo "Stopping Mysql Docker"
		stop_mysql
	;;
	start_mysql)
		echo "Starting Mysql Docker"
		start_mysql
	;;
	remove_mysql)
		echo "Remove Mysql Docker"
		remove_mysql
	;;

esac