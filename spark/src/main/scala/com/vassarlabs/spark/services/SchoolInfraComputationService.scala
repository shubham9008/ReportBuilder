package com.vassarlabs.spark.services

import com.vassarlabs.spark.utils._

object SchoolInfraComputationService {

  def main(args: Array[String]): Unit = {

    val sc = SparkSessionContext.getContext
    var props = Utils.readFromPropertiesFile("/home/aman/backend-prop.properties");
    println(sc)
    Utils.readFileAsDataFrame("/home/aman/geo.csv","true","csv","#",sc);
//    //Sample data frames created and run
//    val sc = getContext
//    val df1 = sc.read.cassandraFormat("kv", "test").load
//    val df2 = sc.read.cassandraFormat("kv1", "test").load
//
//    df1.show()
//    df2.show()
//
//    val df = df1.join(df2, df1("key") === df2("key"), "full")
//



//    df.show()

  }


}
