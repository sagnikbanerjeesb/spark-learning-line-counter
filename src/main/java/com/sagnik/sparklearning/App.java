package com.sagnik.sparklearning;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String SPARK_HOME = "/Users/sagnik.b/sgb_projects/spark-3.1.1-bin-hadoop2.7";
    public static void main( String[] args )
    {
        String logFile = SPARK_HOME + "/README.md"; // Should be some file on your system
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();
        long numAs = logData.filter(new FilterFunction<String>() {
            @Override
            public boolean call(String s) {
                return s.contains("a");
            }
        }).count();
        long numBs = logData.filter(new FilterFunction<String>() {
            @Override
            public boolean call(String s) {
                return s.contains("b");
            }
        }).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
    }
}
