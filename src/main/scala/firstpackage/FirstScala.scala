package firstpackage

object FirstScala {
  def main(args: Array[String]) {

    import java.io.File
    import org.apache.spark.sql.SparkSession
    import org.apache.spark.sql.Row
    import org.apache.spark.sql.SaveMode

    case class Record(key: Int, value: String)
    val warehouseLocation = new File("spark-warehouse").getAbsolutePath

    print("Hello World")

    val spark = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config("spark.master", "local")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate()

    import spark.implicits._
    import spark.sql

    sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) USING hive")
    sql("LOAD DATA LOCAL INPATH 'c:/spark/examples/src/main/resources/kv1.txt' INTO TABLE src")

  // Queries are expressed in HiveQL
  // sql("SELECT * FROM src").show()

    // The results of SQL queries are themselves DataFrames and support all normal functions.
    val sqlDF = sql("SELECT key, value FROM src WHERE key < 10 ORDER BY key")
    sqlDF.createOrReplaceTempView("SRC_VIEW")

    sql("SELECT * FROM SRC_VIEW").show()
    sql("show databases").show()

    // The items in DataFrames are of type Row, which allows you to access each column by ordinal.
    val stringsDS = sqlDF.map {
      case Row(key: Int, value: String) => s"Key: $key, Value: $value"
    }

  //    stringsDS.show()

  //    val recordsDF = spark.createDataFrame((1 to 100).map(i => Record(i, s"val_$i")))
  //    recordsDF.createOrReplaceTempView("records")

  //    // Queries can then join DataFrame data with data stored in Hive.
  //    sql("SELECT * FROM records r JOIN src s ON r.key = s.key").show()
  }

  def dividable(x: Int, y: Int )=
  {
    x/y
  }

}
