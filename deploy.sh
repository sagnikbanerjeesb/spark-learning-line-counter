#!/bin/bash

mvn clean package

$SPARK_HOME/bin/spark-submit \
      --class "com.sagnik.sparklearning.App" \
      --master "local[4]" \
      target/line-counter-1.0-SNAPSHOT.jar