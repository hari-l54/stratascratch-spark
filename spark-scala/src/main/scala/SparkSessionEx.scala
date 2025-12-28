import org.apache.spark.sql.SparkSession

object SparkSessionEx {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Spark Example")
      .master("local[*]")
      .getOrCreate()

    val range = spark.range(0, 10)

    range.show(false)

    spark.stop()
  }
}