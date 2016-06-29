# Simple example using ActiveJDBC.
Usage:

*  Executed `create.sql`.  (Ootionally -Dexec.args="-C")
*  Generate one-to-many relationship
*  Insert, Update, Delete and Query

```
mvn clean package  exec:java -Dexec.mainClass="activejdbc.examples.simple.SimpleExample" -Dexec.args="-C" -Dexec.cleanupDaemonThreads=false -Dorg.slf4j.simpleLogger.defaultLogLevel=debug

```



