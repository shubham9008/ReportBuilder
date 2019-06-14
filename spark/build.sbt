name := "student-hub"

version := "0.1"

scalaVersion := "2.11.12"

//libraryDependencies += "datastax" %% "spark-cassandra-connector" % "2.4.0"

scalacOptions += "-target:jvm-1.8"

val sparkVersion = "2.4.3"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0",
  "org.apache.spark" %% "spark-core" % "2.4.3",
  "org.apache.spark" %% "spark-streaming" % "2.4.3",
  "org.apache.spark" %% "spark-sql" % "2.4.3",
  "com.datastax.spark" %% "spark-cassandra-connector" % "2.4.0",
  "com.databricks" %% "spark-csv" % "1.5.0"
//  "datastax" % "spark-cassandra-connector" % "2.4.1-s_2.11"
)
