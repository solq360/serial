package org.solq.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.rdd.RDD;

public class SimpleApp {

    public static void main(String[] args) throws InterruptedException {
	String logFile = SimpleApp.class.getResource("").getPath() + "README.md";
	SparkConf conf = new SparkConf().setAppName("Simple Application").setMaster("local");
	JavaSparkContext sc = new JavaSparkContext(conf);
	JavaRDD<String> logData = sc.textFile(logFile).cache();
	long numAs = logData.filter(new Function<String, Boolean>() {
	    public Boolean call(String s) {
		return s.contains("a");
	    }
	}).count();

	long numBs = logData.filter(new Function<String, Boolean>() {
	    public Boolean call(String s) {
		return s.contains("b");
	    }
	}).count();

	//logData.saveAsTextFile("d:\\testspark");
	
	System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
	
	while(true){
	    Thread.sleep(500);
	}
    }

}