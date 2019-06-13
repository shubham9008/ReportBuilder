package com.vassarlabs.spark.utils

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import com.vassarlabs.spark.constants.Constants

//Creating as new session context
object SparkSessionContext {

  def getContext: SparkSession = {

    val homeDirectory = System.getProperty(Constants.USER_HOME);
    var props = Utils.readFromPropertiesFile(homeDirectory + "/spark-config.properties");

    @transient lazy val conf: SparkConf =
      new SparkConf()
        .setAppName(props.getProperty(Constants.SPARK_APP_NAME))
        .set(Constants.SPARK_CASSANDRA_CONNECTION_HOST, props.getProperty(Constants.SPARK_CASSANDRA_CONNECTION_HOST)) //Host can be changed

    @transient lazy val sc: SparkSession = SparkSession
      .builder()
      .appName(Constants.SPARK_APP_NAME)
      .config(conf)
      .master(props.getProperty(Constants.MASTER)) //Change this to your spark address
      .getOrCreate()

    sc
  }

}
