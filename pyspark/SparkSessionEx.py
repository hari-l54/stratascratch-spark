from pyspark.sql import SparkSession
if __name__ == "__main__":
    spark = SparkSession.builder.appName("PySpark Spark Session Example").master("local[*]").getOrCreate()

    num_range = spark.range(0, 10)

    num_range.show()

    spark.stop()