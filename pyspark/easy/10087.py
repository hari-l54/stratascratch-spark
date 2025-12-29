#ID: 10087
#Title: Find all posts which were reacted to with a heart
#Description: Find all posts which were reacted to with a heart.
#             For such posts output all columns from facebook_posts table.

#Table1: facebook_reactions
#Schema:
    # poster: bigint
    # friend: bigint
    # reaction: string
    # date_day: bigint
    # post_id: bigint

#Table2: facebook_posts
#Schema:
    # post_id: bigint
    # poster: bigint
    # post_text: string
    # post_keywords: string
    # post_date: date

from pyspark.sql import SparkSession
from pyspark.sql.functions import *
if __name__ == "__main__":
    spark = SparkSession.builder.appName("10087 - Posts with Heart").master("local[*]").getOrCreate()

    schema1 = "poster bigint, friend bigint, reaction string,date_day bigint, post_id bigint"
    schema2 = "post_id bigint, poster bigint, post_text string, post_keywords string, post_date date"
    df_facebook_reactions = (spark.read
                             .option("header", "true")
                             .schema(schema1)
                             .csv("../../input/10087_facebook_reactions.csv"))
    #df_facebook_reactions.show()
    #df_facebook_reactions.printSchema()
    df_face_book_posts = (spark.read
                          .option("header", "true")
                          .schema(schema2)
                          .csv("../../input/10087_facebook_posts.csv"))
    #df_face_book_posts.show()
    #df_face_book_posts.printSchema()

    df_joined = df_face_book_posts.join(df_facebook_reactions, ["poster", "post_id"], "left")

    df_filtered = df_joined.where(col("reaction") == "heart").select("post_id","poster", "post_text", "post_keywords", "post_date")

    df_filtered.show()

    spark.stop()

