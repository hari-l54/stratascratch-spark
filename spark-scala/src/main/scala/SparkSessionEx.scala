import org.apache.spark.sql.SparkSession

object SparkSessionEx {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Spark Example").getOrCreate()

    val range = spark.range(0, 10)

    range.foreach()

  }
}