ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.3"

javacOptions ++= Seq("--release", "11")

lazy val root = (project in file("."))
  .settings(
    name := "spark-scala"
  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.5.7",
  "org.apache.spark" %% "spark-sql"  % "3.5.7"
)
