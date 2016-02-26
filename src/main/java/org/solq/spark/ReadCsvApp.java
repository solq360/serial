package org.solq.spark;

import java.util.HashMap;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

public class ReadCsvApp {

    public static void main(String[] args) throws InterruptedException {
	String logFile = ReadCsvApp.class.getResource("").getPath() + "csv.md";
	SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
	JavaSparkContext sc = new JavaSparkContext(conf);
	SQLContext sqlContext = new SQLContext(sc);

	HashMap<String, String> options = new HashMap<String, String>();
	options.put("header", "true");
	options.put("path", "cars.csv");

	DataFrame df = sqlContext.load("com.databricks.spark.csv", options);
	df.select("year", "model").save("newcars.csv", "com.databricks.spark.csv");

    }

}