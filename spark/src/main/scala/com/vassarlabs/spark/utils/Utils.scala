package com.vassarlabs.spark.utils

import java.io.FileInputStream
import java.util.Properties
import org.apache.spark.sql.DataFrame
import com.databricks.spark
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.SparkSession

object Utils {

  def readFromPropertiesFile(fileLocation: String) : Properties = {
    val props: Properties = new Properties
    props.load(new FileInputStream(fileLocation))
    props
  }

  //CSV only for now...add for excel too
  def readFileAsDataFrame(fileLocation : String, header : String, fileType : String, delimiter : String, sc : SparkSession) : DataFrame = {

    val df =  sc.read.format(fileType).option("header", header).load(fileLocation)
    println(df.show())
    df
  }
}
