package example

import com.datastax.spark.connector._
import org.apache.spark.sql.cassandra._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object Test {

  def getContext: SparkSession = {
    @transient lazy val conf: SparkConf =
      new SparkConf()
        .setAppName("test")
        .set("spark.cassandra.connection.host", "127.0.0.1") //Host can be changed


    @transient lazy val sc: SparkSession = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .config(conf)
      .master("spark://shruti:7077") //Change this to your spark address
      .getOrCreate()

    sc

  }

  def main(args: Array[String]): Unit = {

    //Sample data frames created and run
    val sc = getContext
    val df1 = sc.read.cassandraFormat("kv", "test").load
    val df2 = sc.read.cassandraFormat("kv1", "test").load

    df1.show()
    df2.show()

    val df = df1.join(df2, df1("key") === df2("key"), "full")

    df.show()

  }


}
