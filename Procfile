web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
web: java -cp "target/dependency/*" $JAVA_OPTS -DDB_URL=$JDBC_DATABASE_URL webapp.runner.launch.Main --enable-naming --port $PORT target/*.war
