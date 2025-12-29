package easy

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object PostsReactedWithHeart_10087 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("10087 - Posts with Heart - Scala").master("local[*]").getOrCreate()
    val schema1 = "poster bigint, friend bigint, reaction string,date_day bigint, post_id bigint"
    val schema2 = "post_id bigint, poster bigint, post_text string, post_keywords string, post_date date"
    val df_facebook_reactions = (spark.read
      .option("header", "true")
      .schema(schema1)
      .csv("input/10087_facebook_reactions.csv"))
    val df_face_book_posts = (spark.read
      .option("header", "true")
      .schema(schema2)
      .csv("input/10087_facebook_posts.csv"))

    val df_joined = df_face_book_posts.join(df_facebook_reactions, Seq("poster", "post_id"), "left")

    val df_filtered = df_joined.where(col("reaction") === "heart").select("post_id","poster", "post_text", "post_keywords", "post_date")

    df_filtered.show()

    spark.stop()
  }
}
