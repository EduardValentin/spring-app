#!/bin/bash

echo "Build and $1..."
mvn clean
mvn install
mvn -pl=./web tomee:$1 &
deploy_pid=$!

while inotifywait --exclude .swp -e delete -e create -e modify -r pom.xml web/pom.xml api/pom.xml service/pom.xml \
        ./service/src/main/java ./api/src/main/java \
        ./web/src/main/webapp
do
    # Kill the existing build, if any
    echo "KILLING build process $deploy_pid"
    kill $deploy_pid
    # Launch deploy in background, and store PID for reset if there's another change
    mvn clean
    mvn install
    mvn -pl=./web tomee:$1 &
    deploy_pid=$!
done;